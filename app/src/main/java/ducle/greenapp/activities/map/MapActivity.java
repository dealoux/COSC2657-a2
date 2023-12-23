package ducle.greenapp.activities.map;

import android.os.Bundle;

import ducle.greenapp.R;
import ducle.greenapp.activities.utils.MyActivity;

public class MapActivity extends MyActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentFl, new MapsSiteViewFragment())
                .commit();
    }
}