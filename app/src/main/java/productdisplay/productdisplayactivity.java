package productdisplay;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pharmacy.pharmacyonnet.MainActivity;

import com.pharmacy.pharmacyonnet.R;
import com.razorpay.Checkout;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.ButterKnife;

import cartlist.CartActivity;
import io.paperdb.Paper;
import ss.com.bannerslider.Slider;

import static android.content.ContentValues.TAG;

public class productdisplayactivity extends AppCompatActivity{

    private TextView viewMore;

    ImageView plus,minus,b1,b2;
    TextView cartno,cutprice,change,changeno;
    int counter = 0;
    Dialog myDialog1;
    EditText numberpopup;
    RatingBar ratingbar;
    private Slider slider;

    private DatabaseReference databaseUser;
    String id;
    String name;
    String summary;
    String description;
    String price;
    ArrayList<Images_url> images=new ArrayList<Images_url>();
    String sellingprice;
    String features,review_no;
    private RecyclerView rv2;
    private ArrayList<BeanlistPeopleViewed> Bean1;
    private PeopleViewedRecyclerViewAdapter baseAdapter1;
    private Context context;
    private FirebaseAuth mAuth;

    public static ArrayList<cart> cartArrayList;
    //    *****Trending string*******
    private int[] IMAGET = {R.drawable.watch, R.drawable.jeans0, R.drawable.tshirt0, R.drawable.watch};
    private String[] TITLET = {"Canvera Black Heel", "Canvera Black Heel", "Canvera Black Heel", "Canvera Black Heel"};
    private String[] PRICE = {"1200 Rs", "1200 Rs", "1200 Rs", "1200 Rs"};
    private String[] CUTPRICE = {"200 Rs", "200 Rs", "200 Rs", "200 Rs"};
    private String[] DISCOUNTT = {"15%", "10%", "25%", "50%"};
    private String[] RATINGTEXTT = {"(1234)", "(2322)", "(4322)", "(1223)"};

    ImageView home,offer,fav,bag,noti;
    LinearLayout home0,offer0,fav0,bag0,noti0;

    private Toolbar toolbar;
    private TextView toolbarTitle,title,displayprice,displaysummary,displaycutprice,discount,review_nos;
    private ImageView right,displayimage;
    private ImageView rightSec;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);




        databaseUser = FirebaseDatabase.getInstance().getReference();

        Slider.init(new PicassoImageLoadingService(this));
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
//                //Intent it = new Intent(ProductsDetailActivity.this, CategoriesActivity.class);
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
//                //Intent it = new Intent(ProductsDetailActivity.this, CartListActivity.class);
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
//                //Intent it = new Intent(ProductsDetailActivity.this, ShoppyNotificationActivity.class);
//                //startActivity(it);
//
//            }
//        });

