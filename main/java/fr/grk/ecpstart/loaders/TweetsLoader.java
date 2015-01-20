package fr.grk.ecpstart.loaders;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import fr.grk.ecpstart.model.Tweet;
import fr.grk.ecpstart.network.ApiClient;

/**
 * Created by grk on 05/12/14.
 */

public class TweetsLoader extends AsyncTaskLoader<List<Tweet>> {


    private List<Tweet> mResult;
    private String mUserHandle;


    public TweetsLoader(Context context, String userHandle) {
        super(context);
        this.mUserHandle = userHandle;
    }

    @Override
    public List<Tweet> loadInBackground() {
        try {
            return new ApiClient().getUserTweets(mUserHandle);
        }catch(IOException e){
            Log.e(TweetsLoader.class.getName(), "Failed to download tweets", e);
            return null;
        }
    }


    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mResult != null){
            deliverResult(mResult);
        }
        if (takeContentChanged() || mResult == null){
            forceLoad();
        }
    }


    @Override
    public void deliverResult(List<Tweet> data) {
        Log.i(TweetsLoader.class.getName(), "Returned data: " + data);
        mResult = data;
        super.deliverResult(data);
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        cancelLoad();
    }
}
