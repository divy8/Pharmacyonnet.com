package myAccount;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.pharmacy.pharmacyonnet.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import io.paperdb.Paper;
import menu.ShoppyMenuActivity;

public class myaccount extends AppCompatActivity {


    private Toolbar toolbar;
    private TextView toolbarTitle;
    TextView user_name, user_email;
    ImageView user_photo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);

        setToolBar();

        user_email = findViewById(R.id.user_email_id);
        user_name = findViewById(R.id.user_profile_name);
        user_photo = findViewById(R.id.user_profile_photo);


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(myaccount.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personEmail = acct.getEmail();
            Uri photo=acct.getPhotoUrl();


            user_email.setText(personEmail);
            user_name.setText(personName);

            Picasso.with(getApplicationContext()).load(photo.toString())
                    .resize(400, 400)
                    .into(user_photo, new Callback() {
                        @Override
                        public void onSuccess() {
                            Bitmap imageBitmap = ((BitmapDrawable) user_photo.getDrawable()).getBitmap();
                            RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
                            imageDrawable.setCircular(true);
                            imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                            user_photo.setImageDrawable(imageDrawable);
                        }

                        @Override
                        public void onError() {
                            user_photo.setImageResource(R.drawable.pimage);
                        }
                    });



        }
        else {
            String details= Paper.book().read("persondetails");

            try {
                JSONObject json=new JSONObject(details);
                String fname=json.getString("firstname");
                String lname=json.getString("lastname");
                String email=json.getString("email");

                String name=fname+" "+lname;
                user_email.setText(email);
                user_name.setText(name);

                Picasso.with(getApplicationContext()).load(R.drawable.pimage)
                        .resize(400, 400)
                        .into(user_photo, new Callback() {
                            @Override
                            public void onSuccess() {
                                Bitmap imageBitmap = ((BitmapDrawable) user_photo.getDrawable()).getBitmap();
                                RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
                                imageDrawable.setCircular(true);
                                imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                                user_photo.setImageDrawable(imageDrawable);
                            }

                            @Override
                            public void onError() {
                                user_photo.setImageResource(R.drawable.pimage);
                            }
                        });


            }
            catch (JSONException j)
            {
                j.printStackTrace();
            }

        }
    }



    private void setToolBar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView)toolbar.findViewById(R.id.txt);
        toolbarTitle.setText("My Account");
        toolbarTitle.setTextColor(Color.parseColor("#ffffff"));

        toolbar.setBackgroundColor(Color.parseColor("#07617D"));

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("");


        toolbar.setNavigationIcon(R.drawable.back_white);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


    }
}
