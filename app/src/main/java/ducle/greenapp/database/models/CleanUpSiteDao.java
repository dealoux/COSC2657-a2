package ducle.greenapp.database.models;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public abstract class CleanUpSiteDao implements MyEntityDao<CleanUpSite> {
    @Query("SELECT COUNT(id) FROM CleanUpSite")
    public abstract int getCount();

    @Query("SELECT * FROM CleanUpSite")
    public abstract List<CleanUpSite> getList();

    @Query("SELECT * FROM CleanUpSite WHERE id = :id")
    public abstract CleanUpSite get(String id);

    @Query("SELECT * FROM CleanUpSite WHERE owner_id = :ownerId")
    public abstract List<CleanUpSite> getByOwnerList(String ownerId);
}
