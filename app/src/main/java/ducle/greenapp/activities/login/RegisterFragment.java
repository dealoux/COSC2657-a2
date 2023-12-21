package ducle.greenapp.activities.login;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import ducle.greenapp.R;
import ducle.greenapp.activities.map.MapsRegisterLocationFragment;

public class RegisterFragment extends Fragment {
    ActivityResultLauncher<Intent> launcher;
    Intent data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Register");

        EditText fName = (EditText) view.findViewById(R.id.fNameRegister);
        EditText lName = (EditText) view.findViewById(R.id.lNameRegister);
        EditText location = (EditText) view.findViewById(R.id.locationRegister);
        EditText username = (EditText) view.findViewById(R.id.usernameRegister);
        EditText password = (EditText) view.findViewById(R.id.passwordRegister);
        Button buttonConfirm = (Button) view.findViewById(R.id.buttonRegisterConfirm);
        Button buttonCancel = (Button) view.findViewById(R.id.buttonRegisterCancel);

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
                    return;
                }
//                String text = AppRepository.Instance().getUserManager().addCustomer(
//                        fName.getText().toString(),
//                        lName.getText().toString(),
//                        address.getText().toString(),
//                        phone.getText().toString(),
//                        dob.getText().toString(),
//                        username.getText().toString(),
//                        password.getText().toString()
//                );
//
//                Toast.makeText(getActivity(), "Registered User " + text, Toast.LENGTH_SHORT).show();

                popStack();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popStack();
            }
        });
    }

    private void popStack(){
        getParentFragmentManager().popBackStack();
    }
}