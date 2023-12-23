package ducle.greenapp.activities.utils;

import static ducle.greenapp.activities.utils.ActivityUtils.setDate;

import android.app.DatePickerDialog;
import android.os.Build;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

import java.util.Calendar;
import java.util.GregorianCalendar;

import ducle.greenapp.R;

public abstract class MyDatePickerActivity extends MyActivity implements DatePickerDialog.OnDateSetListener {

    // Date picker activity
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = new GregorianCalendar(year, month, dayOfMonth);
        setDate((EditText) findViewById(R.id.dateSite), cal);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onDateClick(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.show();
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                EditText date = view.findViewById(R.id.dateSite);
                date.setText(dayOfMonth+ "-" + month + "-" +year);
            }
        });
    }
}