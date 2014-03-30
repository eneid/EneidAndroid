package fr.eneid.android.eneidandroid.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class ActionsActivity extends ActionBarActivity implements View.OnClickListener {

    private View mActionsView;
    private View mActionsStatusView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);


        findViewById(R.id.action1).setOnClickListener(this);
        findViewById(R.id.action2).setOnClickListener(this);
        findViewById(R.id.action3).setOnClickListener(this);
        findViewById(R.id.action4).setOnClickListener(this);
        findViewById(R.id.action5).setOnClickListener(this);

        mActionsView = findViewById(R.id.login_form);
        mActionsStatusView = findViewById(R.id.login_status);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actions, menu);
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

    @Override
    public void onClick(View view) {
        Button action = (Button) view;
        int actionId = 0;
        if (action.getId() == 1) {
            actionId = 1;
        } else if (action.getId() == 2) {
            actionId = 2;
        } else if (action.getId() == 3) {
            actionId = 3;
        } else if (action.getId() == 4) {
            actionId = 4;
        } else if (action.getId() == 5) {
            actionId = 5;
        }
        showProgress(true);
        new TimeLineTask().execute(actionId);
    }

    // AsyncTask To get TimeLine Data
    private class TimeLineTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... actionsId) {
            try {
                String token = Base64.encodeToString(("user:password").getBytes("UTF-8"), Base64.DEFAULT);
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://eneid-api.herokuapp.com/api/timeline/action/" + actionsId[0]);
                httpPost.setHeader(new BasicHeader("Authorization", "Basic " + token));
                HttpResponse response = httpClient.execute(httpPost);
                int statusCode = response.getStatusLine().getStatusCode();
                return statusCode;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.e("ActionsActivity", e.getStackTrace().toString());
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                Log.e("ActionsActivity", e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("ActionsActivity", e.getStackTrace().toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer statusCode) {
            showProgress(false);
            if(statusCode != null) {
                Toast.makeText(getApplicationContext(), "Message envoyé", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mActionsStatusView.setVisibility(View.VISIBLE);
            mActionsStatusView.animate()
                    .setDuration(shortAnimTime)
                    .alpha(show ? 1 : 0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mActionsStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
                        }
                    });

//            mActionsView.setVisibility(View.VISIBLE);
//            mActionsView.animate()
//                    .setDuration(shortAnimTime)
//                    .alpha(show ? 0 : 1)
//                    .setListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            mActionsView.setVisibility(show ? View.GONE : View.VISIBLE);
//                        }
//                    });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mActionsStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
            mActionsView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
