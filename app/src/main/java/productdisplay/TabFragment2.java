package productdisplay;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.pharmacy.pharmacyonnet.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class TabFragment2 extends android.support.v4.app.Fragment {

    ArrayList<features> featuresArrayList=new ArrayList<features>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    Viewmoredetails oo;
    void attachOO(Viewmoredetails oo){
        this.oo =oo;
    }
    TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.item_details_fragmenttab2, container, false);

        text=(TextView) view.findViewById(R.id.description);

        recyclerView=view.findViewById(R.id.list_recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        // reci
        Handler uiHandler = new Handler(Looper.getMainLooper());
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
            try {
                JSONArray jsonArray = new JSONArray(oo.getFeatures());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    String name = obj.getString("name");
                    String value = obj.getString("value");

                    features mc = new features(name, value);
                    featuresArrayList.add(mc);
                }
                adapter=new Keyfeature_adapter(featuresArrayList,getContext());
                recyclerView.setAdapter(adapter);

            }
            catch (JSONException j)
            {
                Log.e(TAG, "Exception Caught ", j);

            }



            }
        });

        return  view;




    }
}
