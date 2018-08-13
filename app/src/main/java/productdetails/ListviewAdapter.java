package productdetails;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.pharmacy.pharmacyonnet.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import productdisplay.productdisplayactivity;

public class ListviewAdapter extends BaseAdapter {

    Context context;

    ArrayList<BeanclassList> bean;
    Typeface fonts1,fonts2;
    RatingBar ratingbar;
    Activity main;


    public ListviewAdapter(Activity activity, Context context, ArrayList<BeanclassList> bean) {

        this.main = activity;
        this.context = context;
        this.bean = bean;
    }
    void updateDataSet(ArrayList<BeanclassList> bean){
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.size();
    }

    @Override
    public Object getItem(int position) {
        return bean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        fonts1 =  Typeface.createFromAsset(context.getAssets(),
                "fonts/MavenPro-Regular.ttf");

        fonts2 = Typeface.createFromAsset(context.getAssets(),
                "fonts/MavenPro-Regular.ttf");

        final ViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview,null);

            viewHolder = new ViewHolder();

            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            viewHolder.cutprice = (TextView) convertView.findViewById(R.id.cutprice);
            viewHolder.discount = (TextView) convertView.findViewById(R.id.discount);
            viewHolder.ratingtext = (TextView) convertView.findViewById(R.id.ratingtext);
            viewHolder.fav1 = (ImageView) convertView.findViewById(R.id.fav1);
            viewHolder.fav2 = (ImageView) convertView.findViewById(R.id.fav2);


            viewHolder.title.setTypeface(fonts1);
            viewHolder.price.setTypeface(fonts1);
            viewHolder.cutprice.setTypeface(fonts1);
            viewHolder.discount.setTypeface(fonts1);
            viewHolder.ratingtext.setTypeface(fonts1);



//        ***********ratingBar**********
            ratingbar = (RatingBar) convertView.findViewById(R.id.ratingbar);
            LayerDrawable stars = (LayerDrawable) ratingbar.getProgressDrawable();
           stars.getDrawable(2).setColorFilter(Color.parseColor("#f8d64e"), PorterDuff.Mode.SRC_ATOP);

            convertView.setTag(viewHolder);

            viewHolder.cutprice.setPaintFlags(viewHolder.cutprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else {

            viewHolder = (ViewHolder)convertView.getTag();
        }








        final BeanclassList bean = (BeanclassList)getItem(position);




       // viewHolder.image.setImageResource(bean.getImage());


        //ratingbar.setRating(Float.parseFloat(bean.getRatingtext()));
        //ratingbar.setNumStars(Integer.parseInt(bean.getRatingtext()));

        viewHolder.title.setText(bean.getTitle());
        viewHolder.price.setText(bean.getPrice());

        if(bean.getDiscount()!=null)
        {
            viewHolder.discount.setText(bean.getDiscount());
            viewHolder.cutprice.setText(bean.getCutprice());

        }
        else{
            viewHolder.discount.setVisibility(View.GONE);
            viewHolder.cutprice.setVisibility(View.GONE);
        }
        viewHolder.ratingtext.setText(bean.getRatingtext()+"/5");

        viewHolder.mSmallBang = SmallBang.attach2Window(main);




        viewHolder.fav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                viewHolder.fav2.setVisibility(View.VISIBLE);
                viewHolder.fav1.setVisibility(View.GONE);
                like(v);

            }

            public void like(View view){
                viewHolder.fav2.setImageResource(R.drawable.f4);
                viewHolder.mSmallBang.bang(view);
                viewHolder.mSmallBang.setmListener(new SmallBangListener() {
                    @Override
                    public void onAnimationStart() {

                    }

                    @Override
                    public void onAnimationEnd() {

                    }


                });
            }

        });


        viewHolder.fav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                viewHolder.fav2.setVisibility(View.GONE);
                viewHolder.fav1.setVisibility(View.VISIBLE);


            }
        });

        Picasso
                .with(context)
                .load(bean.getImage())
                //.resize(1000, 550)
                // resizes the image to these dimensions (in pixel). does not respect aspect ratio
                .into(viewHolder.image);


        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , productdisplayactivity.class);
                intent.putExtra("id", bean.getId());
                intent.putExtra("discount", bean.getDiscount());
                context.startActivity(intent);
            }
        });

        viewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , productdisplayactivity.class);
                intent.putExtra("id", bean.getId());
                intent.putExtra("discount", bean.getDiscount());
                context.startActivity(intent);
            }
        });


        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView title;
        TextView price;
        TextView cutprice;
        TextView discount;
        TextView ratingtext;
        ImageView fav1;
        ImageView fav2;
        SmallBang mSmallBang;


    }
}
