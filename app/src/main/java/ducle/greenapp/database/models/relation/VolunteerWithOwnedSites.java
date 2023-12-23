package ducle.greenapp.database.models.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import ducle.greenapp.database.models.CleanUpSite;
import ducle.greenapp.database.models.user.Volunteer;

public class VolunteerWithOwnedSites {
    @Embedded
    public Volunteer volunteer;

    @Relation(
            parentColumn = "id",
            entity = CleanUpSite.class,
            entityColumn = "id",
            associateBy = @Junction(value = VolunteerSiteCrossRef.class,
                    parentColumn = "user_id",
                    entityColumn = "site_id")
    )
    public List<CleanUpSite> ownedSites;
}
