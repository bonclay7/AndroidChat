package fr.grk.ecpstart.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import fr.grk.ecpstart.R;
import fr.grk.ecpstart.fragments.TweetsFragment;

/**
 * Created by grk on 05/12/14.
 */
public class TweetsActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweets_activity);

        if (savedInstanceState == null) {
            TweetsFragment tweetsFragment = new TweetsFragment();
            tweetsFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, tweetsFragment)
                    .commit();
        }

    }
}
