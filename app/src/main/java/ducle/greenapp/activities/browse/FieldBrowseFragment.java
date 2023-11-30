package ducle.greenapp.activities.browse;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ducle.greenapp.AppRepository;
import ducle.greenapp.R;
import ducle.greenapp.activities.reservation.MakeReservationActivity;
import ducle.greenapp.models.field.Centre;
import ducle.greenapp.models.field.Field;

public class FieldBrowseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_browse, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView fieldListView = (ListView) view.findViewById(R.id.browseListView);

        Intent intent = getActivity().getIntent();
        Bundle bundle = this.getArguments();
        Centre centre = AppRepository.Instance().findCentre(bundle.getString("centreId"));

        getActivity().setTitle(centre.getName() + " centre");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, centre.getFieldManager().getList());
        fieldListView.setAdapter(arrayAdapter);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK){
                        Intent data = result.getData();}
                });

        fieldListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long id) {
                if(intent.getStringExtra("userId").startsWith("CUS")){
                    Intent intent1 = new Intent(getActivity(), MakeReservationActivity.class);
                    intent1.putExtras(getActivity().getIntent());
                    intent1.putExtra("fieldId", ((Field) adapterView.getItemAtPosition(i)).getId());
                    launcher.launch(intent1);
                }
            }
        });
    }
}