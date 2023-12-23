package ducle.greenapp.database.models.relation;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public abstract class VolunteerSiteDao {
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
