package ducle.greenapp.database.models.user;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import ducle.greenapp.database.models.MyEntityDao;

@Dao
public interface AdminDao extends MyEntityDao<Admin> {
    @Query("SELECT COUNT(user_id) FROM Admin")
    int getCount();

    @Query("SELECT * FROM Admin")
    List<Admin> getList();

    @Query("SELECT * FROM Admin WHERE user_id = :id")
    Admin get(String id);

    @Query("SELECT * FROM Admin WHERE username = :username")
    Volunteer getByUsername(String username);

    @Query("SELECT * FROM Admin WHERE username = :username AND password = :password")
    Admin get(String username, String password);
}
