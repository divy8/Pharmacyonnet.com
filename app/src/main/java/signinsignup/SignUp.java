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
import android.widget.CheckBox;
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
import com.pharmacy.pharmacyonnet.R;

import org.json.JSONObject;

import java.io.IOException;

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

public class SignUp extends AppCompatActivity {




    TextView signin;

    private TextView signUp;
    private TextView create,name,password,email;
    private CheckBox male,female;
    private FirebaseAnalytics mFirebaseAnalytics;
    private SignInButton signInButton;
    private FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    final static int RC_SIGN_IN=1;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Paper.init(SignUp.this);
//        signInButton = findViewById(R.id.sign_in_button);
//        signInButton.setSize(SignInButton.SIZE_STANDARD);


            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.client_id))
                    .requestEmail()
                    .build();

            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);





        signin = (TextView) findViewById(R.id.signin);
        name=findViewById(R.id.name);
        password=findViewById(R.id.passwordtext);
        email=findViewById(R.id.email);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);

        create = (TextView) findViewById(R.id.create);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(SignUp.this, SignIn.class);
                startActivity(it);

            }
        });





        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.e("Email to send",email.getText().toString());

                try {
                    String names[]=name.getText().toString().split(" ",2);
                    if (names.length>1){
                        if(male.isChecked()){
                            new SendDetails(SignUp.this).execute("https://static.pharmacyonnet.com/webservice/Create.php", names[0],names[1],"1",email.getText().toString(),password.getText().toString());
                        }
                        else{
                            new SendDetails(SignUp.this).execute("https://static.pharmacyonnet.com/webservice/Create.php", names[0],names[1],"3",email.getText().toString(),password.getText().toString());
                        }


                    }
                    else {
                        if(male.isChecked()){
                            //postData.put("id_gender","1");
                            new SendDetails(SignUp.this).execute("https://static.pharmacyonnet.com/webservice/Create.php", names[0]," ","1",email.getText().toString(),password.getText().toString());
                        }
                        else{
                            new SendDetails(SignUp.this).execute("https://static.pharmacyonnet.com/webservice/Create.php", names[0]," ","3",email.getText().toString(),password.getText().toString());
                        }


                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });




    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
    }

    public void firebasesignup(String email,String password){

        final FirebaseAuth mAuth;
        mAuth=FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });



    }

}

class SendDetails extends AsyncTask<String, String, String> {
    SignUp s=new SignUp();

    private Context context;
    OkHttpClient client = new OkHttpClient();
    public SendDetails(Context context) {
        this.context=context;
    }

    static String id,firstname,lastname,email,id_gender,id_shop,id_shop_group,id_default_group,status,message;

    @Override
    protected String doInBackground(final String... params) {

        String data = "";
        try {

            final RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("firstname", params[1])
                    .addFormDataPart("lastname", params[2])
                    .addFormDataPart("id_gender",params[3])
                    .addFormDataPart("email",params[4])
                    .addFormDataPart("passwd", params[5])
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

                public void parse(String result)
                {


                    try {
                        Log.e("response",result);
                        JSONObject obj = new JSONObject(result);
                        status = obj.getString("status");
                        if (status.equalsIgnoreCase("true")) {
                            Paper.book().write("persondetails",result);
                            Handler handler = new Handler(Looper.getMainLooper());

                            handler.post(new Runnable() {

                                @Override
                                public void run() {

                                    s.firebasesignup(params[4],params[5]);

                                }
                            });

                            Intent it = new Intent(context, ShoppyMenuActivity.class);
                            context.startActivity(it);
                        }
                        else
                        {
                            message = obj.getString("message");
                            Handler handler = new Handler(Looper.getMainLooper());

                            handler.post(new Runnable() {

                                @Override
                                public void run() {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setTitle("Warning!!");
                                    builder.setMessage(message);
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
                    }
                    catch (Exception e){

                    }



                }

            } );

        }
        catch (Exception e) {
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

