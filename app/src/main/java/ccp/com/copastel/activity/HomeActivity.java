package ccp.com.copastel.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ccp.com.copastel.R;

/**
 * Created by lucasmorano on 2/22/15.
 */
public class HomeActivity extends Activity {

    private TextView mWelcome;
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
    }
}
