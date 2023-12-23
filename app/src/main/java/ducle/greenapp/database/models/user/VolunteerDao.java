package ducle.greenapp.database.models.user;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import ducle.greenapp.database.models.MyEntityDao;

@Dao
public interface VolunteerDao extends MyEntityDao<Volunteer> {
    @Query("SELECT COUNT(id) FROM Volunteer")
    int getCount();

    @Query("SELECT * FROM Volunteer")
    List<Volunteer> getList();

    @Query("SELECT * FROM Volunteer WHERE id = :id")
    Volunteer get(String id);

    @Query("SELECT * FROM Volunteer WHERE username = :username")
    Volunteer getByUsername(String username);

    @Query("SELECT * FROM Volunteer WHERE username = :username AND password = :password")
    Volunteer get(String username, String password);
}
