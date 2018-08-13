package productdisplay;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pharmacy.pharmacyonnet.R;


public class TabFragment1 extends android.support.v4.app.Fragment{

    Viewmoredetails oo;
    void attachOO(Viewmoredetails oo){
        this.oo =oo;
    }

    TextView text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.item_details_fragmenttab1, container, false);

         text=(TextView) view.findViewById(R.id.description);

         // reci
        Handler uiHandler = new Handler(Looper.getMainLooper());
     uiHandler.post(new Runnable() {
         @Override
         public void run() {
        text.setText(Html.fromHtml(oo.getDescription()));
         }
       });

        return  view;

    }
}

