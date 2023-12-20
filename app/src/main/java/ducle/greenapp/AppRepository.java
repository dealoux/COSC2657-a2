package ducle.greenapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import ducle.greenapp.database.models.CleanUpSite;
import ducle.greenapp.database.models.CleanUpSiteDao;
import ducle.greenapp.database.models.relation.VolunteerSiteCrossRef;
import ducle.greenapp.database.models.relation.VolunteerSiteDao;
import ducle.greenapp.database.models.user.Admin;
import ducle.greenapp.database.models.user.AdminDao;
import ducle.greenapp.database.models.user.User;
import ducle.greenapp.database.models.user.Volunteer;
import ducle.greenapp.database.models.user.VolunteerDao;
import io.reactivex.rxjava3.core.Flowable;

@Database(entities = {CleanUpSite.class, Volunteer.class, Admin.class, VolunteerSiteCrossRef.class}, version = 3)
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
        return instance;
    }

    public AppRepository(){}; // Prevent direct instantiation.

    private static AppRepository create(final Context context) {
        return Room.databaseBuilder(
                context,
                AppRepository.class,
                DB_NAME).build();
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

    public User validateUser(String username, String password) {
        User user = getVolunteerDao().getVolunteer(username, password);

        if(user == null) {
            user = getAdminDao().getAdmin(username, password);
        }

        return user;
    }
}
