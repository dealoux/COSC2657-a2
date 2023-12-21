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

public class LoginActivity extends AppCompatActivity {
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

    @Override
    protected void onResume() {
        super.onResume();
//        AppRepository.Instance().storeData();
//        Toast.makeText(LoginActivity.this,"Data saved", Toast.LENGTH_SHORT).show();
    }
}