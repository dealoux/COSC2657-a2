package ducle.greenapp.activities.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.model.LatLng;

import ducle.greenapp.AppRepository;
import ducle.greenapp.R;
import ducle.greenapp.activities.utils.MyFragment;
import ducle.greenapp.activities.map.MapsRegisterLocationFragment;
import ducle.greenapp.database.models.user.Volunteer;

public class RegisterFragment extends MyFragment {
    private LatLng latLng;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_volunteer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Register");

        EditText userId = (EditText) view.findViewById(R.id.userId);
        EditText userType = (EditText) view.findViewById(R.id.userType);
        EditText fName = (EditText) view.findViewById(R.id.fName);
        EditText lName = (EditText) view.findViewById(R.id.lName);
        EditText location = (EditText) view.findViewById(R.id.location);
        EditText username = (EditText) view.findViewById(R.id.username);
        EditText password = (EditText) view.findViewById(R.id.password);
        Button buttonConfirm = (Button) view.findViewById(R.id.buttonConfirm);


        Volunteer volunteer = AppRepository.Instance(getContext()).nextVolunteer();

        Bundle bundle = getArguments();

        userId.setText(volunteer.getId());
        userType.setText("Volunteer");
        username.setText(bundle.getString("username"));
        password.setText(bundle.getString("password"));

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentFl, new MapsRegisterLocationFragment());
                transaction.addToBackStack(null);
                transaction.commit();
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
                    if(!AppRepository.Instance(getContext()).validateUsername(username.getText().toString())) {
                        Toast.makeText(getActivity(), "Username already exists", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    volunteer.setLatLng(latLng);
                    volunteer.setfName(fName.getText().toString());
                    volunteer.setlName(lName.getText().toString());
                    volunteer.setUsername(username.getText().toString());
                    volunteer.setPassword(password.getText().toString());

                    String text = AppRepository.Instance(getContext()).addVolunteer(volunteer);
                    Toast.makeText(getActivity(), "Registered User " + text, Toast.LENGTH_SHORT).show();
                    popStack();
                }
            }
        });
    }
}