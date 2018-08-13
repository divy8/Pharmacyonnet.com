package home;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
  import com.pharmacy.pharmacyonnet.MainActivity;

import com.pharmacy.pharmacyonnet.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import personal.PersonalActivity;
import wellness.WellnessActivity;
import butterknife.ButterKnife;
import healthcare.MenWomenCategoriesActivity;
import household.houseactivity;
import mother.MotherActivity;
import ss.com.bannerslider.Slider;

import static android.content.ContentValues.TAG;

public class HomeActivity extends Fragment{
    private RecyclerView rv,rv2,rv3;
    private ArrayList<BeanlistGrooming> Bean;
    private GroomingRecyclerViewAdapter baseAdapter;
    private ArrayList<BeanlistTrending> Bean1;
    private TrendingRecyclerViewAdapter baseAdapter1;


    private ExpandableHeightGridView gridview;
    private ExpandableHeightGridView gridview1;
    private ArrayList<Collection_images> collectionImages=new ArrayList<Collection_images>();
    private GridviewAdapter gridviewAdapter,gridviewAdapter1;
    Typeface fonts1,fonts2,fonts3,fonts4;
    TextView shirt1,jeans1,shoes1,slippers1,goggles1,groomingproducts,trendingproducts;
    EditText searchtext;
    LinearLayout home0,offer0,fav0,bag0,noti0;
    ImageView home,offer,fav,bag,noti,b1,b2,b3,b4,b6,b7;
    LinearLayout Health;
    LinearLayout wellness;
    LinearLayout personalcare;
    LinearLayout motherbaby;
    LinearLayout household;
    private Slider slider;
    SwipeRefreshLayout mySwipeRefreshLayout;

    ArrayList<Images_url> images=new ArrayList<Images_url>();
    //    *****Grooming string*****
    private int[] IMAGEG = {R.drawable.womenshoes1, R.drawable.womenshoes2, R.drawable.womenshoes3, R.drawable.womenshoes1};
    private String[] TITLEG = {"Canvera Black Heel", "Canvera Black Heel", "Canvera Black Heel", "Canvera Black Heel"};
    private String[] DESCRIPTIONG = {"1200 Rs", "1200 Rs", "1200 Rs", "1200 Rs"};
    private String[] DATEG = {"200 Rs", "200 Rs", "200 Rs", "200 Rs"};
    private String[] DISCOUNTG = {"15%", "10%", "25%", "50%"};
    private String[] RATINGTEXTG = {"1234", "2322", "4322", "1223"};

    //    *****Trending string*******
    private int[] IMAGET = {R.drawable.watch, R.drawable.jeans0, R.drawable.tshirt0, R.drawable.watch};
    private String[] TITLET = {"Canvera Black Heel", "Canvera Black Heel", "Canvera Black Heel", "Canvera Black Heel"};
    private String[] DESCRIPTIONT = {"1200 Rs", "1200 Rs", "1200 Rs", "1200 Rs"};
    private String[] DATET = {"200 Rs", "200 Rs", "200 Rs", "200 Rs"};
    private String[] DISCOUNTT = {"15%", "10%", "25%", "50%"};
    private String[] RATINGTEXTT = {"(1234)", "(2322)", "(4322)", "(1223)"};



    HashMap<Integer,ArrayList<collection1>> homeimages=new HashMap<>();

    static{
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home, container, false);


