package ducle.greenapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

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

@TypeConverters(Converters.class)
@Database(entities = {
        CleanUpSite.class,
        Volunteer.class,
        Admin.class,
        VolunteerSiteCrossRef.class,
}, version = 1)
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
     * @param ownerId
     */
    public CleanUpSite nextSite(LatLng latLng, String ownerId){
        return new CleanUpSite(String.valueOf(getCleanUpSiteDao().getCount() + 1), latLng, ownerId);
    }

    /**
     * Add a new site object to the database
     * @param site
     */
    public String addSite(CleanUpSite site){
        getCleanUpSiteDao().insert(site);
        getVolunteerSiteDao().insert(site.getOwnerId(), site.getId());
        return "Added " + site.getTitle();
    }

    public String deleteSite(CleanUpSite site){
        getCleanUpSiteDao().delete(site);
        getVolunteerSiteDao().deleteBySiteId(site.getId());
        return "Deleted " + site.getTitle();
    }

    public List<CleanUpSite> getAllSites(){
        return getCleanUpSiteDao().getList();
    }

    /**
     * Return a new volunteer object with the next available id
     * @param username
     * @param password
     */
    public Volunteer nextVolunteer(String username, String password){
        return new Volunteer(String.valueOf(getVolunteerDao().getCount() + 1), username, password);
    }

    /**
     * Return a new admin object with the next available id
     * @param username
     * @param password
     */
    public Admin nextAdmin(String username, String password){
        return new Admin(String.valueOf(getAdminDao().getCount() + 1), username, password);
    }

    /**
     * Add a new user object to the database
     * @param user
     */
    public String addUser(User user){
        if(user instanceof Volunteer){
            getVolunteerDao().insert((Volunteer) user);
        }
        else if(user instanceof Admin){
            getAdminDao().insert((Admin) user);
        }

        return "Added " + user.getTitle();
    }

    /**
     * Return the user with the given id
     * @param userId
     * @return the user with the given id
     */
    public User getUser(String userId){
        User user = getVolunteerDao().get(userId);

        if(user == null) {
            user = getAdminDao().get(userId);
        }

        return user;
    }

    /**
     * Update the user in the database
     * @param user
     */
    public String updateUser(User user){
        if(user instanceof Volunteer){
            getVolunteerDao().update((Volunteer) user);
        }
        else if(user instanceof Admin){
            getAdminDao().update((Admin) user);
        }

        return "Updated " + user.getTitle();
    }

    /**
     * Delete the user from the database
     * @param user
     */
    public String deleteUser(User user){
        if(user instanceof Volunteer){
            getVolunteerDao().delete((Volunteer) user);
            getVolunteerSiteDao().deleteByUserId(user.getId());
        }
        else if(user instanceof Admin){
            getAdminDao().delete((Admin) user);
        }

        return "Deleted " + user.getTitle();
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

    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<>();

        userList.addAll(getVolunteerDao().getList());
        userList.addAll(getAdminDao().getList());

        return userList;
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
