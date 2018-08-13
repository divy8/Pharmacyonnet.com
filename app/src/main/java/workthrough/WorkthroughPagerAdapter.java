package workthrough;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class WorkthroughPagerAdapter extends FragmentStatePagerAdapter {



    public WorkthroughPagerAdapter(FragmentManager fm) {
        super(fm);

    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                WorkthrougOneFragment tab1 = new WorkthrougOneFragment();
                return tab1;
            case 1:
                WorkthroughTwoFragment tab2 = new WorkthroughTwoFragment();
                return tab2;


            case 2:
                WorkthroughThreeFragment tab3 = new WorkthroughThreeFragment();
                return tab3;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
