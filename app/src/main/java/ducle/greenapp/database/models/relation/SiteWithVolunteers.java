package ducle.greenapp.database.models.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import ducle.greenapp.database.models.CleanUpSite;
import ducle.greenapp.database.models.user.Volunteer;

public class SiteWithVolunteers {
    @Embedded
    public CleanUpSite site;

    @Relation(
            parentColumn = "id",
            entity = Volunteer.class,
            entityColumn = "id",
            associateBy = @Junction(value = VolunteerSiteCrossRef.class,
                    parentColumn = "site_id",
                    entityColumn = "user_id"
            )
    )
    public List<Volunteer> volunteerList;
}
