package ducle.greenapp.database.models.relation;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"user_id", "site_id"})
public class VolunteerSiteCrossRef{
    @NonNull
    public String user_id;
    @NonNull
    public String site_id;
}


