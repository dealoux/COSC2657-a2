package ducle.greenapp.activities.user;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;

import ducle.greenapp.AppRepository;
import ducle.greenapp.R;
import ducle.greenapp.activities.map.MapsRegisterLocationFragment;
import ducle.greenapp.activities.utils.MyFragment;
import ducle.greenapp.database.models.user.User;

public class UserEditFragment extends MyFragment {
    private static final int CREATE = 0;
    private static final int EDIT = 1;

    private int state;
    private LatLng latLng;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent intent = getActivity().getIntent();
        Bundle bundle = getArguments();

        EditText userId = (EditText) view.findViewById(R.id.userId);
        EditText userType = (EditText) view.findViewById(R.id.userType);
        EditText fName = (EditText) view.findViewById(R.id.fName);
        EditText lName = (EditText) view.findViewById(R.id.lName);
        EditText location = (EditText) view.findViewById(R.id.location);
        EditText username = (EditText) view.findViewById(R.id.username);
        EditText password = (EditText) view.findViewById(R.id.password);
        Button buttonConfirm = (Button) view.findViewById(R.id.buttonConfirm);
        Button buttonDelete = (Button) view.findViewById(R.id.buttonDelete);

        User user;

        if(bundle.getString("userId") == null){
            state = CREATE;
            getActivity().setTitle("Register");
            user = AppRepository.Instance(getContext()).nextVolunteer(bundle.getString("username"), bundle.getString("password"));
        }
        else{
            state = EDIT;
            getActivity().setTitle("Edit Profile");
            user = AppRepository.Instance(getContext()).getUser(bundle.getString("userId"));
            latLng = user.getLatLng();

            if(intent.getStringExtra("userId").startsWith("ADM")){
                buttonDelete.setVisibility(View.VISIBLE);
            }
        }

        userId.setText(user.getId());
        userType.setText(user.getClass().getSimpleName());
        fName.setText(user.getfName());
        lName.setText(user.getlName());
        location.setText(user.getLatLng().toString());
        username.setText(user.getUsername());
        password.setText(user.getPassword());

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragmentFl, new MapsRegisterLocationFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        getParentFragmentManager().setFragmentResultListener("location", this, (requestKey, result) -> {
            latLng = result.getParcelable("latLng");
            location.setText(latLng.toString());
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fName.getText().toString().isEmpty() ||
                        lName.getText().toString().isEmpty() ||
                        location.getText().toString().isEmpty() ||
                        username.getText().toString().isEmpty() ||
                        password.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(state == CREATE && !AppRepository.Instance(getContext()).validateUsername(username.getText().toString())) {
                        Toast.makeText(getActivity(), "Username already exists", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    user.setLatLng(latLng);
                    user.setfName(fName.getText().toString());
                    user.setlName(lName.getText().toString());
                    user.setUsername(username.getText().toString());
                    user.setPassword(password.getText().toString());

                    String text = "";

                    switch (state){
                        case CREATE:
                            text = AppRepository.Instance(getContext()).addUser(user);
                            break;
                        case EDIT:
                            text = AppRepository.Instance(getContext()).updateUser(user);
                            break;
                    }

                    Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                    popStack();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Delete User " + user.getTitle())
                        .setMessage("Are you sure you want to delete this user?")
                        .setPositiveButton("Yes", (dialogInterface, i) -> {
                            String text = AppRepository.Instance(getContext()).deleteUser(user);
                            Toast.makeText(getActivity(), text , Toast.LENGTH_SHORT).show();
                            popStack();
                        })
                        .setNegativeButton("No", (dialogInterface, i) -> {
                            dialogInterface.dismiss();
                        })
                        .show();
            }
        });
    }
}