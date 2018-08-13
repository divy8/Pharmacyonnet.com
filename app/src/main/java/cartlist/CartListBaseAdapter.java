package cartlist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.pharmacy.pharmacyonnet.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import productdisplay.cart;

public class CartListBaseAdapter extends BaseAdapter{




    Context context;

    ArrayList<cart> bean;




    private int number = 01;

    Typeface fonts1,fonts2;





    public CartListBaseAdapter(Context context, ArrayList<cart> bean) {


        this.context = context;
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
                "fonts/Lato-Light.ttf");

        fonts2 = Typeface.createFromAsset(context.getAssets(),
                "fonts/Lato-Regular.ttf");

        ViewHolder viewHolder = null;

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.cart_list,null);

            viewHolder = new ViewHolder();

            viewHolder.image = (ImageView)convertView.findViewById(R.id.image);
            viewHolder.title = (TextView)convertView.findViewById(R.id.title);

            viewHolder.price = (TextView)convertView.findViewById(R.id.price);

            viewHolder.text = (TextView)convertView.findViewById(R.id.text);



            viewHolder.title.setTypeface(fonts2);

            viewHolder.text.setTypeface(fonts1);
            viewHolder.price.setTypeface(fonts2);

            convertView.setTag(viewHolder);


        }else {

            viewHolder = (ViewHolder)convertView.getTag();
        }







        cart bean = (cart) getItem(position);

//        viewHolder.image.setImageResource(bean.getImage());
        viewHolder.title.setText(bean.getName());
        viewHolder.price.setText(bean.getPrice());

        Picasso
                .with(context)
                .load(bean.getImage())
                //.resize(1000, 550)
                // resizes the image to these dimensions (in pixel). does not respect aspect ratio
                .into(viewHolder.image);




//        number = 01;
//        viewHolder.text.setText(""+number);
//
//        final ViewHolder finalViewHolder = viewHolder;
//        viewHolder.min.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (number == 1){
//                    finalViewHolder.text.setText("" + number);
//            }
//
//                if (number > 1){
//
//                    number = number -1;
//                    finalViewHolder.text.setText(""+number);
//                }
//
//            }
//        });
//
//        final ViewHolder finalViewHolder1 = viewHolder;
//        viewHolder.plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (number == 10) {
//                    finalViewHolder1.text.setText("" + number);
//                }
//
//                if (number < 10) {
//
//                    number = number + 1;
//                    finalViewHolder1.text.setText("" + number);
//
//                }
//
//
//
//
//            }
//        });




        return convertView;
    }









    private class ViewHolder{
        ImageView image;
        TextView title;

        TextView price;

        TextView text;














    }


}
