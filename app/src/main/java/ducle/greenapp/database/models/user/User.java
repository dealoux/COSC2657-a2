package ducle.greenapp.database.models.user;

import ducle.greenapp.database.models.MyEntity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User extends MyEntity implements Comparable<User> {
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    @NonNull
    protected String id;
    @ColumnInfo(name = "first name")
    protected String fName;
    @ColumnInfo(name = "last name")
    protected String lName;
    @ColumnInfo(name = "username")
    protected String username;
    @ColumnInfo(name = "password")
    protected String password;

    public User(String id, double latitude, double longitude, String fName, String lName, String username, String password) {
        super(latitude, longitude);
        initData(id, fName, lName, username, password);
    }

    public User(String id, String latitude, String longitude, String fName, String lName, String username, String password) {
        super(latitude, longitude);
        initData(id, fName, lName, username, password);
    }

    public User(String id, String location, String fName, String lName, String username, String password) {
        super(location);
        initData(id, fName, lName, username, password);
    }

    private void initData(String id, String fName, String lName, String username, String password){
        setId(id);
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @NonNull
    @Override
    public String toString() {
        return id + " " + fName + " " + lName + "\n"
                + "Location: " + getLocation() + "\n";
    }

    @Override
    public int compareTo(User user) {
        return getId().compareTo(user.getId());
    }
}
