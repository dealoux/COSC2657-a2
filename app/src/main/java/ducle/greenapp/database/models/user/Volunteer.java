package ducle.greenapp.database.models.user;

import static ducle.greenapp.database.models.utils.ModelUtils.prefixId;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "Volunteer")
public class Volunteer extends User {
    public Volunteer(String id, double latitude, double longitude, String fName, String lName, String username, String password) {
        super(prefixId(id,"VOL"), latitude, longitude, fName, lName, username, password);

    }

    public Volunteer(String id, String latitude, String longitude, String fName, String lName, String username, String password) {
        super(prefixId(id,"VOL"), latitude, longitude, fName, lName, username, password);
    }

    public Volunteer(String id, String location, String fName, String lName, String username, String password) {
        super(prefixId(id,"VOL"), location, fName, lName, username, password);
    }

    @NonNull
    @Override
    public String toString(){
        return "Customer " + super.toString();
    }
}
