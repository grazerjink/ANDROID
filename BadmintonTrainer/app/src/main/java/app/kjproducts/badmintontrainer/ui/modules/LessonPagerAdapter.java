package app.kjproducts.badmintontrainer.ui.modules;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import app.kjproducts.badmintontrainer.ui.modules.fragments.AdvancedFragment;
import app.kjproducts.badmintontrainer.ui.modules.fragments.BasicFragment;
import app.kjproducts.badmintontrainer.ui.modules.fragments.EnlargedFragment;
import app.kjproducts.badmintontrainer.ui.modules.fragments.MenuSettingFragment;

/**
 * Created by graze on 31/07/2016.
 */
public class LessonPagerAdapter extends FragmentPagerAdapter {
    public LessonPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                BasicFragment basic = new BasicFragment();
                return basic;
            case 1:
                AdvancedFragment advanced = new AdvancedFragment();
                return advanced;
            case 2:
                EnlargedFragment enlarged = new EnlargedFragment();
                return enlarged;
            case 3:
                MenuSettingFragment menu = new MenuSettingFragment();
                return menu;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "BÀI TẬP  CƠ BẢN";
            case 1:
                return "BÀI TẬP NÂNG CAO";
            case 2:
                return "BÀI TẬP MỞ RỘNG";
            case 3:
                return "CÀI ĐẶT  ";
        }
        return null;
    }
}
