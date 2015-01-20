package fr.grk.ecpstart.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import fr.grk.ecpstart.R;
import fr.grk.ecpstart.model.AccountManager;
import fr.grk.ecpstart.network.ApiClient;

/**
 * Created by grk on 12/12/14.
 */
public class PostActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweets_activity);
    }

    public void post(){
        EditText contentText = (EditText) findViewById(R.id.post_content);
        String content = contentText.getText().toString();

        if (content.isEmpty()){
            contentText.setError(getString(R.string.required));
            contentText.requestFocus();
            return;
        }

        new AsyncTask<String, Void, Boolean>(){

            @Override
            protected Boolean doInBackground(String... params) {
                try {
                    String content = params[0];
                    String handle = AccountManager.getUserHandle(PostActivity.this);
                    new ApiClient().postTweet(handle, content);
                    return true;
                }catch (IOException e){
                    Log.e(PostActivity.class.getName(), "Post failed", e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {
                if (success){
                    finish();
                    Toast.makeText(PostActivity.this, R.string.post_success, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PostActivity.this, R.string.post_error, Toast.LENGTH_SHORT).show();
                }

            }
        }.execute();
    }
}
