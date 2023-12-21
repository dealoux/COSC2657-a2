package ducle.greenapp.database.models;

import static ducle.greenapp.database.models.utils.ModelUtils.prefixId;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;

@Entity
public class CleanUpSite extends MyEntity implements Comparable<CleanUpSite>{
    @PrimaryKey
    @ColumnInfo(name = "site_id")
    @NonNull
    protected String id;
    @ColumnInfo(name = "owner_id")
    private String ownerId;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "time")
    private String time;
    @ColumnInfo(name = "collected_amount")
    private String collectedAmount;

    public CleanUpSite(String id, LatLng latLng, String ownerId, String name, String date, String time, String collectedAmount) {
        super(latLng);
        initData(id, ownerId, name, date, time, collectedAmount);
    }

    public CleanUpSite(String id, double latitude, double longitude, String ownerId, String name, String date, String time) {
        super(latitude, longitude);
        initData(id, ownerId, name, date, time, "");
    }

    public CleanUpSite(String id, String latitude, String longitude, String ownerId, String name, String date, String time) {
        super(latitude, longitude);
        initData(id, ownerId, name, date, time, "");
    }

    public CleanUpSite(String id, String location, String ownerId, String name, String date, String time) {
        super(location);
        initData(id, ownerId, name, date, time, "");
    }

    private void initData(String id, String ownerId, String name, String date, String time, String collectedAmount){
        setId(id);
        this.ownerId = ownerId;
        this.name = name;
        this.date = date;
        this.time = time;
        this.collectedAmount = collectedAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = prefixId(this.id, "SITE");
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(String collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    @Override
    public int compareTo(CleanUpSite site) {
        return getId().compareTo(site.getId());
    }

    @NonNull
    @Override
    public String toString(){
        return "CleanUpSite " + super.toString() + ", name: " + name + ", date: " + date + " " + time + ", waste collected: " + collectedAmount;
    }
}
