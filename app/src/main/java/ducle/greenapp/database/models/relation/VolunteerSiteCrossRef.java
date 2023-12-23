package ducle.greenapp.database.models.relation;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"user_id", "site_id"})
public class VolunteerSiteCrossRef{
    @NonNull
    public String user_id;
    @NonNull
    public String site_id;

    public VolunteerSiteCrossRef(@NonNull String user_id, @NonNull String site_id) {
        this.user_id = user_id;
        this.site_id = site_id;
    }
}


