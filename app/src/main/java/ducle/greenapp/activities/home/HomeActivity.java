package ducle.greenapp.activities.home;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ducle.greenapp.R;
import ducle.greenapp.activities.map.MapsActivity;
import ducle.greenapp.activities.site.ManageSiteActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        String userId = (String) intent.getExtras().get("userId");
        String userFname = (String) intent.getExtras().get("userFname");

        setTitle("Welcome " + userFname);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK){
                        Intent data = result.getData();
                    }
                });

        Button buttonHomeBrowse = (Button) findViewById(R.id.buttonHomeBrowse);
        buttonHomeBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HomeActivity.this, MapsActivity.class);
                intent1.putExtras(intent);
                launcher.launch(intent1);
            }
        });

        Button buttonHomeReservations = (Button) findViewById(R.id.buttonHomeReservation);
        buttonHomeReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HomeActivity.this, ManageSiteActivity.class);
                intent1.putExtras(intent);
                launcher.launch(intent1);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}