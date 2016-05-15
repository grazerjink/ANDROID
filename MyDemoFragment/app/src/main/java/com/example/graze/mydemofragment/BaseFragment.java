package com.example.graze.mydemofragment;

import android.app.Fragment;

/**
 * Created by graze on 15/05/2016.
 */
public abstract class BaseFragment extends Fragment {
    protected abstract String getTagName();

    public BaseFragment() {
        super();
    }
}
