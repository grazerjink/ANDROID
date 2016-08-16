package app.kjproducts.badmintontrainer.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.List;

import app.kjproducts.badmintontrainer.fragment.ImageFragment;

/**
 * Created by KJ on 16/08/2016.
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    List<String> imgLink;

    public FragmentAdapter(FragmentManager fm, List<String> imgLink) {
        super(fm);
        this.imgLink = imgLink;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ImageFragment.newInstance(imgLink.get(0).toString());
            case 1:
                return ImageFragment.newInstance(imgLink.get(1).toString());
            case 2:
                return ImageFragment.newInstance(imgLink.get(2).toString());
            case 3:
                return ImageFragment.newInstance(imgLink.get(3).toString());
            case 4:
                return ImageFragment.newInstance(imgLink.get(4).toString());
            case 5:
                return ImageFragment.newInstance(imgLink.get(5).toString());
            case 6:
                return ImageFragment.newInstance(imgLink.get(6).toString());
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return imgLink.size();
    }
}
