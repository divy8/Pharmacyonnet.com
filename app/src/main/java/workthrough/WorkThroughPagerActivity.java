package workthrough;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import com.pharmacy.pharmacyonnet.R;


public class WorkThroughPagerActivity extends AppCompatActivity {

    private View view;

    WorkThroughPagerActivity mFragmentPager;

    private Menu mMenu;

    ViewPager viewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workthrough_pager);



            viewPager = (ViewPager)findViewById(R.id.pager);
        final WorkthroughPagerAdapter adapter = new WorkthroughPagerAdapter
                (getSupportFragmentManager());
        viewPager.setAdapter(adapter);

    }


    public void setCurrentTab(int i) {
        viewPager.setCurrentItem(i);
    }



    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);

    }


}

