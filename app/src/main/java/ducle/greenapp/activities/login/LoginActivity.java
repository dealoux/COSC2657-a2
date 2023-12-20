package ducle.greenapp.activities.login;

import static ducle.greenapp.activities.utils.ActivityUtils.setDate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

import ducle.greenapp.AppRepository;
import ducle.greenapp.R;

public class LoginActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout);

        AppRepository.Instance(this);

        Fragment loginFragment = new LoginFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentFl, loginFragment);
        transaction.commit();
    }

    // Date picker activity
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = new GregorianCalendar(year, month, dayOfMonth);
        setDate((EditText) findViewById(R.id.dobRegister), cal);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onDateClick(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.show();
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                EditText date = view.findViewById(R.id.dobRegister);
                date.setText(dayOfMonth+ "-" + month + "-" +year);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        AppRepository.Instance().storeData();
//        Toast.makeText(LoginActivity.this,"Data saved", Toast.LENGTH_SHORT).show();
    }
}