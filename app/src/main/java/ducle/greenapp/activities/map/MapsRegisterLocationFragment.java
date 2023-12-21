package ducle.greenapp.activities.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ducle.greenapp.R;

public class MapsRegisterLocationFragment extends BaseMapsFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps_locationbutton, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Choose Your Location");

        Button useLocationButton = view.findViewById(R.id.uselocation_button);
        Button confirmLocationButton = view.findViewById(R.id.confirm_button);

        useLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("latLng", new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()));
                getParentFragmentManager().setFragmentResult("location", bundle);
                popStack();
            }
        });

        confirmLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();

                if(customLocation == null) {
                    bundle.putParcelable("latLng", new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()));
                } else {
                    bundle.putParcelable("latLng", customLocation);
                }
                getParentFragmentManager().setFragmentResult("location", bundle);
                popStack();
            }
        });
    }

    @Override
    protected void handleMapsCallback() {
        super.handleMapsCallback();

        myMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){

            @Override
            public void onMapClick(LatLng latLng) {
                customLocation = latLng;
                myMap.clear();
                myMap.addMarker(new MarkerOptions().position(customLocation).title("User Location"));
                myMap.moveCamera(CameraUpdateFactory.newLatLng(customLocation));
            }
        });
    }
}