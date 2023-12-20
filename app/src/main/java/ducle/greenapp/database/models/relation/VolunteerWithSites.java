package ducle.greenapp.database.models.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import ducle.greenapp.database.models.CleanUpSite;
import ducle.greenapp.database.models.user.Volunteer;

public class VolunteerWithSites {
    @Embedded
    public Volunteer volunteer;

    @Relation(
            parentColumn = "user_id",
            entityColumn = "site_id",
            associateBy = @Junction(VolunteerSiteCrossRef.class)
    )
    public List<CleanUpSite> siteList;
}
