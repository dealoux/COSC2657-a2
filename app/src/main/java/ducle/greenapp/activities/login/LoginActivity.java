package ducle.greenapp.activities.login;

import android.os.Bundle;

import ducle.greenapp.AppRepository;
import ducle.greenapp.R;
import ducle.greenapp.activities.utils.MyActivity;
import ducle.greenapp.database.models.user.Admin;

public class LoginActivity extends MyActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout);

//        AppRepository.Instance(this).getAdminDao().insert(new Admin("ADM_1", "admin", "admin"));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentFl, new LoginFragment())
                .commit();
    }
}