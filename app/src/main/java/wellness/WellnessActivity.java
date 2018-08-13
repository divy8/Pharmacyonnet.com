package wellness;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.pharmacy.pharmacyonnet.R;

import java.util.ArrayList;

public class WellnessActivity extends AppCompatActivity {

    ImageView home,offer,fav,bag,noti;
    LinearLayout home0,offer0,fav0,bag0,noti0;


    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView right;


     static ArrayList<Main_Catagories> main_catagories = new ArrayList<Main_Catagories>();
     ArrayList<Main_Catagories> main_catagoriesArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men_women_categories);

        setToolBar();
//        home = (ImageView) findViewById(R.id.home);
//        home0 =(LinearLayout)findViewById(R.id.home0);
//        offer = (ImageView) findViewById(R.id.offer);
//        offer0 =(LinearLayout)findViewById(R.id.offer0);
//        fav = (ImageView) findViewById(R.id.fav);
//        fav0 =(LinearLayout)findViewById(R.id.fav0);
//        bag = (ImageView) findViewById(R.id.bag);
//        bag0 =(LinearLayout)findViewById(R.id.bag0);
//        noti = (ImageView) findViewById(R.id.noti);
//        noti0 =(LinearLayout)findViewById(R.id.noti0);
//
//
//        home.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
//        offer.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
//        fav.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
//        bag.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
//        noti.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
//
//
//        home0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                clickb("1");
//
//               // Intent it = new Intent(MenWomenCategoriesActivity.this, CategoriesActivity.class);
//                //startActivity(it);
//
//            }
//        });
//        offer0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                clickb("2");
//
//            }
//        });
//        fav0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                clickb("3");
//
//            }
//        });
//        bag0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                clickb("4");
//
//               // Intent it = new Intent(MenWomenCategoriesActivity.this, CartListActivity.class);
//                //startActivity(it);
//
//
//            }
//        });
//        noti0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                clickb("5");
//
//                //Intent it = new Intent(MenWomenCategoriesActivity.this, ShoppyNotificationActivity.class);
//                //startActivity(it);
//
//            }
//        });



//         *******Tabview*********

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Specialized Nutrition"));
        tabLayout.addTab(tabLayout.newTab().setText("Anti Ageing"));
        tabLayout.addTab(tabLayout.newTab().setText("Healthy Living"));
        tabLayout.addTab(tabLayout.newTab().setText("Natural & Organic"));
        tabLayout.addTab(tabLayout.newTab().setText("Well Being"));


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        PagerAdapter1 adapter = new PagerAdapter1(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

//    private void clickb(String s) {
//
//        home.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
//        offer.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
//        fav.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
//        bag.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
//        noti.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
//
//
//
//        if(s.equalsIgnoreCase("1")) {
//
//            home.setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.MULTIPLY);
//
//        }
//
//
//        else if(s.equalsIgnoreCase("2")) {
//
//            offer.setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.MULTIPLY);
//
//        }
//        else if(s.equalsIgnoreCase("3")) {
//
//            fav.setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.MULTIPLY);
//
//        }
//        else if(s.equalsIgnoreCase("4")) {
//
//            bag.setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.MULTIPLY);
//
//        }
//        else if(s.equalsIgnoreCase("5")) {
//
//            noti.setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.MULTIPLY);
//
//        }
//    }


    private void setToolBar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.txt);
        right = (ImageView) toolbar.findViewById(R.id.right);
        //toolbarTitle.getText();
        toolbarTitle.setText("Catagories");

        right.setImageResource(R.drawable.search);


        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("");


        toolbar.setNavigationIcon(R.drawable.back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


    }
}



