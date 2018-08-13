package com.pharmacy.pharmacyonnet;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.github.siyamed.shapeimageview.CircularImageView;
import java.util.ArrayList;

import adapters.NavigationAdapter;
import helpers.NavigationItems;

public class NavigationActivity extends AppCompatActivity {

    private Toolbar toolbar;


    private TextView logout;
    private TextView userName;
    private ImageView back;
    private TextView terms;
    private TextView share;
    private TextView rate;

//    private CircularImageView imageView;
    private RelativeLayout relativeClose;

    // It is for horizontal bar chart

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    private NavigationAdapter adapter;
    private ListView list;
    private ArrayList<NavigationItems> items;


    private String[] NAME_AGENTS = {"home","Clients", "Report", "Fill Client Detail", "Settings", "Birthday" ,"Logout","Map"
    };

    private int[] ICON_AGENTS = { R.drawable.back, R.drawable.back, R.drawable.back, R.drawable.back , R.drawable.back , R.drawable.back , R.drawable.back
            ,  R.drawable.back};




    Fragment newFragment;
    android.support.v4.app.FragmentTransaction transaction;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);




        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("");




//        }

        relativeClose = (RelativeLayout)findViewById(R.id.relativeClose);
        userName = (TextView)findViewById(R.id.userName);
//        imageView = (CircularImageView)findViewById(R.id.imageView);
        back = (ImageView)findViewById(R.id.back);
        // terms = (TextView)findViewById(R.id.terms);

//        rate = (TextView)findViewById(R.id.rate);
//
//
//        rate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String url = "https://play.google.com/store/apps/details?id=ws.wolfsoft.wroofbikes";
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);
//
//            }
//        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.closeDrawer(relativeClose);
            }
        });




        transaction = getSupportFragmentManager().beginTransaction();





        toolbar = (Toolbar) findViewById(R.id.toolbar);

        list = (ListView) findViewById(R.id.list);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        items = new ArrayList<NavigationItems>();


        for (int i = 0; i < NAME_AGENTS.length; i++) {

            NavigationItems nav = new NavigationItems(ICON_AGENTS[i],NAME_AGENTS[i] );

            items.add(nav);
        }






        adapter = new NavigationAdapter(NavigationActivity.this, items);
        list.setAdapter(adapter);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //  selectItem_agents(position);
                drawerLayout.closeDrawer(relativeClose);
            }


        });




        initDrawer();


    }
    private void initDrawer() {

        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,toolbar , R.string.drawer_open, R.string.drawer_close
        ) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);



            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);



            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }



//    private void selectItem_agents(int position) {
//
//        transaction = getSupportFragmentManager().beginTransaction();
//
//        switch (position) {
//            case 0:
//
//                txt.setText("Agents");
//
//                    newFragment = new AgentListFragment();
//                    transaction.replace(R.id.frame_container, newFragment);
//                    transaction.addToBackStack(null);
//                    transaction.commit();
//
//
//                break;
//
//            case 1:
//
//                    txt.setText("Clients");
//
//                    Intent it1 = new Intent(NavigationActivity.this, ClieintListActivity.class);
//                    startActivity(it1);
//
//
//
//
//                break;
//
//            case 4:
//
//                txt.setText("Profile");
//
//                    newFragment = new ProfileAgentFragment();
//                    transaction.replace(R.id.frame_container, newFragment);
//                    transaction.addToBackStack(null);
//                    transaction.commit();
//
//
//
//                break;
//
//            case 2:
//
//
//                if (users.getUser().get(0).getDo_code().equals("")){
//
//                    dialogNoDoSelected.show();
//
//
//                }else {
//
//                    txt.setText("Report");
//
//                    newFragment = new PreviousReportListFragment();
//                    transaction.replace(R.id.frame_container, newFragment);
//                    transaction.addToBackStack(null);
//                    transaction.commit();
//
//
//                }
//
//
//                break;
//
//            case 3:
//
//                cd = new ConnectionDetector(NavigationActivity.this);
//                isInternetPresent = cd.isConnectingToInternet();
//
//                if (isInternetPresent) {
//
//                    Intent it2 = new Intent(NavigationActivity.this, ClientDetailPageOneActivity.class);
//                    startActivity(it2);
//
//
//
//                }else {
//                    cd.showAlertDialog(NavigationActivity.this, "Internet Connection",
//                            "You don't have internet connection.", true);
//                }
//
//                break;
//
//
//
//            case 5:
//
//                Intent it7 = new Intent(NavigationActivity.this, BirthdayListActivity.class);
//                startActivity(it7);
//
//                break;
//
//
//
//            case 6:
//                new Clean_data().execute();
//                break;
//
//
//            case 7:
//
//                txt.setText("Map");
//
//                Intent it = new Intent(NavigationActivity.this, MapActivity.class);
//                startActivity(it);
//                break;
//
//
//        }
//
//    }









    private void ReferFriend(){

        Intent shareIntent =
                new Intent(Intent.ACTION_SEND);

        //set the type
        shareIntent.setType("text/plain");

        //add a subject
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,
                "https://play.google.com/store/apps/details?id=ws.wolfsoft.wroofbikes");

        //build the body of the message to be shared
        String shareMessage = "Hi ! I thought you would be interested in this. Hire bike for your next Ride! \n https://goo.gl/EcrJKH";

        //add the message
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                shareMessage);

        //start the chooser for sharing
        startActivity(Intent.createChooser(shareIntent,
                "Wroomrides"));
    }



