package com.example.graze.myfirstproject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

/**
 * Created by graze on 18/05/2016.
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            LoginFragment login = new LoginFragment();
            return login;
        }
        if(position == 1) {
            SignupFragment signup = new SignupFragment();
            return signup;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            return "LOGIN";
        }
        if(position == 1) {
            return "REGISTER";
        }
        return null;
    }
}
