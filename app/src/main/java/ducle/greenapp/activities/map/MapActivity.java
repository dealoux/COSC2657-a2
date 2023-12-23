package ducle.greenapp.activities.map;

import android.os.Bundle;

import ducle.greenapp.R;
import ducle.greenapp.activities.MyActivity;

public class MapActivity extends MyActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentFl, new MapsSiteViewFragment())
                .commit();
    }
}