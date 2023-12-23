package ducle.greenapp.activities.map;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.CameraUpdateFactory;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import ducle.greenapp.AppRepository;
import ducle.greenapp.R;
import ducle.greenapp.activities.site.CreateSiteActivity;
import ducle.greenapp.database.models.CleanUpSite;

public class MapsSiteViewFragment extends BaseMapsFragment {
    private boolean isAdmin = false;
    ActivityResultLauncher<Intent> launcher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Choose or create a site");

        isAdmin = getActivity().getIntent().getExtras().getString("userId").startsWith("ADM");

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK){
                        Intent data = result.getData();
                        Toast.makeText(getActivity(), (String) data.getExtras().get("response"), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshSites();
    }

    @Override
    protected void handleMapsCallback() {
        super.handleMapsCallback();
        refreshSites();
    }

    @Override
    protected void popStack() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("latLng", new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()));
        getParentFragmentManager().setFragmentResult("location", bundle);
        super.popStack();
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        updateLatLng(latLng);
        myMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Your Maker")
                .snippet(latLng.toString()));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        Toast.makeText(getActivity(), "Click on the marker to view more info and proceed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {
        currentLocation.setLatitude(marker.getPosition().latitude);
        currentLocation.setLongitude(marker.getPosition().longitude);

        Intent intent = getActivity().getIntent();
        Intent intent1 = new Intent(getActivity(), CreateSiteActivity.class);
        intent1.putExtras(intent);

        if(marker.getTitle().startsWith("SITE")){
            intent1.putExtra("siteId", marker.getTitle());
        }
        else{
            if(isAdmin){
                return;
            }

            intent1.putExtra("latLng", new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()));
        }

        launcher.launch(intent1);
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Toast.makeText(getActivity(), "Click on the info window to confirm the site", Toast.LENGTH_SHORT).show();
        return super.onMarkerClick(marker);
    }

    private void refreshSites(){
        if(myMap == null) return;

        myMap.clear();

        List<CleanUpSite> sites = AppRepository.Instance(getContext()).getCleanUpSiteDao().getList();

        for(CleanUpSite site : sites) {
            myMap.addMarker(new MarkerOptions()
                    .position(site.getLatLng())
                    .title(site.getId())
                    .snippet(site.getSnippet()));
        }
    }
}