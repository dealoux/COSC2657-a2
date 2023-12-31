package ducle.greenapp.activities.home;

import android.os.Bundle;

import ducle.greenapp.AppRepository;
import ducle.greenapp.R;
import ducle.greenapp.activities.utils.MyActivity;

public class HomeActivity extends MyActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout);

        AppRepository.Instance(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentFl, new HomeFragment())
                .commit();
    }
}