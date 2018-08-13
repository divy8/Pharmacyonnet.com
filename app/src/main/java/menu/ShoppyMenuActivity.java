package menu;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.pharmacy.pharmacyonnet.R;
import com.pusher.pushnotifications.PushNotifications;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import home.HomeActivity;
import io.paperdb.Paper;
import myAccount.myaccount;
import orders.ShoppyOrderActivity;
import signinsignup.SignIn;

public class ShoppyMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout frame_container;


    TextView name,id;
    ImageView imageView;
    boolean doubleBackToExitPressedOnce = false;
    Fragment newFragment;
    android.support.v4.app.FragmentTransaction transaction;

    GoogleSignInClient mGoogleSignInClient;

    private Toolbar toolbar;
    private TextView toolbarTitle;
    String personName,personGivenName,personEmail;
    String personPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidebar);


        Paper.init(getApplicationContext());


        setToolBar();
        frame_container = (FrameLayout)findViewById(R.id.frame_container);

        transaction = getSupportFragmentManager().beginTransaction();
        newFragment = new HomeActivity();
        transaction.replace(R.id.frame_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();


        PushNotifications.start(getApplicationContext(), "a8b77047-8e32-46f1-8d05-b720e372d3cb");
        PushNotifications.subscribe("hello");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();




        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header=navigationView.getHeaderView(0);
        name=header.findViewById(R.id.name);
        id=header.findViewById(R.id.emailid);
        imageView=header.findViewById(R.id.imageView);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(ShoppyMenuActivity.this);
        if (acct != null) {
            personName = acct.getDisplayName();
            personGivenName = acct.getGivenName();
            personEmail = acct.getEmail();
            Uri uri = acct.getPhotoUrl();
            name.setText(personName);
            id.setText(personEmail);
            if (uri != null) {
                personPhoto = uri.toString();
                Log.e("photo url", personPhoto);
                Picasso.with(getApplicationContext()).load(personPhoto)
                        .resize(150, 150)
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                Bitmap imageBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                                RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
                                imageDrawable.setCircular(true);
                                imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                                imageView.setImageDrawable(imageDrawable);
                            }

                            @Override
                            public void onError() {
                                imageView.setImageResource(R.drawable.pimage);
                            }
                        });
            }
            else
                imageView.setImageResource(R.drawable.pimage);
        }
        else
        {
            String persondetails=Paper.book().read("persondetails");
        try {
        JSONObject obj = new JSONObject(persondetails);
        String fname=obj.getString("firstname");
        String lname=obj.getString("lastname");
        String email=obj.getString("email");

        String s=fname+" "+lname;
        name.setText(s);
        id.setText(email);
            Picasso.with(getApplicationContext()).load(R.drawable.pimage)
                    .resize(150, 150)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            Bitmap imageBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                            RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
                            imageDrawable.setCircular(true);
                            imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                            imageView.setImageDrawable(imageDrawable);
                        }

                        @Override
                        public void onError() {
                            imageView.setImageResource(R.drawable.pimage);
                        }
                    });

            }
            catch (Exception e){
            e.printStackTrace();
        }

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
            }
        }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        drawer.closeDrawer(GravityCompat.START);

        transaction = getSupportFragmentManager().beginTransaction();
        newFragment = new HomeActivity();
        transaction.replace(R.id.frame_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        if(item.getItemId()==R.id.logout)
        {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.client_id))
                    .requestEmail()
                    .build();

            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            signOut();
        }
        else if(item.getItemId()==R.id.account)
        {
            Intent it=new Intent(ShoppyMenuActivity.this, myaccount.class);
            startActivity(it);
        }
        else if(item.getItemId()==R.id.orders){
            Intent it=new Intent(ShoppyMenuActivity.this, ShoppyOrderActivity.class);
            startActivity(it);

        }


        return true;
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
//        Paper.book().delete("persondetails");
//        Intent it=new Intent(ShoppyMenuActivity.this,SignIn.class);
//        startActivity(it);

        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Paper.book().delete("persondetails");
                        Intent it=new Intent(ShoppyMenuActivity.this,SignIn.class);
                        startActivity(it);
                    }
                });
    }

    private void setToolBar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView)toolbar.findViewById(R.id.txt);
        toolbarTitle.setText("Pharmacyonnet");
        toolbarTitle.setTextColor(Color.parseColor("#ffffff"));

        toolbar.setBackgroundColor(Color.parseColor("#07617D"));

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("");


        toolbar.setNavigationIcon(R.drawable.menu2);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


    }


}
