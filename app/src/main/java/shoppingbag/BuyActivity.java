package shoppingbag;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pharmacy.pharmacyonnet.R;


public class BuyActivity extends AppCompatActivity{


    private TextView placeOrder;

    private Toolbar toolbar;
    private TextView toolbarTitle;
    private TextView rightTxt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_bag);

        setToolBar();




        placeOrder= (TextView)findViewById(R.id.placeOrder);

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent it = new Intent(BuyActivity.this, ShoppyAddressActivity.class);
//                startActivity(it);
            }
        });




    }



    private void setToolBar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView)toolbar.findViewById(R.id.txt);
        rightTxt = (TextView) toolbar.findViewById(R.id.rightTxt);
        toolbarTitle.setText("Buy Now");

        rightTxt.setText("Step 1/3");

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("");


        toolbar.setNavigationIcon(R.drawable.back);

        rightTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(BuyActivity.this,
                        "Your Message", Toast.LENGTH_LONG).show();

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


    }

}
