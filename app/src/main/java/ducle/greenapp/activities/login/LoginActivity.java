package ducle.greenapp.activities.login;

import android.os.Bundle;

import ducle.greenapp.AppRepository;
import ducle.greenapp.R;
import ducle.greenapp.activities.utils.MyActivity;

public class LoginActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout);

        AppRepository.Instance(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentFl, new LoginFragment())
                .commit();
    }
}