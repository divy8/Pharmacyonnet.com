package cartlist;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pharmacy.pharmacyonnet.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.paperdb.Paper;
import productdisplay.cart;
import shoppingbag.BuyActivity;

public class CartActivity extends AppCompatActivity{



    private ListView listview;
    private TextView pay,nocart;

    Typeface fonts1,fonts2;
    static ImageView img_product;

    private int[] IMAGE = {R.drawable.bag_my, R.drawable.bag_my, R.drawable.bag_my,
            R.drawable.bag_my,
            R.drawable.bag_my};

    private String[] TITLE = {"Teak & Steel Petanque Set", "Lemon Peel Baseball", "Seil Marschall Hiking Pack", "Teak & Steel Petanque Set"
            , "Lemon Peel Baseball"};


    private String[] PRICE = {"$ 220.00","$ 49.00","$ 320.00","$ 220.00","$ 49.00"};
    private ArrayList<CartListBeanlist> Bean;
    private CartListBaseAdapter baseAdapter;

    private Toolbar toolbar;
    private TextView toolbarTitle;

    private FirebaseAuth mAuth;

    private DatabaseReference database_order_user;

    ImageView home,offer,fav,bag,noti;
    LinearLayout home0,offer0,fav0,bag0,noti0;

    RecyclerView user_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartlist);

        String id = mAuth.getInstance().getCurrentUser().getUid();
        database_order_user = FirebaseDatabase.getInstance().getReference().child("user").child(id).child("cart");
        database_order_user.keepSynced(true);


        user_order = findViewById(R.id.order_user);
        user_order.setHasFixedSize(true);
        user_order.setLayoutManager(new LinearLayoutManager(this));

        setToolBar();



        home = (ImageView) findViewById(R.id.home);
        home0 =(LinearLayout)findViewById(R.id.home0);
        offer = (ImageView) findViewById(R.id.offer);
        offer0 =(LinearLayout)findViewById(R.id.offer0);
        fav = (ImageView) findViewById(R.id.fav);
        fav0 =(LinearLayout)findViewById(R.id.fav0);
        bag = (ImageView) findViewById(R.id.bag);
        bag0 =(LinearLayout)findViewById(R.id.bag0);
        noti = (ImageView) findViewById(R.id.noti);
        noti0 =(LinearLayout)findViewById(R.id.noti0);


        home.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
        offer.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
        fav.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);
        bag.setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.MULTIPLY);
        noti.setColorFilter(getResources().getColor(R.color.boticon), PorterDuff.Mode.MULTIPLY);


        home0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickb("1");
//
//                Intent it = new Intent(CartListActivity.this, MenWomenCategoriesActivity.class);
//                startActivity(it);

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

//                Intent it = new Intent(CartListActivity.this, CartListActivity.class);
//                startActivity(it);


            }
        });
        noti0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickb("5");

//                Intent it = new Intent(CartListActivity.this, ShoppyNotificationActivity.class);
//                startActivity(it);

            }
        });




        listview = (ListView)findViewById(R.id.listview);
        pay = (TextView)findViewById(R.id.pay);


        if(database_order_user!=null){
        user_order.setAdapter(show());
        }
        else {
            nocart=findViewById(R.id.nocart);
                nocart.setVisibility(View.VISIBLE);
        }


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CartActivity.this, BuyActivity.class);
                startActivity(it);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    public static class cartViewHolder extends RecyclerView.ViewHolder{
        View mview;
        ImageView imageView;
        public cartViewHolder(View itemView){
            super(itemView);
            mview = itemView;

            imageView=mview.findViewById(R.id.delete);
        }
        public void setProduct_price(String price){
            TextView prod_price = mview.findViewById(R.id.price);
            prod_price.setText(price);
        }
        public void setProduct_name(String name){
            TextView nam = mview.findViewById(R.id.title);
            nam.setText(name);
        }
        public void setProduct_url(String url){
            try {
                img_product = mview.findViewById(R.id.image);
                Picasso
                        .with(mview.getContext())
                        .load(url)
                        //.resize(1000, 550)
                        // resizes the image to these dimensions (in pixel). does not respect aspect ratio
                        .into(img_product);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private FirebaseRecyclerAdapter show()
    {
        String id = mAuth.getInstance().getCurrentUser().getUid();
        database_order_user = FirebaseDatabase.getInstance().getReference().child("user").child(id).child("cart");
        database_order_user.keepSynced(true);
        FirebaseRecyclerAdapter<cart, cartViewHolder>firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<cart, cartViewHolder>(cart.class,R.layout.cart_list,cartViewHolder.class,database_order_user) {

                    @Override
                    public cart getItem(int position) {
                        return super.getItem(position);
                    }

                    @Override
                    public long getItemId(int position) {
                        return super.getItemId(position);
                    }

                    @Override
                    protected void populateViewHolder(cartViewHolder viewHolder, final cart model, int position) {

                        viewHolder.setProduct_name(model.getName());
                        viewHolder.setProduct_price(model.getPrice());
                        viewHolder.setProduct_url(model.getImage());
                        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                DatabaseReference remove=database_order_user.child(model.getId());
                                remove.keepSynced(true);
                                remove.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){
                                            user_order.setAdapter(show());
                                        }
                                        else
                                        {
                                            Toast.makeText(getBaseContext(), "item can not remove", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });
                    }
                };
        return firebaseRecyclerAdapter;
    }

    private void setToolBar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView)toolbar.findViewById(R.id.txt);
        toolbarTitle.setText("Cart");



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

}

