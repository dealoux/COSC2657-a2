package ducle.greenapp.database.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface CleanUpSiteDao extends MyEntityDao<CleanUpSite> {
//    @Query("SELECT * FROM CLeanUpSite WHERE id = :id")
//    Flowable<Volunteer> getSite(String id);
//    @Query("SELECT * FROM CleanUpSite")
//    Flowable<ArrayList<CleanUpSite>> getSiteList();
//
//    @Query("SELECT * FROM CleanUpSite WHERE `owner id` = :ownerId")
//    Flowable<ArrayList<CleanUpSite>> getSiteByOwnerList(String ownerId);

    @Query("SELECT * FROM CleanUpSite WHERE site_id = :id")
    CleanUpSite getSite(String id);
    @Query("SELECT * FROM CleanUpSite")
    Flowable<List<CleanUpSite>> getSiteList();

    @Query("SELECT * FROM CleanUpSite WHERE owner_id = :ownerId")
    Flowable<List<CleanUpSite>> getSiteByOwnerList(String ownerId);
}
