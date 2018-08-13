package personal;


public class Data_Fetcher {}
  //  static ArrayList<Main_Catagories> main_catagories = new ArrayList<Main_Catagories>();
    //static ArrayList<Main_Catagories> main_catagoriesArrayList;



    /*public static void loaddata()
    {
        // child_catagoriesArrayList=new ArrayList<Child_catagories>();
        //main_catagoriesArrayList=new ArrayList<Main_Catagories>();
        String url = "https://static.pharmacyonnet.com/module/applicationdataexport/getcategory?id=20";
        OkHttpClient client = new OkHttpClient();
        //ButterKnife.bind(this);
        if (isNetworkAvailabe()) {
            Request request = new Request.Builder().url(url).build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Log.e(TAG, "Failure");
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        Log.e(TAG, jsonData);
                        JSONArray jsonArray = new JSONArray(jsonData);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            String id=obj.getString("id");
                            String name = obj.getString("name");
                            String child_cat = obj.getString("child_cat");
                            String pos=obj.getString("position");

                            main_catagoriesArrayList=new ArrayList<Main_Catagories>();
                            JSONArray childsonArray=new JSONArray(child_cat);
                            for (int x=0;x<childsonArray.length();x++) {
                                JSONObject childobj=childsonArray.getJSONObject(x);
                                String childid=childobj.getString("id");
                                String childpos=childobj.getString("position");
                                String childname=childobj.getString("name");
                                Main_Catagories cc=new Main_Catagories(childid,childname,childpos);
                                main_catagoriesArrayList.add(cc);
                            }

                            Main_Catagories mc=new Main_Catagories(id,name,pos);
                            mc.setChildcatagories(main_catagoriesArrayList);
                            main_catagories.add(mc);
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception Caught ", e);
                    } catch (JSONException j) {
                        Log.e(TAG, "Exception Caught ", j);

                    }
                }

            });
        }
        else{
           Toast.makeText(this, "Network is Unavailable!",
                    Toast.LENGTH_LONG).show();
        }

    }


    private static boolean isNetworkAvailabe() {
        try {
            ConnectivityManager connec =
                    (ConnectivityManager)this.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connec.getActiveNetworkInfo();
            boolean isAvailable = false;

            if (networkInfo != null && networkInfo.isConnected()) {
                isAvailable = true;
            }
            return isAvailable;
        }

        catch (Exception e){}

        return false;
    }
}*/
