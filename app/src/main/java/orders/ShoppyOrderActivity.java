package orders;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.pharmacy.pharmacyonnet.R;

import java.util.ArrayList;

import catregories.CategoriesActivity;

public class ShoppyOrderActivity extends AppCompatActivity {

    private ExpandableHeightListView listview;
    private ArrayList<BeanclassOrder> Bean;
    private ListBaseAdapterOrder baseAdapter;
    private ProgressBar progressBar1;

    private int[] IMAGE = {R.drawable.order1, R.drawable.order1, R.drawable.order1,R.drawable.order1};
    private String[] ORDERDAY = {"2 day ago", "5 day ago", "7 day ago","8 day ago"};
    private String[] ORDERNAME = {"Vanvera Black High Heels", "Vanvera Black High Heels", "Vanvera Black High Heels","Vanvera Black High Heels"};
    private String[] QT = {"1 Pair", "1 Pair", "1 Pair","1 Pair"};
    private String[] DATE = {"June 20, 2016", "June 23, 2016", "June 28, 2016","June 30, 2016"};
    private String[] IDNUMBER = {"987876543", "3244876543", "233216543","9654876543"};


    ImageView home,offer,fav,bag,noti;
    LinearLayout home0,offer0,fav0,bag0,noti0;


    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView right;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        setToolBar();


        home = (ImageView) findViewById(R.id.home);
        home0 =(LinearLayout)findViewById(R.id.home0);
        offer = (ImageView) findViewById(R.id.offer);
        offer0 =(LinearLayout)findViewById(R.id.offer0);
        fav = (ImageView) findViewById(R.id.fav);
        fav0 =(LinearLayout)findViewById(R.id.fav0);
        bag = (ImageView) findViewById(R.id.bag);
        bag0 =(LinearLayout)findViewById(R.id.bag0);
        noti = (ImageView) findViewById(R.id.noti);
        noti0 =(LinearLayout)findViewById(R.id.noti0);


        home.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
        offer.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
        fav.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
        bag.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
        noti.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);


        home0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickb("1");

//                Intent it = new Intent(ShoppyOrderActivity.this, CategoriesActivity.class);
//                startActivity(it);

            }
        });
        offer0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickb("2");

            }
        });
        fav0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickb("3");

            }
        });
        bag0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                clickb("4");
//
//                Intent it = new Intent(ShoppyOrderActivity.this, CartListActivity.class);
//                startActivity(it);


            }
        });
        noti0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickb("5");

//                Intent it = new Intent(ShoppyOrderActivity.this, ShoppyNotificationActivity.class);
//                startActivity(it);

            }
        });

//        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
//
////        progressBar1.getProgressDrawable().setColorFilter(
////                Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
//
//        ((ProgressBar)findViewById(R.id.progressBar1))
//                .getIndeterminateDrawable()
//                .setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);


//     ********Listview**************

        listview = (ExpandableHeightListView)findViewById(R.id.listview);

        Bean = new ArrayList<BeanclassOrder>();

        for (int i= 0; i< IMAGE.length; i++){

            BeanclassOrder bean = new BeanclassOrder(IMAGE[i],ORDERDAY[i], ORDERNAME[i], QT[i], DATE[i], IDNUMBER[i]);
            Bean.add(bean);

        }

        baseAdapter = new ListBaseAdapterOrder(ShoppyOrderActivity.this, Bean) {
        };

        listview.setAdapter(baseAdapter);

    }


    private void clickb(String s) {

        home.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);
        offer.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);
        fav.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);
        bag.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);
        noti.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);



        if(s.equalsIgnoreCase("1")) {

            home.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);

        }


        else if(s.equalsIgnoreCase("2")) {

            offer.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);

        }
        else if(s.equalsIgnoreCase("3")) {

            fav.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);

        }
        else if(s.equalsIgnoreCase("4")) {

            bag.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);

        }
        else if(s.equalsIgnoreCase("5")) {

            noti.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);

        }
    }


    private void setToolBar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView)toolbar.findViewById(R.id.txt);
        right = (ImageView) toolbar.findViewById(R.id.right);
        toolbarTitle.setText("Orders");

        right.setImageResource(R.drawable.cross);

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