/////////////////////////////////////////////////////////////////////////////////////////
        String s=getIntent().getStringExtra("id");
        String url = MainActivity.domain+"/module/applicationdataexport/getproductinfo?id_product="+s;
        OkHttpClient client = new OkHttpClient();
        ButterKnife.bind(this);
        if (isNetworkAvailabe()) {
            Request request = new Request.Builder().url(url).build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Log.e(TAG, "Failure");
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        Log.e(TAG, jsonData);
                        //JSONArray jsonArray = new JSONArray(jsonData);
                       // for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = new JSONObject(jsonData);
                             id=obj.getString("id_product");
                             name = obj.getString("name");
                             summary = obj.getString("summary");
                             description=obj.getString("description");
                             price=obj.getString("price");
                             sellingprice=obj.getString("selling_price");
                            String image=obj.getString("images");
                            features=obj.getString("features");
                        review_no=obj.getString("review_no");
                                JSONArray jsonArray1=new JSONArray(image);
                                for (int j=0;j<jsonArray1.length();j++) {
                                    JSONObject obj1 = jsonArray1.getJSONObject(j);
                                    String url=obj1.getString("url");
                                    Images_url iu=new Images_url(url);
                                    images.add(iu);
                                }
                        //}

                        Handler uiHandler = new Handler(Looper.getMainLooper());
                        uiHandler.post(new Runnable() {
                            @Override
                            public void run() {

                                title=findViewById(R.id.title);
                                displayprice=findViewById(R.id.Finalprice);
                                displaysummary=findViewById(R.id.summary);
                                displaycutprice=findViewById(R.id.cutprice);
                                discount=findViewById(R.id.discount);
                                review_nos=findViewById(R.id.reviewno);
                                slider = findViewById(R.id.banner_slider1);





                                slider.setAdapter(new MainSliderAdapter(images,productdisplayactivity.this));
                                slider.setIndicatorStyle(3);


                                title.setText(name);
                                displaysummary.setText(Html.fromHtml(summary));
                                displayprice.setText("Rs "+sellingprice);
                                review_nos.setText(review_no);
                                if (getIntent().getStringExtra("discount")!=null) {
                                    discount.setText(getIntent().getStringExtra("discount"));
                                    displaycutprice.setText("Rs "+price);
                                }
                                else{
                                    discount.setVisibility(View.GONE);
                                    displaycutprice.setVisibility(View.GONE);
                                }
                            }
                        });
                    }
                    catch (IOException e) {
                        Log.e(TAG, "Exception Caught ", e);
                    } catch (JSONException j) {
                        Log.e(TAG, "Exception Caught ", j);

                    }
                }

            });
        }
        else{
            Toast.makeText(this, "Network is Unavailable!",
                    Toast.LENGTH_LONG).show();
        }



        //////////////////////////////////////////////////////////////////////////////////////


        b1=findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                upload_order(name,id,sellingprice,images.get(0).getUrl());

            }
        });

        b2=findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Checkout.preload(getApplicationContext());
//                startPayment();
                Intent i= new Intent(productdisplayactivity.this, CartActivity.class);
                i.putExtra("image",images.get(0).getUrl());
                i.putExtra("name",name);
                i.putExtra("price",sellingprice);
                i.putExtra("MRP",price);
                i.putExtra("discount",getIntent().getStringExtra("discount"));
                startActivity(i);
            }
        });


        // send
        viewMore = (TextView)findViewById(R.id.viewMore);
        viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(productdisplayactivity.this, Viewmoredetails.class);
                it.putExtra("description",description);
                it.putExtra("features",features);
                startActivity(it);
            }
        });


        //*********RECYCLERVIEWS*********

        rv2 = (RecyclerView)findViewById(R.id.rv2);

        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rv2.setLayoutManager(mLayoutManager1);


        Bean1 = new ArrayList<BeanlistPeopleViewed>();

        for (int i= 0; i< TITLET.length; i++){

            BeanlistPeopleViewed bean1 = new BeanlistPeopleViewed(IMAGET[i], TITLET[i], PRICE[i], CUTPRICE[i],DISCOUNTT[i], RATINGTEXTT[i]);
            Bean1.add(bean1);

        }


        baseAdapter1 = new PeopleViewedRecyclerViewAdapter(this,productdisplayactivity.this, Bean1) {
        };






        rv2.setAdapter(baseAdapter1);





        change = (TextView) findViewById(R.id.change);

        changeno = (TextView) findViewById(R.id.changeno);
//*******Text thru Line********
        cutprice = (TextView) findViewById(R.id.cutprice);
        cutprice.setPaintFlags(cutprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

//********PRODUCT PLUS MINUS*************
        plus = (ImageView)findViewById(R.id.plus);
        minus = (ImageView)findViewById(R.id.minus);
        cartno = (TextView) findViewById(R.id.cartno);

        final int[] number = {0};
        cartno.setText(""+ number[0]);

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (number[0] == 0){
                    cartno.setText("" + number[0]);
                }

                if (number[0] > 0){

                    number[0] = number[0] -1;
                    cartno.setText(""+ number[0]);
                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (number[0] == 9) {
                    cartno.setText("" + number[0]);
                }

                if (number[0] < 9) {

                    number[0] = number[0] + 1;
                    cartno.setText("" + number[0]);

                }
            }
        });

