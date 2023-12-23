package ducle.greenapp.activities.site;

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

import ducle.greenapp.AppRepository;
import ducle.greenapp.R;
import ducle.greenapp.activities.MyFragment;
import ducle.greenapp.activities.utils.ActivityUtils;
import ducle.greenapp.database.models.CleanUpSite;
import ducle.greenapp.database.models.user.Volunteer;

public class SiteEditFragment extends MyFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_site, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent intent = getActivity().getIntent();
        Bundle bundle = getArguments();

        TextView siteId = (TextView) view.findViewById(R.id.siteId);
        TextView siteName = (TextView) view.findViewById(R.id.siteName);
        TextView ownerData = (TextView) view.findViewById(R.id.siteOwner);
        TextView locationData = (TextView) view.findViewById(R.id.siteLocation);
        EditText dateSite = (EditText) view.findViewById(R.id.dateSite);
        AutoCompleteTextView timeslot = (AutoCompleteTextView) view.findViewById(R.id.timeSite);
        TextView wasteData = (TextView) view.findViewById(R.id.wasteAmount);

        Button buttonViewVolunteers = (Button) view.findViewById(R.id.buttonViewVolunteers);
        Button buttonConfirm = (Button) view.findViewById(R.id.buttonSiteConfirm);
        Button buttonCancel = (Button) view.findViewById(R.id.buttonSiteCancel);

        CleanUpSite site;

        if(bundle != null){
            getActivity().setTitle("Edit Site");
            site = AppRepository.Instance(getContext()).getCleanUpSiteDao().get(bundle.getString("siteId"));
        }
        else{
            String userId = intent.getStringExtra("userId");

            if(intent.getStringExtra("siteId") != null){
                site = AppRepository.Instance(getContext()).getCleanUpSiteDao().get(intent.getStringExtra("siteId"));

                if(userId.equals(site.getOwnerId())){
                    getActivity().setTitle("Edit Site");
                }
                else{
                    getActivity().setTitle("View Site");
                }
            }
            else{
                getActivity().setTitle("Create Site");
                buttonViewVolunteers.setVisibility(View.GONE);
                site = AppRepository.Instance(getContext()).nextSite(intent.getParcelableExtra("latLng"), userId);
            }
        }

        Volunteer owner = AppRepository.Instance(getContext()).getVolunteerDao().get(site.getOwnerId());

        siteId.setText(site.getId());
        siteName.setText(site.getName());
        ownerData.setText(owner.getTitle());
        locationData.setText(site.getLatLng().toString());
        dateSite.setText(site.getDate());
        timeslot.setText(site.getTime());
        wasteData.setText(site.getCollectedAmount());

        timeslot.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, CleanUpSite.TIME_SLOTS));

        dateSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.datePickerDialog(getActivity().getSupportFragmentManager());
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                site.setName(siteName.getText().toString());
                site.setDate(dateSite.getText().toString());
                site.setTime(timeslot.getText().toString());
                site.setCollectedAmount(wasteData.getText().toString());

                if(bundle == null){
                    String result = AppRepository.Instance(getContext()).addSite(site);
                    Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "Edited site " + site.getId() , Toast.LENGTH_SHORT).show();
                }

                popStack();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popStack();
            }
        });
    }
}