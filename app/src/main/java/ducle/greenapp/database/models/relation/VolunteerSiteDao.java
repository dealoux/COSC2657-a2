package ducle.greenapp.database.models.relation;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import ducle.greenapp.database.models.MyEntityDao;

@Dao
public abstract class VolunteerSiteDao implements MyEntityDao<VolunteerSiteCrossRef>{
    @Insert
    public void insert(String userId, String siteId) {
        insert(new VolunteerSiteCrossRef(userId, siteId));
    }

    @Query("DELETE FROM VolunteerSiteCrossRef WHERE user_id = :userId")
    public abstract void  deleteByUserId(String userId);
    @Query("DELETE FROM VolunteerSiteCrossRef WHERE site_id = :siteId")
    public abstract void  deleteBySiteId(String siteId);
    @Query("DELETE FROM VolunteerSiteCrossRef WHERE user_id =:userId AND site_id = :siteId")
    public abstract void  delete(String userId, String siteId);

    @Transaction
    @Query("SELECT * FROM Volunteer")
    public abstract List<VolunteerWithOwnedSites> getVolunteerWithSites();

    @Transaction
    @Query("SELECT * FROM Volunteer WHERE id = :volunteerId")
    public abstract VolunteerWithOwnedSites getVolunteerWithSites(String volunteerId);

    @Transaction
    @Query("SELECT * FROM CleanUpSite")
    public abstract List<SiteWithVolunteers> getSiteWithVolunteers();

    @Transaction
    @Query("SELECT * FROM CleanUpSite WHERE id = :siteId")
    public abstract SiteWithVolunteers getSiteWithVolunteers(String siteId);
}
