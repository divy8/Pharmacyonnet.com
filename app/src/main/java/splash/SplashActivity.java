package splash;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import com.pharmacy.pharmacyonnet.R;

import io.paperdb.Paper;
import menu.ShoppyMenuActivity;
import signinsignup.SignIn;
import signinsignup.SignUp;

public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 5000;
    String splash="0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Paper.init(getApplicationContext());


        if(Paper.book().read("persondetails")==null)
        {

            SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
            if(!preferences.getBoolean("firsttime",false)) {
                setAnimation();
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent i = new Intent(SplashActivity.this, SignIn.class);
                        startActivity(i);

                        finish();
                    }
                }, SPLASH_TIME_OUT);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putBoolean("firsttime",true);
                editor.apply();
            }
            else{
                Intent i = new Intent(SplashActivity.this, SignIn.class);
                startActivity(i);
            }

            }
            else
        {
            Intent i = new Intent(SplashActivity.this, ShoppyMenuActivity.class);
            startActivity(i);
        }
        }



    private void setAnimation() {
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "scaleX", 5.0F, 1.0F);
        scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimation.setDuration(2000);
        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "scaleY", 5.0F, 1.0F);
        scaleYAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleYAnimation.setDuration(2000);
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "alpha", 0.0F, 1.0F);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimation.setDuration(2000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleXAnimation).with(scaleYAnimation).with(alphaAnimation);
        animatorSet.setStartDelay(2000);
        animatorSet.start();



        // findViewById(R.id.imagelogo2).setAlpha(1.0F);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.fade);
        // findViewById(R.id.imagelogo2).startAnimation(anim);

    }
}