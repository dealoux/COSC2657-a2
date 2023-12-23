package ducle.greenapp.database.models.user;

import ducle.greenapp.database.models.MyEntity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.google.android.gms.maps.model.LatLng;

@Entity(tableName = "User")
public class User extends MyEntity {
    @ColumnInfo(name = "first_name")
    protected String fName;
    @ColumnInfo(name = "last_name")
    protected String lName;
    @ColumnInfo(name = "username")
    protected String username;
    @ColumnInfo(name = "password")
    protected String password;

    public User(String id, String username, String password){
        super(id);
        initData("", "", username, password);
    }

    public User(String id, LatLng latLng, String fName, String lName, String username, String password) {
        super(id, latLng);
        initData(fName, lName, username, password);
    }

    public User(String id, double latitude, double longitude, String fName, String lName, String username, String password) {
        super(id, latitude, longitude);
        initData(fName, lName, username, password);
    }

    public User(String id, String latitude, String longitude, String fName, String lName, String username, String password) {
        super(latitude, longitude);
        initData(fName, lName, username, password);
    }

    public User(String id, String location, String fName, String lName, String username, String password) {
        super(location);
        initData(fName, lName, username, password);
    }

    private void initData(String fName, String lName, String username, String password){
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return id + " " + fName + " " + lName;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString() + "\n" + fName + " " + lName;
    }
}
