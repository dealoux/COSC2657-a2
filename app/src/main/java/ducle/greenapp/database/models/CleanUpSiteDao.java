package ducle.greenapp.database.models;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;


@Dao
public interface CleanUpSiteDao extends MyEntityDao<CleanUpSite> {
    @Query("SELECT COUNT(site_id) FROM CleanUpSite")
    int getCount();

    @Query("SELECT * FROM CleanUpSite")
    List<CleanUpSite> getList();

    @Query("SELECT * FROM CleanUpSite WHERE site_id = :id")
    CleanUpSite get(String id);

    @Query("SELECT * FROM CleanUpSite WHERE owner_id = :ownerId")
    List<CleanUpSite> getByOwnerList(String ownerId);
}
