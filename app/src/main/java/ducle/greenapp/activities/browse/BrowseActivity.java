package ducle.greenapp.activities.browse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import ducle.greenapp.R;

public class BrowseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Fragment browseFragment = new CentreBrowseFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentFl, browseFragment);
        transaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}