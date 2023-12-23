package ducle.greenapp.activities.site;

import android.os.Bundle;

import ducle.greenapp.R;
import ducle.greenapp.activities.utils.MyDatePickerActivity;

public class CreateSiteActivity extends MyDatePickerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentFl, new SiteEditFragment())
                .commit();
    }
}