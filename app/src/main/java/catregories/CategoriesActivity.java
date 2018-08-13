package catregories;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.pharmacy.pharmacyonnet.R;

import healthcare.MenWomenCategoriesActivity;

public class CategoriesActivity extends AppCompatActivity {

    private RelativeLayout relative;

    ImageView home,offer,fav,bag,noti;
    LinearLayout home0,offer0,fav0,bag0,noti0;

    private Toolbar toolbar;
    private TextView toolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

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

                Intent it = new Intent(CategoriesActivity.this, CategoriesActivity.class);
                startActivity(it);

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

                clickb("4");

               // Intent it = new Intent(CategoriesActivity.this, CartListActivity.class);
                //startActivity(it);


            }
        });
        noti0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickb("5");

               // Intent it = new Intent(CategoriesActivity.this, ShoppyNotificationActivity.class);
                //startActivity(it);


            }
        });


        relative = (RelativeLayout)findViewById(R.id.relative);
        relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(CategoriesActivity.this, MenWomenCategoriesActivity.class);
                startActivity(it);

            }
        });
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
        toolbarTitle.setText("Categories");
        toolbarTitle.setTextColor(Color.parseColor("#ffffff"));

        toolbar.setBackgroundColor(Color.parseColor("#3d4652"));

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("");


        toolbar.setNavigationIcon(R.drawable.back_white);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }
        });


    }



}

