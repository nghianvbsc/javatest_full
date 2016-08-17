package bscenter.tracnghiemlaptrinh.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import bscenter.tracnghiemlaptrinh.views.fragments.GuideLevel;
import bscenter.tracnghiemlaptrinh.views.fragments.GuideOverview;
import bscenter.tracnghiemlaptrinh.views.fragments.GuidePauseAndExit;
import bscenter.tracnghiemlaptrinh.views.fragments.GuideScore;
import bscenter.tracnghiemlaptrinh.views.fragments.GuideScoreAndTime;
import bscenter.tracnghiemlaptrinh.views.fragments.GuideSetting;

/**
 * Created by NIT Admin on 05/06/2016
 */

public class GuideAdapter extends FragmentStatePagerAdapter {

    public GuideAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new GuideOverview();
        } else if (position == 1) {
            return new GuideSetting();
        } else if (position == 2) {
            return new GuideLevel();
        } else if (position == 3) {
            return new GuideScoreAndTime();
        } else if (position == 4) {
            return new GuideScore();
        } else if (position == 5) {
            return new GuidePauseAndExit();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 6;
    }
}
