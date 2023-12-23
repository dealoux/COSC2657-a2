package ducle.greenapp.database.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;

import ducle.greenapp.database.models.utils.ModelUtils;

@Entity
public abstract class MyEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    protected String id;

    @ColumnInfo(name = "latLng")
    protected LatLng latLng;

    public MyEntity(LatLng latLng) {
        setLatLng(latLng);
    }

    public MyEntity(double latitude, double longitude) {
        setLatLng(latitude, longitude);
    }

    public MyEntity(String latitude, String longitude) {
        setLatLng(latitude, longitude);
    }

    public MyEntity(String location) {
        setLatLng(location);
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public void setLatLng(double latitude, double longitude) {
        this.latLng = new LatLng(latitude, longitude);
    }

    public void setLatLng(String latitude, String longitude) {
        setLatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
    }

    public void setLatLng(String locationString) {
        String[] location = ModelUtils.splitTrimLine(locationString, ",");
        setLatLng(location[0], location[1]);
    }
}