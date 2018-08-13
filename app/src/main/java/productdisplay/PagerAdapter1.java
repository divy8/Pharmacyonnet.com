package productdisplay;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

public class PagerAdapter1 extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    Viewmoredetails oo;
    public PagerAdapter1(FragmentManager fm, int NumOfTabs, Viewmoredetails oo) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.oo = oo;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragment1 tab1 = new TabFragment1();
                tab1.attachOO(oo);
                return tab1;
            case 1:
                TabFragment2 tab2 = new TabFragment2();
                 tab2.attachOO(oo);
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
