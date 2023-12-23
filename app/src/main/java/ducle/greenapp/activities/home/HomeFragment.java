package ducle.greenapp.activities.home;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import ducle.greenapp.R;
import ducle.greenapp.activities.map.MapActivity;
import ducle.greenapp.activities.site.SiteActivity;
import ducle.greenapp.activities.user.UserActivity;
import ducle.greenapp.activities.user.UserEditFragment;
import ducle.greenapp.activities.utils.MyFragment;

public class HomeFragment extends MyFragment implements ActivityCompat.OnRequestPermissionsResultCallback{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent intent = getActivity().getIntent();

        String userId = (String) intent.getExtras().get("userId");
        String username = (String) intent.getExtras().get("username");

        getActivity().setTitle("Welcome " + username);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK){
                        Intent data = result.getData();
                    }
                });

        Button buttonProfile = (Button) getActivity().findViewById(R.id.buttonProfile);
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("userId", userId);

                UserEditFragment userEditFragment = new UserEditFragment();
                userEditFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentFl, userEditFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button buttonHomeOpenMap = (Button) getActivity().findViewById(R.id.buttonHomeOpenMap);
        buttonHomeOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getContext(), MapActivity.class);
                intent1.putExtras(intent);
                launcher.launch(intent1);
            }
        });

        Button buttonHomeManageSite = (Button) getActivity().findViewById(R.id.buttonHomeManageSite);
        buttonHomeManageSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getContext(), SiteActivity.class);
                intent1.putExtras(intent);
                launcher.launch(intent1);
            }
        });

        Button buttonHomeManageUser = (Button) getActivity().findViewById(R.id.buttonHomeManageUser);

        buttonHomeManageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getContext(), UserActivity.class);
                intent1.putExtras(intent);
                launcher.launch(intent1);
            }
        });

        if(userId.startsWith("ADM")){
            buttonHomeManageUser.setVisibility(View.VISIBLE);
        }
    }
}