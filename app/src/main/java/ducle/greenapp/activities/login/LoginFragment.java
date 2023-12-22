package ducle.greenapp.activities.login;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ducle.greenapp.AppRepository;
import ducle.greenapp.activities.utils.MyFragment;
import ducle.greenapp.activities.home.HomeActivity;
import ducle.greenapp.R;
import ducle.greenapp.database.models.user.User;

public class LoginFragment extends MyFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Login");

        EditText username = (EditText) view.findViewById(R.id.usernameLogin);
        EditText password = (EditText) view.findViewById(R.id.passwordLogin);
        Button buttonLogin = (Button) view.findViewById(R.id.buttonLogin);
        Button buttonRegister = (Button) view.findViewById(R.id.buttonLoginRegister);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK){
                        Intent data = result.getData();
                        Toast.makeText(getActivity(), (String) data.getExtras().get("response"), Toast.LENGTH_SHORT).show();
                    }
                });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = AppRepository.Instance(getContext()).validateUser(username.getText().toString(), password.getText().toString());

                if(user != null){
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    intent.putExtra("userId", user.getId());
                    intent.putExtra("username", user.getUsername());

                    launcher.launch(intent);
                } else {
                    Toast.makeText(getActivity(), "Incorrect credentials, please try again", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentFl, new RegisterFragment());
                transaction.addToBackStack(null);
                transaction.commit();

                Toast.makeText(getActivity(), "Double click on Location to open the map", Toast.LENGTH_LONG).show();
            }
        });
    }
}