//        ***********ratingBar**********
        ratingbar = (RatingBar) findViewById(R.id.ratingbar);
        LayerDrawable stars = (LayerDrawable) ratingbar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);

        //        *********POPUP*************

        changeno.setText("000000");
        changeno.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                myDialog1 = new Dialog(productdisplayactivity.this);
                myDialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                myDialog1.setCancelable(true);
                myDialog1.setContentView(R.layout.popup_change);
                myDialog1.show();

                numberpopup = (EditText) myDialog1.findViewById(R.id.numberpopup);

                TextView button = (TextView) myDialog1.findViewById(R.id.button);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if(numberpopup.getText().toString().equalsIgnoreCase("")||numberpopup.getText().toString().equalsIgnoreCase(null)) {

                            Toast.makeText(productdisplayactivity.this,"write a code",Toast.LENGTH_LONG).show();

                        }

                        else{


                            changeno.setText(numberpopup.getText().toString());
                            myDialog1.dismiss();


                        }
                    }
                });
            }
        });

    }



//    private void clickb(String s) {
//
//        home.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);
//        offer.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);
//        fav.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);
//        bag.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);
//        noti.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);
//
//
//
//        if(s.equalsIgnoreCase("1")) {
//
//            home.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
//
//        }
//
//
//        else if(s.equalsIgnoreCase("2")) {
//
//            offer.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
//
//        }
//        else if(s.equalsIgnoreCase("3")) {
//
//            fav.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
//
//        }
//        else if(s.equalsIgnoreCase("4")) {
//
//            bag.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
//
//        }
//        else if(s.equalsIgnoreCase("5")) {
//
//            noti.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
//
//        }
//    }


    private void setToolBar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView)toolbar.findViewById(R.id.txt);
        right = (ImageView) toolbar.findViewById(R.id.right);
        rightSec = (ImageView) toolbar.findViewById(R.id.rightSec);
        toolbarTitle.setText("Product Details");

        right.setImageResource(R.drawable.fav1);
        rightSec.setImageResource(R.drawable.share);

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

    private boolean isNetworkAvailabe(){
        try {
            ConnectivityManager manager = (ConnectivityManager) productdisplayactivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            boolean isAvailable = false;

            if (networkInfo != null && networkInfo.isConnected()) {
                isAvailable = true;
            }
            return isAvailable;
        }

        catch (Exception e){}

        return false;
    }

    private void upload_order(String name,String pid,String price,String image) {
        String id = mAuth.getInstance().getCurrentUser().getUid();
        cart product_order = new cart(name,pid,price,image);
        databaseUser.child("user").child(id).child("cart").child(pid).setValue(product_order);
        databaseUser.keepSynced(true);
        Toast.makeText(getBaseContext(), "Item added to cart", Toast.LENGTH_SHORT).show();
    }

    public void startPayment(){
        /**
         * Replace with your public key
         */
        final String public_key = "rzp_live_jRmzAnrSnxw3gR";

        /**
         * You need to pass current activity in order to let razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        co.setImage(R.drawable.ic_launcher_foreground);

        try{
            JSONObject options = new JSONObject();
            double amount=Double.parseDouble(sellingprice)*100;

            options.put("amount", amount);
            options.put("name", "Pharmacyonnet");
            options.put("description",name);

            options.put("prefill", new JSONObject("{email: 'divypandey999@gmail.com', contact: '7754992244'}"));

            co.open(activity, options);

        } catch(Exception e){
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * The name of the function has to be
     *   onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    public void onPaymentSuccess(String razorpayPaymentID){
        try {
            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Log.e("com.merchant", e.getMessage(), e);
        }
    }

    /**
     * The name of the function has to be
     *   onPaymentError
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    public void onPaymentError(int code, String response){
        try {
            Toast.makeText(this, "Payment failed: " + Integer.toString(code) + " " + response, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Log.e("com.merchant", e.getMessage(), e);
        }
    }


}