        mySwipeRefreshLayout=view.findViewById(R.id.swiperefresh);
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(TAG, "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        request_to_url();
                    }
                }
        );

        gridview=view.findViewById(R.id.gridview);
        gridview1=view.findViewById(R.id.gridview1);
        Slider.init(new PicassoImageLoadingService(view.getContext()));
        slider = view.findViewById(R.id.banner_slider1);

        Health = (LinearLayout)view.findViewById(R.id.healths);
        Health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), MenWomenCategoriesActivity.class);
                startActivity(it);
            }
        });

        wellness = (LinearLayout)view.findViewById(R.id.Wellness);
        wellness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), WellnessActivity.class);
                startActivity(it);
            }
        });

        personalcare = (LinearLayout)view.findViewById(R.id.personalcare);
        personalcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), PersonalActivity.class);
                startActivity(it);
            }
        });

        motherbaby = (LinearLayout)view.findViewById(R.id.motherbaby);
        motherbaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), MotherActivity.class);
                startActivity(it);
            }
        });

        household = (LinearLayout)view.findViewById(R.id.household);
        household.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), houseactivity.class);
                startActivity(it);
            }
        });



        shirt1 = (TextView)view. findViewById(R.id.shirt1);
        jeans1 = (TextView)view. findViewById(R.id.jeans1);
        shoes1 = (TextView) view.findViewById(R.id.shoes1);
        slippers1 = (TextView) view.findViewById(R.id.slippers1);
        goggles1 = (TextView) view.findViewById(R.id.goggles1);
        groomingproducts = (TextView)view. findViewById(R.id.groomingproducts);
        trendingproducts = (TextView) view.findViewById(R.id.trendingproducts);
        searchtext = (EditText) view.findViewById(R.id.searchtext);

//     *******BOT BAR COLOR *********
        home = (ImageView) view.findViewById(R.id.home);
        home0 =(LinearLayout)view.findViewById(R.id.home0);
        offer = (ImageView) view.findViewById(R.id.offer);
        offer0 =(LinearLayout)view.findViewById(R.id.offer0);
        fav = (ImageView) view.findViewById(R.id.fav);
        fav0 =(LinearLayout)view.findViewById(R.id.fav0);
        bag = (ImageView) view.findViewById(R.id.bag);
        bag0 =(LinearLayout)view.findViewById(R.id.bag0);
        noti = (ImageView) view.findViewById(R.id.noti);
        noti0 =(LinearLayout)view.findViewById(R.id.noti0);


        home.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
        offer.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
        fav.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
        bag.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
        noti.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);


        home0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickb("1");

              //  Intent it = new Intent(getActivity(), MenWomenCategoriesActivity.class);
                //startActivity(it);

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

            //    Intent it = new Intent(getActivity(), CartListActivity.class);
              //  startActivity(it);


            }
        });
        noti0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickb("5");

               // Intent it = new Intent(getActivity(), ShoppyNotificationActivity.class);
                //startActivity(it);

            }
        });

        request_to_url();


        fonts1 =  Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/OpenSans-Regular.ttf");
        fonts2 =  Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/OpenSans-Semibold.ttf");

        fonts3 =  Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Roboto-Medium.ttf");

        fonts4 =  Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Roboto-Regular.ttf");


        searchtext.setTypeface(fonts1);
        shirt1.setTypeface(fonts4);
        jeans1.setTypeface(fonts4);
        shoes1.setTypeface(fonts4);
        slippers1.setTypeface(fonts4);
        goggles1.setTypeface(fonts4);
        groomingproducts.setTypeface(fonts2);
        trendingproducts.setTypeface(fonts2);
