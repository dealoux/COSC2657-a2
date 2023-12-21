package ducle.greenapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.google.android.gms.maps.model.LatLng;

import ducle.greenapp.database.converters.Converters;
import ducle.greenapp.database.models.CleanUpSite;
import ducle.greenapp.database.models.CleanUpSiteDao;
import ducle.greenapp.database.models.relation.VolunteerSiteCrossRef;
import ducle.greenapp.database.models.relation.VolunteerSiteDao;
import ducle.greenapp.database.models.user.Admin;
import ducle.greenapp.database.models.user.AdminDao;
import ducle.greenapp.database.models.user.User;
import ducle.greenapp.database.models.user.Volunteer;
import ducle.greenapp.database.models.user.VolunteerDao;

@Database(entities = {CleanUpSite.class, Volunteer.class, Admin.class, VolunteerSiteCrossRef.class}, version = 3)
@TypeConverters(Converters.class)
public abstract class AppRepository extends RoomDatabase {
    public abstract CleanUpSiteDao getCleanUpSiteDao();
    public abstract VolunteerDao getVolunteerDao();
    public abstract AdminDao getAdminDao();
    public abstract VolunteerSiteDao getVolunteerSiteDao();

    public static final String DB_NAME = "GreenApp.db";
    private static volatile AppRepository instance;

    public static synchronized AppRepository Instance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        instance.getOpenHelper().getWritableDatabase();
        return instance;
    }

    public AppRepository(){}; // Prevent direct instantiation.

    private static AppRepository create(final Context context) {
        return Room.databaseBuilder(context, AppRepository.class, DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    @Override
    public void clearAllTables() {

    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(@NonNull DatabaseConfiguration databaseConfiguration) {
        return null;
    }

    /**
     * Close the database and clear any cached data
     */
    public void destroyInstance() {

        if (instance.isOpen()) {
            instance.close();
        }

        instance = null;
    }

    /**
     * Return a new basic site object with the next available id
     * @param latLng
     * @param OwnerId
     */
    public CleanUpSite nextSite(LatLng latLng, String OwnerId){
        return new CleanUpSite(String.valueOf(getCleanUpSiteDao().getCount() + 1), latLng, OwnerId);
    }


    /**
     * Add a new site object to the database
     * @param site
     */
    public String addSite(CleanUpSite site){
        getCleanUpSiteDao().insert(site);
        return site.toString();
    }

    /**
     * Add a new volunteer object with the next available id to the database
     * @param latLng
     * @param fName
     * @param lName
     * @param username
     * @param password
     */
    public String addVolunteer(LatLng latLng, String fName, String lName, String username, String password){
        Volunteer volunteer = new Volunteer(String.valueOf(getVolunteerDao().getCount() + 1), latLng, fName, lName, username, password);
        getVolunteerDao().insert(volunteer);

        return volunteer.toString();
    }

    /**
     * Validate if the username is unique
     * @param username
     * @return true if the username is unique
     *        false otherwise
     */
    public boolean validateUsername(String username) {
        if(getVolunteerDao().getByUsername(username) == null) {
            return getAdminDao().getByUsername(username) == null;
        }

        return false;
    }

    /**
     * Validate if the username and password match a user in the database
     * @param username
     * @param password
     * @return the user if the username and password match a user in the database
     *        null otherwise
     */
    public User validateUser(String username, String password) {
        User user = getVolunteerDao().get(username, password);

        if(user == null) {
            user = getAdminDao().get(username, password);
        }

        return user;
    }
}
