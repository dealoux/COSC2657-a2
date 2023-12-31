package ducle.greenapp.activities.site;

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

import ducle.greenapp.AppRepository;
import ducle.greenapp.R;
import ducle.greenapp.activities.utils.MyFragment;
import ducle.greenapp.database.models.CleanUpSite;

public class SiteBrowseFragment extends MyFragment {
    private String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_browse, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Browse Sites");

        Intent intent = getActivity().getIntent();
        Bundle bundle = getArguments();

        ListView siteListView = (ListView) view.findViewById(R.id.browseListView);

        if(bundle != null){
            userId = bundle.getString("userId");
        }
        else{
            userId = intent.getStringExtra("userId");
        }

        if(userId.startsWith("ADM")){
            ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, AppRepository.Instance(getContext()).getAllSites());
            siteListView.setAdapter(arrayAdapter);
        }
        else{
            ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, AppRepository.Instance(getContext()).getCleanUpSiteDao().getByOwnerList(userId));
            siteListView.setAdapter(arrayAdapter);
        }

        siteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long id) {
                CleanUpSite site = (CleanUpSite) adapterView.getItemAtPosition(i);
                Bundle bundle = new Bundle();
                bundle.putString("siteId", site.getId());

                SiteEditFragment siteEditFragment = new SiteEditFragment();
                siteEditFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentFl, siteEditFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}