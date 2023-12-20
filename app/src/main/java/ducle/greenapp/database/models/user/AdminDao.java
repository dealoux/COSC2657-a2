package ducle.greenapp.database.models.user;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import ducle.greenapp.database.models.MyEntityDao;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface AdminDao extends MyEntityDao<Admin> {
//    @Query("SELECT * FROM Admin")
//    Flowable<List<Admin>> getAdminList();
//
//    @Query("SELECT * FROM Admin WHERE id = :id")
//    Flowable<Admin> getAdmin(String id);
//
//    @Query("SELECT * FROM Admin WHERE username = :username AND password = :password")
//    Flowable<ArrayList<Admin>> getAdmin(String username, String password);

    @Query("SELECT * FROM Admin")
    List<Admin> getAdminList();

    @Query("SELECT * FROM Admin WHERE user_id = :id")
    Admin getAdmin(String id);

    @Query("SELECT * FROM Admin WHERE username = :username AND password = :password")
    Admin getAdmin(String username, String password);
}
