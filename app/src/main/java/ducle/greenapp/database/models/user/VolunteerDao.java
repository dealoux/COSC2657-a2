package ducle.greenapp.database.models.user;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import ducle.greenapp.database.models.MyEntityDao;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface VolunteerDao extends MyEntityDao<Volunteer> {
//    @Query("SELECT * FROM Volunteer")
//    Flowable<List<Volunteer>> getVolunteerList();
//
//    @Query("SELECT * FROM Volunteer WHERE username = :username AND password = :password")
//    Flowable<Volunteer> getVolunteer(String username, String password);
//
//    @Query("SELECT * FROM Volunteer WHERE id = :id")
//    Flowable<Volunteer> getVolunteer(String id);

    @Query("SELECT * FROM Volunteer")
    List<Volunteer> getVolunteerList();

    @Query("SELECT * FROM Volunteer WHERE username = :username AND password = :password")
    Volunteer getVolunteer(String username, String password);

    @Query("SELECT * FROM Volunteer WHERE user_id = :id")
    Volunteer getVolunteer(String id);
}
