package ducle.greenapp.database.models.user;

import static ducle.greenapp.database.models.utils.ModelUtils.prefixId;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.android.gms.maps.model.LatLng;

@Entity(tableName = "Volunteer")
public class Volunteer extends User {
    @Ignore
    public Volunteer(String id) {
        super(id);
    }

    public Volunteer(String id, LatLng latLng, String fName, String lName, String username, String password) {
        super(id, latLng, fName, lName, username, password);
    }

    public Volunteer(String id, double latitude, double longitude, String fName, String lName, String username, String password) {
        super(id, latitude, longitude, fName, lName, username, password);
    }

    public Volunteer(String id, String latitude, String longitude, String fName, String lName, String username, String password) {
        super(id, latitude, longitude, fName, lName, username, password);
    }

    public Volunteer(String id, String location, String fName, String lName, String username, String password) {
        super(id, location, fName, lName, username, password);
    }

    @Override
    public void setId(@NonNull String id) {
        this.id = prefixId(id, "VOL");
    }
}