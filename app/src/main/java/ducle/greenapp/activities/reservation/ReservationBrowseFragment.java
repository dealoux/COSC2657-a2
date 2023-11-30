package ducle.greenapp.activities.reservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ducle.greenapp.AppRepository;
import ducle.greenapp.R;
import ducle.greenapp.models.field.Reservation;

public class ReservationBrowseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_browse, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Browse Reservation");

        Intent intent = getActivity().getIntent();

        ListView reservationListView = (ListView) view.findViewById(R.id.browseListView);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, AppRepository.Instance().reservationsList(intent.getStringExtra("userId")));
        reservationListView.setAdapter(arrayAdapter);

        reservationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long id) {
                Reservation reservation = (Reservation) adapterView.getItemAtPosition(i);
                Bundle bundle = new Bundle();
                bundle.putString("reservationId", reservation.getId());

                ReservationEditFragment reservationEditFragment = new ReservationEditFragment();
                reservationEditFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentFl, reservationEditFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void popStack(){
        getParentFragmentManager().popBackStack();
    }
}