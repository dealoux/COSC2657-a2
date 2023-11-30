package ducle.greenapp.activities.reservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ducle.greenapp.AppRepository;
import ducle.greenapp.R;
import ducle.greenapp.activities.utils.ActivityUtils;
import ducle.greenapp.models.field.Field;
import ducle.greenapp.models.field.Reservation;
import ducle.greenapp.models.user.Customer;

public class ReservationEditFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_reservation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent intent = getActivity().getIntent();
        Bundle bundle = getArguments();

        TextView reservationId = (TextView) view.findViewById(R.id.reservationId);
        TextView centreData = (TextView) view.findViewById(R.id.centreReservation);
        TextView fieldData = (TextView) view.findViewById(R.id.fieldReservation);
        TextView customerData = (TextView) view.findViewById(R.id.customerReservation);
        EditText dateReservation = (EditText) view.findViewById(R.id.dateReservation);
        AutoCompleteTextView timeslot = (AutoCompleteTextView) view.findViewById(R.id.timeReservation);
        Button buttonConfirm = (Button) view.findViewById(R.id.buttonReservationConfirm);
        Button buttonCancel = (Button) view.findViewById(R.id.buttonReservationCancel);

        Reservation reservation;

        if(bundle != null){
            getActivity().setTitle("Edit Reservation");
            reservation = AppRepository.Instance().getReservationManager().getManager().get(bundle.getString("reservationId"));

            dateReservation.setText(reservation.getDate());
            timeslot.setText(reservation.getTimeslot());
        }
        else{
            getActivity().setTitle("Make Reservation");
            Field field = AppRepository.Instance().findField(intent.getStringExtra("fieldId"));
            Customer customer = AppRepository.Instance().getUserManager().getCustomerManager().get(intent.getStringExtra("userId"));
            reservation = AppRepository.Instance().getReservationManager().nextReservation(customer, field);
        }

        reservationId.setText(reservation.getId());
        centreData.setText(reservation.getField().getCentre().toString());
        fieldData.setText(reservation.getField().toString());
        customerData.setText(reservation.getCustomer().toString());

        timeslot.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, Reservation.TIME_SLOTS));

        dateReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.datePickerDialog(getActivity().getSupportFragmentManager());
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reservation.setDate(dateReservation.getText().toString());
                reservation.setTimeslot(timeslot.getText().toString());

                if(bundle == null){
                    String result = AppRepository.Instance().getReservationManager().addReservation(reservation);
                    Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "Edited reservation " + reservation.getId() , Toast.LENGTH_SHORT).show();
                }

                popStack();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bundle != null){
                    reservation.setStatus("Cancelled");
                    Toast.makeText(getActivity(), "Cancelled reservation " + reservation.getId() , Toast.LENGTH_SHORT).show();
                }

                popStack();
            }
        });
    }

    private void popStack(){
        getActivity().finish();
    }
}