package ducle.greenapp.activities.user;

import android.os.Bundle;

import ducle.greenapp.R;
import ducle.greenapp.activities.utils.MyDatePickerActivity;

public class UserActivity extends MyDatePickerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentFl, new UserBrowseFragment())
                .commit();
    }
}