//    public class Clean_data extends AsyncTask<String, String, String>
//    {
//        //final ProgressDialog dialog = new ProgressDialog(ActivityMain.this);
//        public StringBuilder sb;
//        private String result;
//        private InputStream is;
//        Boolean flag= false;
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            //dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            //dialog.setMessage("Sending Device Id inprocess...");
//            //dialog.show();
//
//        }
//
//        @Override
//        protected String doInBackground(String... aurl)
//        {
//            try
//            {
//
////					AppPrefs appPrefs = new AppPrefs(GCMIntentService.this);
////
////					String user_id_un = appPrefs.getuserun_id();
//
//
//
//
//                String user_id_un = users.getUser().get(0).getUser_id();
//                Log.e("user_id_un", user_id_un);
//
//
//                HttpClient httpclient = new DefaultHttpClient();
//                //http://vmss.co.in/new/admin/webservices/Add_Device_key?deviceregId=5487889
//                HttpPost httppost = new HttpPost(Constants.URL+"SaveGcmIdApp");
//                MultipartEntity data_to_send = new MultipartEntity(
//                        HttpMultipartMode.BROWSER_COMPATIBLE);
//
//                data_to_send.addPart("user_id", new StringBody(user_id_un));
//                Log.e("gcmId", Globals.str_registrationId);
//
//                data_to_send.addPart("gcm_id", new StringBody(""));
//                data_to_send.addPart("key", new StringBody("go"));
//
//
//                httppost.setEntity(data_to_send);
//                HttpResponse response = httpclient.execute(httppost);
//                HttpEntity entity = response.getEntity();
//                is = entity.getContent();
//                result=is.toString();
//
//                if(result != null)
//                {
//                    flag = true;
//                }
//            }catch(Exception e)
//            {
//                Log.e("log_tag", "Error in http connection"+e.toString());
//            }
//
//            if(flag == true)
//            {
//                try
//                {
//                    System.out.println("1");
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
//                    System.out.println("2");
//                    sb = new StringBuilder();
//                    System.out.println("3");
//                    sb.append(reader.readLine() + "\n");
//                    String line="0";
//                    while ((line = reader.readLine()) != null)
//                    {
//                        sb.append(line);
//
//                    }
//
//                    is.close();
//                    result=sb.toString();
//                    System.out.println("else "+result);
//
//                }catch(Exception e)
//                {
//
//                }
//            }
//            return null;
//        }
//        protected void onProgressUpdate(String... progress)
//        {
//            Log.d("ANDRO_ASYNC",progress[0]);
//        }
//
//        @Override
//        protected void onPostExecute(String unused)
//        {
//
//
//            PrefUtils.clearUser(NavigationActivity.this);
//
//            Intent it = new Intent(NavigationActivity.this, LoginActivity.class);
//            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            it.putExtra("EXIT", true);
//            startActivity(it);
//            finish();
//
//            //dialog.dismiss();
//
//            //result=result.trim();
//            // Toast.makeText(getApplicationContext(), "Device id send"+result, Toast.LENGTH_SHORT).show();
//	    		/*if(result.equalsIgnoreCase("Success"))
//	    		{
//	    			// Toast.makeText(getApplicationContext(), "Device id send", Toast.LENGTH_SHORT).show();
//	    		}
//	    		else
//	    		{
//	    			// Toast.makeText(getApplicationContext(), "Device id exist", Toast.LENGTH_SHORT).show();
//	    		}*/
//        }
//    }


    @Override
    public void onBackPressed() {


    }
}
