package ducle.greenapp.activities.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.Task;

import ducle.greenapp.R;
import ducle.greenapp.activities.utils.MyFragment;

public class BaseMapsFragment extends MyFragment implements GoogleMap.OnMyLocationChangeListener, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {
    protected static final int FINE_PERMISSION_REQUEST = 1;
    protected GoogleMap myMap;

    protected Location currentLocation;
    protected FusedLocationProviderClient fusedLocationProviderClient;

    protected GoogleMap.OnMarkerClickListener onMarkerClickListener = marker -> false;

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

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        fetchLocation();
    }

    protected OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            myMap = googleMap;

            myMap.setOnMarkerClickListener(onMarkerClickListener);

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]
                        {android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_REQUEST);
                return;
            }

            myMap.setMyLocationEnabled(true);
            myMap.setOnMyLocationButtonClickListener(BaseMapsFragment.this);
            myMap.setOnMyLocationClickListener(BaseMapsFragment.this);

            myMap.getUiSettings().setZoomControlsEnabled(true);
            myMap.getUiSettings().setMyLocationButtonEnabled(true);
            myMap.getUiSettings().setCompassEnabled(true);

            handleMapsCallback();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if((requestCode == FINE_PERMISSION_REQUEST) && (grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
            fetchLocation();
        }
        else{
            Toast.makeText(getActivity(), "Permission denied, please allow permission to access location", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMyLocationChange(@NonNull Location location) {
        currentLocation = location;
    }

    @Override
    public boolean onMyLocationButtonClick() {
        fetchLocation();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
    }

    protected void fetchLocation() {
        if ((ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(getActivity(), new String[]
                    {android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_REQUEST);
            return;
        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                currentLocation = location;

                SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                if (mapFragment != null) {
                    mapFragment.getMapAsync(callback);
                }
            }
        });
    }

    protected void updateLatLng(LatLng latLng){
        currentLocation.setLatitude(latLng.latitude);
        currentLocation.setLongitude(latLng.longitude);
    }

    protected void handleMapsCallback(){
        if(currentLocation != null){
            LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            myMap.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLng(latLng));
            myMap.animateCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(latLng, 15));
        }
    }
}