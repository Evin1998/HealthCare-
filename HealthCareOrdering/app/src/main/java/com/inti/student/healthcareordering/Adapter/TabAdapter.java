package com.inti.student.healthcareordering.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.inti.student.healthcareordering.Fragment.AllFragment;
import com.inti.student.healthcareordering.Fragment.BabyCareFragment;
import com.inti.student.healthcareordering.Fragment.MedicineFragment;
import com.inti.student.healthcareordering.Fragment.SupplementFragment;

public class TabAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public TabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AllFragment allFragment = new AllFragment();
                return allFragment;
            case 1:
                BabyCareFragment babyCareFragment = new BabyCareFragment();
                return babyCareFragment;
            case 2:
                MedicineFragment healthFragment = new MedicineFragment();
                return healthFragment;
            case 3:
                SupplementFragment supplementFragment = new SupplementFragment();
                return supplementFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
