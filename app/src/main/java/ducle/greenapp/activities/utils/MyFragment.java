package ducle.greenapp.activities.utils;

import androidx.fragment.app.Fragment;

public abstract class MyFragment extends Fragment {
    protected void popStack(){
        getParentFragmentManager().popBackStack();
    }
}