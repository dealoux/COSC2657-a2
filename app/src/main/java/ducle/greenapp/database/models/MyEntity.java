package ducle.greenapp.database.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import ducle.greenapp.database.models.utils.ModelUtils;

@Entity
public abstract class MyEntity {
    @ColumnInfo(name = "latitude")
    protected double latitude;
    @ColumnInfo(name = "longitude")
    protected double longitude;

    public MyEntity(double latitude, double longitude) {
        setLocation(latitude, longitude);
    }

    public MyEntity(String latitude, String longitude) {
        setLocation(latitude, longitude);
    }

    public MyEntity(String location) {
        setLocation(location);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLocation() {
        return "latitude: " + latitude + ", longitude: " + longitude;
    }

    public void setLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setLocation(String latitude, String longitude) {
        setLocation(Double.parseDouble(latitude), Double.parseDouble(longitude));
    }

    public void setLocation(String locationString) {
        String[] location = ModelUtils.splitTrimLine(locationString);
        setLocation(location[0], location[1]);
    }
}