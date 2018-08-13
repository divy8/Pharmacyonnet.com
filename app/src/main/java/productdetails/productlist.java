package productdetails;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

  import com.pharmacy.pharmacyonnet.MainActivity;

import com.pharmacy.pharmacyonnet.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

public class productlist extends AppCompatActivity {

    Activity k;
    private ExpandableHeightGridView gridview;
    private ArrayList<Beanclass> beanclassArrayList=new ArrayList<Beanclass>();
    private GridviewAdapter gridviewAdapter;

    private ExpandableHeightListView listview;
    private ArrayList<BeanclassList>Bean=new ArrayList<BeanclassList>();
    private ListviewAdapter baseAdapter;

    ArrayList<productitems> productitems=new ArrayList<productitems>();

    ImageView gridviewicon,listviewicon;

    private TextView viewmore,viewmore1;

    ImageView home,offer,fav,bag,noti;
    LinearLayout home0,offer0,fav0,bag0,noti0;
    int currentLen = 12;
    private Toolbar toolbar;
    private TextView toolbarTitle,notext;
    private ImageView right;


     private int[] IMAGE = {R.drawable.pik1, R.drawable.pik2, R.drawable.pik3, R.drawable.pik4, R.drawable.pik1, R.drawable.pik2};
     private String[] TITLE = {"abc","xyz"};
     private String[] PRICE =  {"Rs. 1,699", "Rs. 1,999"};
     private String[] CUTPRICE = {"Rs. 1,699", "Rs. 1,999", "Rs. 1,599", "Rs. 1,399", "Rs. 1,699", "Rs. 1,999"};
     private String[] DISCOUNT = {"10%", "14%", "20%", "25%","10%", "14%"};
     private String[] RATING = {"(1231)", "(2331)", "(2321)", "(3213)","(4322)", "(2432)"};

    private TextView refine;
    private int x,j;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppy_product_list);

        progressBar=findViewById(R.id.progress_bar);
        setToolBar();
        gridview = findViewById(R.id.gridview);
        listview = (ExpandableHeightListView)findViewById(R.id.listview);
        listview = findViewById(R.id.listview);
        progressBar=findViewById(R.id.progress_bar);

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
//        home.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
//        offer.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
//        fav.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
//        bag.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
//        noti.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
//
//        home0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                clickb("1");
//
//                Intent it = new Intent(productlist.this, CategoriesActivity.class);
//                startActivity(it);
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
//               // Intent it = new Intent(productlist.this, CartListActivity.class);
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
//                //Intent it = new Intent(productlist.this, ShoppyNotificationActivity.class);
//                //startActivity(it);
//
//            }
//        });
//
//
//        refine = (TextView)findViewById(R.id.refine);
//        refine.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               // Intent it = new Intent(productlist.this, FilterActivity.class);
//                //startActivity(it);
//            }
//        });

        k = this;
        viewmore = (TextView)findViewById(R.id.viewMore);
        viewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdata(productlist.this);
            }
        });

        viewmore1 = (TextView)findViewById(R.id.viewMore);
        viewmore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdata(productlist.this);
            }
        });


//
//        ********GRIDVIEW***********
        if (getIntent().hasExtra("id")) {
            String s = getIntent().getStringExtra("id");
            String url = MainActivity.domain + "/module/applicationdataexport/getproduct?id=" + s;
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
                            if (jsonData.length()!= 2) {
                                JSONArray jsonArray = new JSONArray(jsonData);

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    String id = obj.getString("id_product");
                                    String name = obj.getString("name");
                                    String price = obj.getString("price");
                                    String img_url = obj.getString("img_url");
                                    String final_price = obj.getString("final_price");
                                    String isDiscounted = obj.getString("isDiscounted");
                                    String grade = obj.getString("grade");
                                    if (isDiscounted.equalsIgnoreCase("true")) {
                                        String reduction_type = obj.getString("reduction_type");

                                        if (reduction_type.equalsIgnoreCase("percentage")) {
                                            String reduction_per = obj.getString("reduction");
                                            productitems pr = new productitems(name, id, price, img_url, reduction_per + "%", final_price, grade);
                                            productitems.add(pr);
                                        } else {
                                            String reduction_amt = obj.getString("reduction");
                                            productitems pr = new productitems(name, id, price, img_url, "Rs" + reduction_amt, final_price, grade);
                                            productitems.add(pr);
                                        }
                                    } else {
                                        productitems pr = new productitems(name, id, price, img_url, null, final_price, grade);
                                        productitems.add(pr);
                                    }
                                    // String pos=obj.getString("position");
//

                                }
                                x = productitems.size();
                                beanclassArrayList = new ArrayList<Beanclass>();
                                Bean = new ArrayList<BeanclassList>();
                                j = (int) x;
                                if (productitems.size() > 12) {
                                    Handler uiHandler = new Handler(Looper.getMainLooper());
                                    uiHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            showdata(productlist.this);

                                        }
                                    });
                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            viewmore.setVisibility(View.GONE);
                                            viewmore1.setVisibility(View.GONE);
                                        }
                                    });
                                    for (int i = 0; i < j; i++) {
                                        Beanclass beanclass = new Beanclass(productitems.get(i).getImg_url(), productitems.get(i).getName(), productitems.get(i).getFinal_price(), productitems.get(i).getPrice(), productitems.get(i).getDiscount(), productitems.get(i).getGrade(), productitems.get(i).getId());
                                        beanclassArrayList.add(beanclass);
                                        BeanclassList BeanclassList = new BeanclassList(productitems.get(i).getImg_url(), productitems.get(i).getName(), productitems.get(i).getFinal_price(), productitems.get(i).getPrice(), productitems.get(i).getDiscount(), productitems.get(i).getGrade(), productitems.get(i).getId());
                                        Bean.add(BeanclassList);
                                    }

                                    final Handler uiHandler = new Handler(Looper.getMainLooper());
                                    uiHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            gridviewAdapter = new GridviewAdapter(k, productlist.this, beanclassArrayList);
                                            gridview.setExpanded(true);
                                            gridview.setAdapter(gridviewAdapter);

                                            baseAdapter = new ListviewAdapter(k, productlist.this, Bean) {
                                            };
                                            listview.setAdapter(baseAdapter);

                                            listview = findViewById(R.id.listview);
                                            baseAdapter = new ListviewAdapter(productlist.this, productlist.this, Bean);
                                            listview.setExpanded(true);
                                            listview.setAdapter(baseAdapter);
                                            progressBar.setVisibility(View.INVISIBLE);
                                            uiHandler.postDelayed(this,3000);
                                        }
                                    });

                                }

                            }
                            else{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        notext=findViewById(R.id.notext);
                                        progressBar.setVisibility(View.INVISIBLE);
                                        notext.setText("Their are no products right now");
                                        notext.setVisibility(View.VISIBLE);

                                    }
                                });

                            }
                        }catch (IOException e) {
                            Log.e(TAG, "Exception Caught ", e);
                        } catch (JSONException j) {
                            Log.e(TAG, "Exception Caught ", j);

                        }
                    }

                });
            } else {
                Toast.makeText(this, "Network is Unavailable!",
                        Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this, "No Products",
                    Toast.LENGTH_LONG).show();
        }
        //////////////////////////////////////////////////

