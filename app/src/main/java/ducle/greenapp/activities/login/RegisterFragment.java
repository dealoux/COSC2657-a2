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
import ducle.greenapp.activities.MyFragment;
import ducle.greenapp.activities.map.MapsRegisterLocationFragment;

public class RegisterFragment extends MyFragment {
    EditText fName;
    EditText lName;
    EditText location;
    EditText username;
    EditText password;
    Button buttonConfirm;
    Button buttonCancel;
    LatLng latLng;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Register");

        fName = (EditText) view.findViewById(R.id.fNameRegister);
        lName = (EditText) view.findViewById(R.id.lNameRegister);
        location = (EditText) view.findViewById(R.id.locationRegister);
        username = (EditText) view.findViewById(R.id.usernameRegister);
        password = (EditText) view.findViewById(R.id.passwordRegister);
        buttonConfirm = (Button) view.findViewById(R.id.buttonRegisterConfirm);
        buttonCancel = (Button) view.findViewById(R.id.buttonRegisterCancel);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentFl, new MapsRegisterLocationFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
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

                    String text = AppRepository.Instance(getContext()).addVolunteer(latLng, fName.getText().toString(), lName.getText().toString(), username.getText().toString(), password.getText().toString());
                    Toast.makeText(getActivity(), "Registered User " + text, Toast.LENGTH_SHORT).show();
                    popStack();
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popStack();
            }
        });

        getParentFragmentManager().setFragmentResultListener("location", this, (requestKey, result) -> {
            latLng = result.getParcelable("latLng");
            location.setText(latLng.toString());
        });
    }
}