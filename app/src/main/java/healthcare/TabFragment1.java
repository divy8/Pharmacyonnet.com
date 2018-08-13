package healthcare;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

import butterknife.ButterKnife;
import healthcare.HealthAdapter;

import static android.content.ContentValues.TAG;


public class TabFragment1 extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    ImageView cover;
    static View view;
    ProgressBar progressBar;
    LinearLayout diabetes;
    //TextView men,women;
    //LinearLayout listmen,listwomen;
    //  private ArrayList<Child_catagories> child_catagoriesArrayList;

    static ArrayList<Main_Catagories> main_catagories=new ArrayList<Main_Catagories>();
    private static ArrayList<Main_Catagories> main_catagoriesArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmenttab1, container, false);

        progressBar=view.findViewById(R.id.progress_bar);
        loaddata(view);
        final Handler handler;
        recyclerView=view.findViewById(R.id.list_recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setLayoutManager(getActivity().getApplicationContext());
        // Data_Fetcher.loaddata();
        cover= view.findViewById(R.id.cover);
        Picasso
                .with(view.getContext())
                .load("https://static.pharmacyonnet.com/img/appimages/category-image/Healthcare-category-img.jpg")
                .resize(1000, 550)
                // resizes the image to these dimensions (in pixel). does not respect aspect ratio
                .into(cover);

//

        handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {

                adapter=new HealthAdapter(main_catagories,getContext());
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);
                handler.postDelayed(this, 3000);
            }

        };
        handler.postDelayed(r,3000);
//*******men women***********

        //  men = (TextView) view.findViewById(R.id.men);
        //women = (TextView) view.findViewById(R.id.women);
        //listmen = (LinearLayout) view.findViewById(R.id.listmen);
        //listwomen = (LinearLayout) view.findViewById(R.id.listwomen);

        //men.setTextColor(Color.parseColor("#ea495f"));
        //listmen.setVisibility(View.VISIBLE);
        //listwomen.setVisibility(View.GONE);


        //  listmen.setOnClickListener(new View.OnClickListener() {
        // @Override
        ///   public void onClick(View v) {
        //         Intent it = new Intent(getActivity(), ShoppyProductListActivity.class);
        //       startActivity(it);
        //    }
        //   });

//
//        listwomen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it = new Intent(getActivity(), ShoppyProductListActivity.class);
//                startActivity(it);
//            }
//        });


//        men.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                clickb("S", "1");
//
//            }
//        });
//
//
//        women.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                clickb("M", "2");
//
//            }
//        });

        //   return view;
        //}

        // private void clickb(String s , String t) {


        //   men.setTextColor(Color.parseColor("#76818a"));
        //  women.setTextColor(Color.parseColor("#76818a"));

/*
        if(t.equalsIgnoreCase("1")) {

            men.setTextColor(Color.parseColor("#ea495f"));
            listmen.setVisibility(View.VISIBLE);
            listwomen.setVisibility(View.GONE);



        }
        else if(t.equalsIgnoreCase("2")){

            women.setTextColor(Color.parseColor("#ea495f"));
            listwomen.setVisibility(View.VISIBLE);
            listmen.setVisibility(View.GONE);


        }
//*/


        //}
        return view;
    }

    public static void loaddata(final View view)
    {
        main_catagories.clear();
        // child_catagoriesArrayList=new ArrayList<Child_catagories>();
        //main_catagoriesArrayList=new ArrayList<Main_Catagories>();
        String url = MainActivity.domain+"/module/applicationdataexport/getcategory?id=32";
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
                        String jsonData = response.body().string();
                        Log.e(TAG, jsonData);
                        JSONArray jsonArray = new JSONArray(jsonData);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            String id=obj.getString("id");
                            String name = obj.getString("name");
                            String child_cat = obj.getString("child_cat");
                            String pos=obj.getString("position");

                            Main_Catagories mc=new Main_Catagories(id,name,pos);
                            main_catagories.add(mc);

                            //main_catagoriesArrayList=new ArrayList<Main_Catagories>();

                           /* JSONArray childsonArray=new JSONArray(child_cat);
                                for (int x=0;x<childsonArray.length();x++) {
                                    JSONObject childobj=childsonArray.getJSONObject(x);
                                    String childid=childobj.getString("id");
                                    String childpos=childobj.getString("position");
                                    String childname=childobj.getString("name");
                                    Main_Catagories cc=new Main_Catagories(childid,childname,childpos);
                                    main_catagoriesArrayList.add(cc);
                                }*/


                            //mc.setChildcatagories(main_catagoriesArrayList);

                        }
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

    }


    private static boolean isNetworkAvailabe() {
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