//        ***********Grid - list view **********

        gridviewicon = (ImageView) findViewById(R.id.gridviewicon);
        listviewicon = (ImageView)findViewById(R.id.listviewicon);


        gridviewicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                listviewicon.setVisibility(View.VISIBLE);
                viewmore1.setVisibility(View.GONE);
                gridviewicon.setVisibility(View.GONE);
                listview.setVisibility(View.GONE);
                gridview.setVisibility(View.VISIBLE);


            }
        });

        listviewicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewmore.setVisibility(View.VISIBLE);
                listviewicon.setVisibility(View.GONE);
                gridviewicon.setVisibility(View.VISIBLE);
                listview.setVisibility(View.VISIBLE);
                gridview.setVisibility(View.GONE);




            }
        });



    }


    public void showdata(final Context context) {

        beanclassArrayList.clear();
        Bean.clear();
        if (currentLen == 12) {

            viewmore.setVisibility(View.VISIBLE);
            viewmore1.setVisibility(View.VISIBLE);

        }
        if(currentLen==productitems.size()){
            viewmore1.setVisibility(View.GONE);
            viewmore.setVisibility(View.GONE);
        }
        for (int i = 0; i < currentLen; i++) {
            float price = Float.parseFloat(productitems.get(i).getFinal_price());
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            float twoDigitsF = Float.valueOf(decimalFormat.format(price));
            Beanclass beanclass = new Beanclass(productitems.get(i).getImg_url(), productitems.get(i).getName(),productitems.get(i).getFinal_price(),productitems.get(i).getPrice(), productitems.get(i).getDiscount(), productitems.get(i).getGrade(), productitems.get(i).getId());
            beanclassArrayList.add(beanclass);
            BeanclassList BeanclassList = new BeanclassList(productitems.get(i).getImg_url(), productitems.get(i).getName(),productitems.get(i).getFinal_price(),productitems.get(i).getPrice(), productitems.get(i).getDiscount(), productitems.get(i).getGrade(), productitems.get(i).getId());
            Bean.add(BeanclassList);

        }
        if(currentLen<12){
            gridviewAdapter = new GridviewAdapter(k, context, beanclassArrayList);
            gridview.setExpanded(true);
            gridview.setAdapter(gridviewAdapter);
            baseAdapter = new ListviewAdapter(k, context, Bean);
            listview.setExpanded(true);
            listview.setAdapter(baseAdapter);
        }
        if (currentLen == 12) {
            gridviewAdapter = new GridviewAdapter(k, context, beanclassArrayList);
            gridview.setExpanded(true);
            gridview.setAdapter(gridviewAdapter);
            baseAdapter = new ListviewAdapter(k, context, Bean);
            listview.setExpanded(true);
            listview.setAdapter(baseAdapter);
        }

        else{
            gridviewAdapter.updateDataSet(beanclassArrayList);
            gridviewAdapter.notifyDataSetChanged();
            baseAdapter.updateDataSet(Bean);
            baseAdapter.notifyDataSetChanged();
        }
        if (currentLen + 12 < productitems.size())
        {
            currentLen += 12;}
        else {
            currentLen = currentLen + (productitems.size() - currentLen);
        }

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
        toolbarTitle.setText("health");

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


    private boolean isNetworkAvailabe(){
        try {
            ConnectivityManager manager = (ConnectivityManager) productlist.this.getSystemService(Context.CONNECTIVITY_SERVICE);
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

}
