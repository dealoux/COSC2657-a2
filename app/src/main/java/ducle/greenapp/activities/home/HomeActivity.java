package ducle.greenapp.activities.home;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ducle.greenapp.R;
import ducle.greenapp.activities.utils.MyActivity;
import ducle.greenapp.activities.map.MapActivity;
import ducle.greenapp.activities.site.SiteActivity;

public class HomeActivity extends MyActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();

        String userId = (String) intent.getExtras().get("userId");
        String username = (String) intent.getExtras().get("username");

        setTitle("Welcome " + username);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK){
                        Intent data = result.getData();
                    }
                });

        Button buttonHomeOpenMap = (Button) findViewById(R.id.buttonHomeOpenMap);
        buttonHomeOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HomeActivity.this, MapActivity.class);
                intent1.putExtras(intent);
                launcher.launch(intent1);
            }
        });

        Button buttonHomeManageSite = (Button) findViewById(R.id.buttonHomeManageSite);
        buttonHomeManageSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HomeActivity.this, SiteActivity.class);
                intent1.putExtras(intent);
                launcher.launch(intent1);
            }
        });

        Button buttonHomeManageUser = (Button) findViewById(R.id.buttonHomeManageUser);

//        buttonHomeManageUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent1 = new Intent(HomeActivity.this, ManageUserActivity.class);
//                intent1.putExtras(intent);
//                launcher.launch(intent1);
//            }
//        });

        if(!userId.startsWith("ADM")){
            buttonHomeManageUser.setVisibility(View.GONE);
        }
    }
}