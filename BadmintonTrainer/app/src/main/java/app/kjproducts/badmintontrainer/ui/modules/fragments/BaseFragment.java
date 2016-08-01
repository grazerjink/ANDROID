package app.kjproducts.badmintontrainer.ui.modules.fragments;

import android.app.Fragment;

/**
 * Created by graze on 31/07/2016.
 */
public abstract class BaseFragment extends Fragment {
    protected abstract String getTagName();

    public BaseFragment() {
        super();
    }
}
