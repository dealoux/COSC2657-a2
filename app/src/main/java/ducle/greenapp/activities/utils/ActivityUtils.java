package ducle.greenapp.activities.utils;
import android.widget.EditText;

import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.model.LatLng;

import java.text.DateFormat;
import java.util.Calendar;

public class ActivityUtils {
    public static void datePickerDialog(FragmentManager fragmentManager){
        DatePickerFragment datePicker = new DatePickerFragment();
        datePicker.show(fragmentManager, "date picker");
    }

    public static void setDate(EditText view, final Calendar calendar){
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        view.setText(dateFormat.format(calendar.getTime()));
    }
}
