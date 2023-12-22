package ducle.greenapp.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ducle.greenapp.AppRepository;
import ducle.greenapp.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout);

        AppRepository.Instance(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentFl, new LoginFragment())
                .addToBackStack(null)
                .commit();
    }
}