//*********RECYCLERVIEWS*********

        rv = (RecyclerView)view.findViewById(R.id.rv);
        rv2 = (RecyclerView)view.findViewById(R.id.rv2);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(mLayoutManager);

        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv2.setLayoutManager(mLayoutManager1);



        Bean = new ArrayList<BeanlistGrooming>();

        for (int i= 0; i< TITLEG.length; i++){

            BeanlistGrooming bean = new BeanlistGrooming(IMAGEG[i], TITLEG[i], DESCRIPTIONG[i], DATEG[i],DISCOUNTG[i], RATINGTEXTG[i]);
            Bean.add(bean);

        }

        baseAdapter = new GroomingRecyclerViewAdapter(getActivity(),getActivity(), Bean) {
        };

        Bean1 = new ArrayList<BeanlistTrending>();

        for (int i= 0; i< TITLEG.length; i++){

            BeanlistTrending bean1 = new BeanlistTrending(IMAGET[i], TITLET[i], DESCRIPTIONT[i], DATET[i],DISCOUNTT[i], RATINGTEXTT[i]);
            Bean1.add(bean1);

        }


        baseAdapter1 = new TrendingRecyclerViewAdapter(getActivity(),getActivity(), Bean1) {
        };
        rv.setAdapter(baseAdapter);
        rv2.setAdapter(baseAdapter1);


        ((GroomingRecyclerViewAdapter) baseAdapter).setOnItemClickListener(new GroomingRecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {


                //Intent it = new Intent(getActivity(), ProductsDetailActivity.class);
                //startActivity(it);

            }
        });

        ((TrendingRecyclerViewAdapter) baseAdapter1).setOnItemClickListener(new TrendingRecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

              //  Intent it = new Intent(getActivity(), ProductsDetailActivity.class);
               // startActivity(it);

            }
        });
        return view;
    }

    public void displayimages(int position, ImageView img, int size){
        img.setBackgroundColor(getResources().getColor(R.color.white));
        Picasso
                .with(view.getContext())
                .load(homeimages.get(position).get(size).getUrl())
                .into(img);
    }


    public void request_to_url()
    {
        String url = MainActivity.domain+"/appbackend/appimages/getjson";
        OkHttpClient client = new OkHttpClient();
        ButterKnife.bind(view);
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

                        for(int i=1; i<10;i++){
                            ArrayList<collection1> arrayList=new ArrayList<>();
                            homeimages.put(i,arrayList);
                        }

                        String jsonData = response.body().string();
                        Log.e(TAG, jsonData);
                        JSONArray jsonArray = new JSONArray(jsonData);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            String place_id = obj.getString("place_id");
                            String url = obj.getString("url");
                            String catid=obj.getString("category_id");
                            collection1 c=new collection1(url,place_id,catid);
                            homeimages.get(Integer.parseInt(place_id)).add(c);

                        }
                        Handler uiHandler = new Handler(Looper.getMainLooper());
                        uiHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                slider.setAdapter(new MainSliderAdapter(homeimages.get(1)));
                                slider.setIndicatorStyle(0);
                                slider.setLoopSlides(true);
                                slider.setInterval(5000);

                                b1=view.findViewById(R.id.b1);
                                b2=view.findViewById(R.id.b2);
                                b3=view.findViewById(R.id.b3);
                                b4=view.findViewById(R.id.b4);
                                b6=view.findViewById(R.id.b6);
                                b7=view.findViewById(R.id.b7);

                                if(homeimages.get(2).size()>=1) {
                                    displayimages(2,b1,0);
                                }
                                gridviewAdapter = new GridviewAdapter(getContext(),homeimages.get(3));
                                gridview.setAdapter(gridviewAdapter);

                                if(homeimages.get(4).size()>=1) {
                                    displayimages(4,b2,0);
                                }
                                gridviewAdapter1=new GridviewAdapter(getContext(),homeimages.get(5));
                                gridview1.setAdapter(gridviewAdapter1);
                                if(homeimages.get(6).size()>=1) {
                                    displayimages(6,b3,0);
                                }
                                if(homeimages.get(7).size()>=1) {
                                    displayimages(4,b4,0);
                                }
                                if(homeimages.get(8).size()>=1) {
                                    displayimages(8,b6,0);
                                }
                                if(homeimages.get(9).size()>=1) {
                                    displayimages(9,b7,0);
                                }
                            }
                        });


                    } catch (IOException e) {
                        Log.e(TAG, "Exception Caught ", e);
                    } catch (JSONException j) {
                        Log.e(TAG, "Exception Caught ", j);

                    }
                }
            });
        }
        else{
            Toast.makeText(view.getContext(), "Network is Unavailable!",
                    Toast.LENGTH_LONG).show();
        }

        mySwipeRefreshLayout.setRefreshing(false);


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
    ////////////////////////////////////////////////////////////////////////////////////////////-------------------------------------
    private boolean isNetworkAvailabe() {
        try {
            ConnectivityManager manager = (ConnectivityManager) view.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
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

