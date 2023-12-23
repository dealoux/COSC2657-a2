package ducle.greenapp.activities.user;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ducle.greenapp.AppRepository;
import ducle.greenapp.R;
import ducle.greenapp.activities.utils.MyFragment;
import ducle.greenapp.database.models.relation.SiteWithVolunteers;
import ducle.greenapp.database.models.user.User;
import ducle.greenapp.database.models.user.Volunteer;

public class UserBrowseFragment extends MyFragment {
    private SiteWithVolunteers siteWithVolunteers = null;
    private boolean isOwner = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_browse, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent intent = getActivity().getIntent();
        Bundle bundle = getArguments();

        ListView userListView = (ListView) view.findViewById(R.id.browseListView);

        if(bundle != null){
            siteWithVolunteers = AppRepository.Instance(getContext()).getVolunteerSiteDao().getSiteWithVolunteers(bundle.getString("siteId"));
            ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, siteWithVolunteers.volunteerList);
            userListView.setAdapter(arrayAdapter);

            isOwner = siteWithVolunteers.site.getOwnerId().equals(intent.getStringExtra("userId"));

            String title = "Volunteers for " + siteWithVolunteers.site.getId();
            if(isOwner){
                title += " (Owner)";
            }

            getActivity().setTitle(title);
        }
        else{
            if(intent.getStringExtra("userId").startsWith("ADM")){
                getActivity().setTitle("Browse Users");
                ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, AppRepository.Instance(getContext()).getAllUsers());
                userListView.setAdapter(arrayAdapter);
            }
            else{
                siteWithVolunteers = AppRepository.Instance(getContext()).getVolunteerSiteDao().getSiteWithVolunteers(intent.getStringExtra("siteId"));
                getActivity().setTitle("Volunteers for " + siteWithVolunteers.site.getId());
                ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, siteWithVolunteers.volunteerList);
                userListView.setAdapter(arrayAdapter);

                isOwner = siteWithVolunteers.site.getOwnerId().equals(intent.getStringExtra("userId"));

                String title = "Volunteers for " + siteWithVolunteers.site.getId();
                if(isOwner){
                    title += " (Owner)";
                }

                getActivity().setTitle(title);
            }
        }

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long id) {
                if(siteWithVolunteers == null){
                    User user = (User) adapterView.getItemAtPosition(i);
                    Bundle bundle = new Bundle();
                    bundle.putString("userId", user.getId());

                    UserEditFragment userEditFragment = new UserEditFragment();
                    userEditFragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentFl, userEditFragment)
                            .addToBackStack(null)
                            .commit();
                }
                else{
                    Volunteer volunteer = (Volunteer) adapterView.getItemAtPosition(i);

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    if(volunteer.getId().equals(siteWithVolunteers.site.getOwnerId())){
                        Toast.makeText(getContext(), "This is the site's owner", Toast.LENGTH_SHORT).show();
                    }
                    else if(isOwner || intent.getStringExtra("userId").startsWith("ADM")){
                        builder.setTitle("Remove confirmation");
                        builder.setMessage("Would you like to remove volunteer " + volunteer.getId() + " from the site?");
                        builder.setPositiveButton("Yes", (dialogInterface, i1) -> {
                            AppRepository.Instance(getContext()).getVolunteerSiteDao().delete(volunteer.getId(), siteWithVolunteers.site.getId());
                            Toast.makeText(getContext(), "Removed Volunteer " + volunteer.getTitle() + " from site " + siteWithVolunteers.site.getTitle(), Toast.LENGTH_SHORT).show();
                        });
                        builder.setNegativeButton("No", (dialogInterface, i1) -> {
                            dialogInterface.cancel();
                        });
                        builder.show();
                    }
                    else if (volunteer.getId().equals(intent.getStringExtra("userId"))){
                        builder.setTitle("Remove confirmation");
                        builder.setMessage("Would you like to remove yourself from this site?");
                        builder.setPositiveButton("Yes", (dialogInterface, i1) -> {
                            AppRepository.Instance(getContext()).getVolunteerSiteDao().delete(volunteer.getId(), siteWithVolunteers.site.getId());
                            Toast.makeText(getContext(), "Removed Volunteer " + volunteer.getTitle() + " from site " + siteWithVolunteers.site.getTitle(), Toast.LENGTH_SHORT).show();
                        });
                        builder.setNegativeButton("No", (dialogInterface, i1) -> {
                            dialogInterface.cancel();
                        });
                        builder.show();
                    }
                }
            }
        });
    }
}