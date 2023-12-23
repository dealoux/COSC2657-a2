package ducle.greenapp.activities.utils;

import androidx.fragment.app.Fragment;

public abstract class MyFragment extends Fragment {
    protected void popStack(){
        if(getParentFragmentManager().getBackStackEntryCount() > 0)
            getParentFragmentManager().popBackStack();
        else
            getActivity().finish();
    }
}