package home;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.pharmacy.pharmacyonnet.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridviewAdapter extends BaseAdapter {

    Context context;

    ArrayList<collection1> images;

    //public Integer images[]={R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4};
    public GridviewAdapter(Context context,ArrayList<collection1> images) {
        this.images = images;
        this.context = context;
    }

//    void updateDataSet(ArrayList<Collection_images> images){
//        this.images = images;
//    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.home_grid, null);


            viewHolder = new ViewHolder();
//            viewHolder.progressBar=(ProgressBar) convertView.findViewById(R.id.progress_bar);
            convertView.setTag(viewHolder);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }


            collection1 image =images.get(position);

       // viewHolder.image.setImageResource(images[position]);

        Picasso
                .with(context)
                .load(image.getUrl())
                //.resize(1000, 550)
                // resizes the image to these dimensions (in pixel). does not respect aspect ratio
                .into(viewHolder.image);



        return convertView;

    }

    private class ViewHolder {
        ImageView image;
    }
}


