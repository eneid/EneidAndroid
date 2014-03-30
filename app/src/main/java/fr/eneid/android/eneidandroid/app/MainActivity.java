package fr.eneid.android.eneidandroid.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fr.eneid.android.eneidandroid.beans.Message;


public class MainActivity extends ActionBarActivity {

    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = (ListView) findViewById(R.id.timeLine);

        new TimeLineTask().execute("http://eneid-api.herokuapp.com/api/timeline", "user", "password");

        Button action = (Button) findViewById(R.id.action);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actionIntent = new Intent(MainActivity.this, ActionsActivity.class);
                startActivity(actionIntent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // AsyncTask To get TimeLine Data
    private class TimeLineTask extends AsyncTask<String, Void, List<Message>> {

        @Override
        protected List<Message> doInBackground(String... strings) {
            List<Message> messages = new ArrayList<Message>();


            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(strings[0]);

            String email = strings[1];
            String password = strings[2];

//            StringBuilder total = null;
//            try {
//                String token = Base64.encodeToString((email + ":" + password).getBytes("UTF-8"), Base64.DEFAULT);
//                httpGet.setHeader(new BasicHeader("Authorization", "Basic " + token));
//
//                HttpResponse response = httpclient.execute(httpGet);
//                HttpEntity ht = response.getEntity();
//                BufferedHttpEntity buf = new BufferedHttpEntity(ht);
//                InputStream is = buf.getContent();
//                BufferedReader r = new BufferedReader(new InputStreamReader(is));
//                total = new StringBuilder();
//                String line;
//                while ((line = r.readLine()) != null) {
//                    total.append(line);
//                }
//            } catch (IOException e) {
//                Log.e("TimeLineTask", e.getMessage());
//            }

            String total = "[{\"id\":1,\"author\":{\"email\":\"jj@gmail.com\",\"firstName\":\"Jean jacques\",\"name\":\"Martin\"},\"date\":1396127618091,\"contents\":\"Il a mangé !\",\"action\":null}," +
                    "{\"id\":2,\"author\":{\"email\":\"jj@gmail.com\",\"firstName\":\"Jean jacques\",\"name\":\"Cousine\"},\"date\":1396127618091,\"contents\":\"Il va bien !\",\"action\":null}," +
                    "{\"id\":3,\"author\":{\"email\":\"jj@gmail.com\",\"firstName\":\"Jean jacques\",\"name\":\"Toto\"},\"date\":1396127618091,\"contents\":\"Il a besoin d'argent !\",\"action\":null}," +
                    "{\"id\":4,\"author\":{\"email\":\"jj@gmail.com\",\"firstName\":\"Jean jacques\",\"name\":\"Le snack d'en face\"},\"date\":1396127618091,\"contents\":\"Il a besoin d'un Kebab\",\"action\":null}," +
                    "{\"id\":5,\"author\":{\"email\":\"jj@gmail.com\",\"firstName\":\"Jean jacques\",\"name\":\"Le boulanger\"},\"date\":1396127618091,\"contents\":\"Et oui il va bien :)\",\"action\":null}" +

                    "]";

            if(total != null) {
                Gson gson = new Gson();
                Type messageType = new TypeToken<ArrayList<Message>>(){}.getType();
                messages = gson.fromJson(total.toString(), messageType);
            }


            return messages;
        }

        @Override
        protected void onPostExecute(List<Message> messages) {
            TimeLineAdapter timeLineAdapter = new TimeLineAdapter(MainActivity.this, messages);
            mList.setAdapter(timeLineAdapter);
        }
    }

}
