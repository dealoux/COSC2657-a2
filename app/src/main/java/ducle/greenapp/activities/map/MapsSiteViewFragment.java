package ducle.greenapp.activities.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.CameraUpdateFactory;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import ducle.greenapp.AppRepository;
import ducle.greenapp.R;
import ducle.greenapp.database.models.CleanUpSite;

public class MapsSiteViewFragment extends BaseMapsFragment {
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

        getActivity().setTitle("Choose a site or create a new one");
    }

    @Override
    protected void handleMapsCallback() {
        super.handleMapsCallback();

        List<CleanUpSite> sites = AppRepository.Instance(getContext()).getCleanUpSiteDao().getList();

        for(CleanUpSite site : sites) {
            myMap.addMarker(new MarkerOptions().position(site.getLatLng()).title(site.getName()));
        }
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
        myMap.addMarker(new MarkerOptions().position(latLng).title("Clean Up Site"));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}