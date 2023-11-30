package ducle.greenapp.activities.browse;

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
import ducle.greenapp.models.field.Centre;

public class CentreBrowseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_browse, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Browsing centres");

        ListView centreListView = (ListView) view.findViewById(R.id.browseListView);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, AppRepository.Instance().centresList());
        centreListView.setAdapter(arrayAdapter);

        centreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long id) {
                String centreId = ((Centre) adapterView.getItemAtPosition(i)).getId();
                Bundle bundle = new Bundle();
                bundle.putString("centreId", centreId);

                FieldBrowseFragment fieldBrowseFragment = new FieldBrowseFragment();
                fieldBrowseFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentFl, fieldBrowseFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}