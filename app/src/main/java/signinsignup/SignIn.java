package signinsignup;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.pharmacy.pharmacyonnet.MainActivity;
import com.pharmacy.pharmacyonnet.R;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import menu.ShoppyMenuActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;


public class SignIn extends AppCompatActivity {
    TextView signup,user,password;


    String x="",y="";
    private TextView signin1;
    private FirebaseAnalytics mFirebaseAnalytics;
    private SignInButton signInButton;
    private FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    final static int RC_SIGN_IN=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        user=findViewById(R.id.email);
        password=findViewById(R.id.pass);

        signup = (TextView)findViewById(R.id.signup);
        signin1 = (TextView)findViewById(R.id.signin1);


        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        String details=Paper.book().read("persondetails");
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(FirebaseAuth.getInstance().getCurrentUser()!=null&& account!=null&&details.equalsIgnoreCase(" ")){
            Intent it = new Intent(SignIn.this, ShoppyMenuActivity.class);
            startActivity(it);
        }
        else {
            // Configure Google Sign In
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.client_id))
                    .requestEmail()
                    .build();
            mAuth = FirebaseAuth.getInstance();
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signIn();
                }
            });
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(SignIn.this, SignUp.class);
                startActivity(it);

            }
        });

        signin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Sendsign(SignIn.this).execute("https://static.pharmacyonnet.com/module/applicationdataexport/customerlogin",user.getText().toString(),password.getText().toString());
//                Intent it = new Intent(SignIn.this, SignUp.class);
//                startActivity(it);

            }
        });


    }




    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


public void entrytofirebase(final String e, final String p){

    mAuth = FirebaseAuth.getInstance();
    mAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();
            } else {
                // If sign in fails, display a message to the user.
                ////////////////////////////////////////////////////////////
                mAuth.createUserWithEmailAndPassword(e, p);
                ///////////////////////////////////////////////////////////
            }

            // ...
        }
    });;

}


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            OkHttpClient client = new OkHttpClient();
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.e("Email",user.getEmail());
                            ///////////////////////////////////////////////////////////////////////////////
                            String names[]=user.getDisplayName().split(" ",2);
                            if(names.length>1){
                                x=names[0];
                                y=names[1];
                            }
                            else
                            {
                                x=names[0];
                                y=" ";
                            }
                            new sendtour(getApplicationContext()).execute("http://static.pharmacyonnet.com/webservice/gsignup.php",x,y,user.getEmail());
                            /////////////////////////////////////////////////////////////////////////////////
                            Intent it = new Intent(SignIn.this, ShoppyMenuActivity.class);
                            startActivity(it);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.post(new Runnable() {

                                @Override
                                public void run() {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SignIn.this);
                                    builder.setTitle("Warning!!");
                                    builder.setMessage("All ready loged in");
                                    builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    builder.show();
                                }
                            });
                        }

                        // ...
                    }
                });
    }


//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//            // Signed in successfully, show authenticated UI.
//            Log.e("Successful!!","1");
//            String idToken=account.getIdToken();
//            Intent it = new Intent(SignIn.this, ShoppyMenuActivity.class);
//            startActivity(it);
//
//        } catch (ApiException e) {
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
//            Handler handler = new Handler(Looper.getMainLooper());
//
//            handler.post(new Runnable() {
//
//                @Override
//                public void run() {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(SignIn.this);
//                    builder.setTitle("Warning!!");
//                    builder.setMessage("Unable to Login Due to Some Error");
//                    builder.setPositiveButton("Try Again",new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//                    builder.show();
//                }
//            });
//        }
//    }

}

class sendtour extends AsyncTask<String,String,String>{


    private Context context;
    OkHttpClient client = new OkHttpClient();
    FirebaseAuth firebaseAuth;

    sendtour(Context context){

     this.context=context;

    }

    @Override
    protected String doInBackground(String... strings) {

        try {

            final RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("firstname", strings[1])
                    .addFormDataPart("lastname", strings[2])
                    .addFormDataPart("email", strings[3])
                    .addFormDataPart("passwd", "123456789")
                    .build();

            final Request request = new Request.Builder()
                    .url(strings[0])
                    .post(requestBody)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                    Log.e("HttpService", "onFailure() Request was: " + request);
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    String s=response.body().string();
                    Paper.book().write("persondetails",s);
                    Log.e("personal data",s);


                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}

class Sendsign extends AsyncTask<String, String, String>{
    SignIn s=new SignIn();
    private Context context;
    OkHttpClient client = new OkHttpClient();
    FirebaseAuth firebaseAuth;

    public Sendsign(Context context) {
        this.context = context;
    }

    static String id, firstname, lastname, email, id_gender, id_shop, id_shop_group, id_default_group, status, message;

    @Override
    protected String doInBackground(final String... params) {

        String data = "";
        try {

            final RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("email", params[1])
                    .addFormDataPart("passwd", params[2])
                    .build();

            final Request request = new Request.Builder()
                    .url(params[0])
                    .post(requestBody)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                    Log.e("HttpService", "onFailure() Request was: " + request);
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {

                    parse(response.body().string());
                }

                public void parse(String result) {

                    try {

                        Log.e("response", result);
                        JSONObject obj = new JSONObject(result);
                        status = obj.getString("status");
                        if (status.equalsIgnoreCase("true")) {
                            Paper.book().write("persondetails",result);
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.post(new Runnable() {

                                @Override
                                public void run() {

                                    s.entrytofirebase(params[1],params[2]);

                                }
                            });
                            Intent it = new Intent(context, ShoppyMenuActivity.class);
                            context.startActivity(it);
                        } else {
                            message = obj.getString("message");
                            Handler handler = new Handler(Looper.getMainLooper());

                            handler.post(new Runnable() {

                                @Override
                                public void run() {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setTitle("Warning!!");
                                    builder.setMessage(message);
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    builder.show();
                                }
                            });
                        }
                    } catch (Exception e) {

                    }


                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }



    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Log.e("TAG", result);
        // this is expecting a response code to be sent from your server upon receiving the POST data
    }
}