package ducle.greenapp.activities.site;

import android.os.Bundle;

import ducle.greenapp.R;
import ducle.greenapp.activities.MyDatePickerActivity;

public class SiteActivity extends MyDatePickerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentFl, new SiteBrowseFragment())
                .addToBackStack(null)
                .commit();
    }
}