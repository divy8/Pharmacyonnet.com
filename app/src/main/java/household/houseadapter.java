package household;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.pharmacy.pharmacyonnet.R;

import java.util.ArrayList;

import productdetails.productlist;


public class houseadapter extends RecyclerView.Adapter<houseadapter.ViewHolder> {

     private ArrayList<Main_Catagories> listItems;
     private Context context;

    public houseadapter(ArrayList<Main_Catagories> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_catagories,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {

      final Main_Catagories listitem =listItems.get(position);
        holder.head.setText(listitem.getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main_Catagories id=listItems.get(position);
                Intent intent = new Intent(context , productlist.class);
                intent.putExtra("id",id.getId());
                //Log.e("Sending yooo o o id",id.getId());
                context.startActivity(intent);
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main_Catagories id=listItems.get(position);
                Intent intent = new Intent(context , productlist.class);
                intent.putExtra("id", id.getId());
                context.startActivity(intent);
            }
        });
        }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public customfonts.MyTextView head;
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            head=itemView.findViewById(R.id.h1);
            imageView=itemView.findViewById(R.id.imageV);
        }
    }
}
