package ccp.com.copastel.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ccp.com.copastel.R;
import ccp.com.copastel.domain.Element;
import ccp.com.copastel.domain.Request;
import ccp.com.copastel.network.HttpUtil;

/**
 * Created by lucasmorano on 2/22/15.
 */
public class HomeActivity extends Activity {

    private TextView mWelcome;
    private ListView mListView;
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Bundle extras = getIntent().getExtras();
        String userLogin = extras.getString("LOGIN");
        mWelcome = (TextView) findViewById(R.id.welcome);
        mWelcome.setVisibility(View.VISIBLE);
        mWelcome.setText("Welcome in " + userLogin + "!");

        mListView = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<Element> feed = getFeed();
        feed.size();
    }


    private List<Element> getFeed() {
        AsyncTask<Void, Void, List<Element>> asyncTask = new AsyncTask<Void, Void, List<Element>>() {
            @Override
            protected List<Element> doInBackground(Void... params) {
                try {
                    Request<Element[]> request = new Request<>();
                    request.setUrl("/api/GetFeed");
                    request.setResponseClass(Element[].class);
                    return Arrays.asList(HttpUtil.doPost(request));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        try {
            List<Element> elements = asyncTask.execute().get();
            return elements;
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        }
        return null;
    }
}
