package fr.eneid.android.eneidandroid.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mList = (ListView) findViewById(R.id.timeLine);

        List<Message> messages = new ArrayList<Message>();

        messages.add(new Message(1, "Sébastian Lemerdy", "http://www.gravatar.com/avatar/492d722c2d77a5816f0e600bd2c3a9f5?size=100", "Il va bien"));
        messages.add(new Message(2, "Eric Lemerdy", "http://www.gravatar.com/avatar/492d722c2d77a5816f0e600bd2c3a9f5?size=100", "Il a mangé"));
        messages.add(new Message(3, "Lapetite Lemerdy", "http://www.gravatar.com/avatar/492d722c2d77a5816f0e600bd2c3a9f5?size=100", "Il est fatigué"));
        messages.add(new Message(4, "Bogdan", "http://www.gravatar.com/avatar/492d722c2d77a5816f0e600bd2c3a9f5?size=100", "Il a mangé"));
        messages.add(new Message(1, "Bogdan", "http://www.gravatar.com/avatar/492d722c2d77a5816f0e600bd2c3a9f5?size=100", "Il va bien"));
        messages.add(new Message(1, "Bogdan", "http://www.gravatar.com/avatar/492d722c2d77a5816f0e600bd2c3a9f5?size=100", "Il dormait"));
        messages.add(new Message(2, "Bogdan", "http://www.gravatar.com/avatar/492d722c2d77a5816f0e600bd2c3a9f5?size=100", "Il va bien"));
        messages.add(new Message(2, "Marouane", "http://www.gravatar.com/avatar/492d722c2d77a5816f0e600bd2c3a9f5?size=100", "Il va bien"));

        TimeLineAdapter timeLineAdapter = new TimeLineAdapter(this, messages);
        mList.setAdapter(timeLineAdapter);

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

}
