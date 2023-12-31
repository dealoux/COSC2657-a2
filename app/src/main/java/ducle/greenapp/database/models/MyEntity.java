package ducle.greenapp.database.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;

import ducle.greenapp.database.models.utils.ModelUtils;

@Entity
public class MyEntity implements Comparable<MyEntity> {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    protected String id;

    @ColumnInfo(name = "latLng")
    protected LatLng latLng;

    public MyEntity(String id){
        setId(id);
        setLatLng(0, 0);
    }

    public MyEntity(String id, LatLng latLng) {
        setId(id);
        setLatLng(latLng);
    }

    public MyEntity(String id, double latitude, double longitude) {
        setId(id);
        setLatLng(latitude, longitude);
    }

    public MyEntity(String id, String latitude, String longitude) {
        setId(id);
        setLatLng(latitude, longitude);
    }

    public MyEntity(String id, String location) {
        setId(id);
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

    @Override
    public int compareTo(MyEntity entity) {
        return getId().compareTo(entity.getId());
    }

    @NonNull
    @Override
    public String toString(){
        return getClass().getSimpleName() + " " + getId() + "\n" + getLatLng().toString();
    }
}