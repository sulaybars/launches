package slybars.launches.ui.launchdetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import slybars.launches.model.entities.SpaceXLaunchItem;

/**
 * Created by slybars on 07/03/2018.
 */

public class LaunchDetailPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<SpaceXLaunchItem> launchList;

    public LaunchDetailPagerAdapter(FragmentManager fm, ArrayList<SpaceXLaunchItem> data) {
        super(fm);
        launchList = data;
    }

    @Override
    public Fragment getItem(int position) {
        return LaunchDetailFragment.getInstance(launchList.get(position));
    }

    @Override
    public int getCount() {
        return launchList == null ? 0 : launchList.size();
    }
}
