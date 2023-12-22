package ducle.greenapp.activities.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import ducle.greenapp.R;

public class MapsRegisterLocationFragment extends BaseMapsFragment {
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
        getActivity().setTitle("Choose Your Location");
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        updateLatLng(latLng);
        myMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("User Marker")
                .snippet(latLng.toString()));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        Toast.makeText(getActivity(), "Click on the marker to view more info and proceed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());

        myMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Current Location")
                .snippet(latLng.toString()));

        Toast.makeText(getActivity(), "Click on the marker to view more info and proceed", Toast.LENGTH_SHORT).show();
        return super.onMyLocationButtonClick();
    }


    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {
        currentLocation.setLatitude(marker.getPosition().latitude);
        currentLocation.setLongitude(marker.getPosition().longitude);
        popStack();
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Toast.makeText(getActivity(), "Click on the info window to confirm your location", Toast.LENGTH_SHORT).show();
        return super.onMarkerClick(marker);
    }

    @Override
    protected void popStack() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("latLng", new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()));
        getParentFragmentManager().setFragmentResult("location", bundle);
        super.popStack();
    }
}