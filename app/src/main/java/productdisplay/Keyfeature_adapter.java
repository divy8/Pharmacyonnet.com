package productdisplay;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.pharmacy.pharmacyonnet.R;

import java.util.ArrayList;

public class Keyfeature_adapter extends RecyclerView.Adapter<Keyfeature_adapter.ViewHolder>{



    private ArrayList<features> listItems;
    private Context context;

    public Keyfeature_adapter(ArrayList<features> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.keyfeatures,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        features features=listItems.get(position);
        holder.head.setText(features.getHead());
        holder.des.setText(features.getDes());

//       holder.itemView.setBackgroundColor(Color.rgb(0,0,0));

    }
    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public customfonts.MyTextView head;
        public customfonts.MyTextView des;

        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            head=itemView.findViewById(R.id.head);
            des=itemView.findViewById(R.id.description);
        }
    }
}
