package ducle.greenapp.database.models.relation;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface VolunteerSiteDao {
//    @Transaction
//    @Query("SELECT * FROM Volunteer")
//    Flowable<List<VolunteerWithSites>> getVolunteerWithSites();
//
//    @Transaction
//    @Query("SELECT * FROM CleanUpSite")
//    Flowable<List<SiteWithVolunteers>> getSiteWithVolunteers();

    @Transaction
    @Query("SELECT * FROM Volunteer")
    List<VolunteerWithSites> getVolunteerWithSites();

    @Transaction
    @Query("SELECT * FROM Volunteer WHERE user_id = :volunteerId")
    VolunteerWithSites getVolunteerWithSites(String volunteerId);

    @Transaction
    @Query("SELECT * FROM CleanUpSite")
    List<SiteWithVolunteers> getSiteWithVolunteers();

    @Transaction
    @Query("SELECT * FROM CleanUpSite WHERE site_id = :siteId")
    SiteWithVolunteers getSiteWithVolunteers(String siteId);
}
