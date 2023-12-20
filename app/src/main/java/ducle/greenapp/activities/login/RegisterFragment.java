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
import androidx.fragment.app.Fragment;

import ducle.greenapp.AppRepository;
import ducle.greenapp.R;
import ducle.greenapp.activities.utils.ActivityUtils;

public class RegisterFragment extends Fragment {

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
        EditText dob = (EditText) view.findViewById(R.id.dobRegister);
        EditText phone = (EditText) view.findViewById(R.id.phoneRegister);
        EditText address = (EditText) view.findViewById(R.id.addressRegister);
        EditText username = (EditText) view.findViewById(R.id.usernameRegister);
        EditText password = (EditText) view.findViewById(R.id.passwordRegister);
        Button buttonConfirm = (Button) view.findViewById(R.id.buttonRegisterConfirm);
        Button buttonCancel = (Button) view.findViewById(R.id.buttonRegisterCancel);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.datePickerDialog(getActivity().getSupportFragmentManager());
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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