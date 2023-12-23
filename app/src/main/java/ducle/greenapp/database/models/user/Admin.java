package ducle.greenapp.database.models.user;

import static ducle.greenapp.database.models.utils.ModelUtils.prefixId;

import androidx.room.Entity;

import com.google.android.gms.maps.model.LatLng;

@Entity(tableName = "Admin")
public class Admin extends User {
    public Admin(String id, LatLng latLng, String fName, String lName, String username, String password) {
        super(prefixId(id,"ADM"), latLng, fName, lName, username, password);
    }
    public Admin(String id, double latitude, double longitude, String fName, String lName, String username, String password) {
        super(prefixId(id,"ADM"), latitude, longitude, fName, lName, username, password);
    }
    public Admin(String id, String latitude, String longitude, String fName, String lName, String username, String password) {
        super(prefixId(id,"ADM"), latitude, longitude, fName, lName, username, password);
    }

    public Admin(String id, String location, String fName, String lName, String username, String password) {
        super(prefixId(id,"ADM"), location, fName, lName, username, password);
    }
}
