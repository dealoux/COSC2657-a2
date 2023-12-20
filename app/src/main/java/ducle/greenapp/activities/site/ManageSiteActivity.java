package ducle.greenapp.activities.site;

import static ducle.greenapp.activities.utils.ActivityUtils.setDate;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Calendar;
import java.util.GregorianCalendar;

import ducle.greenapp.R;

public class ManageSiteActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Fragment reservationFragment = new SiteBrowseFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentFl, reservationFragment);
        transaction.commit();
    }

    // Date picker activity
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = new GregorianCalendar(year, month, dayOfMonth);
        setDate((EditText) findViewById(R.id.dateReservation), cal);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onDateClick(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.show();
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                EditText date = view.findViewById(R.id.dateReservation);
                date.setText(dayOfMonth+ "-" + month + "-" +year);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}