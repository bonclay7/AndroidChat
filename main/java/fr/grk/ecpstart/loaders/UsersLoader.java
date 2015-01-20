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

import fr.grk.ecpstart.model.User;
import fr.grk.ecpstart.network.ApiClient;

/**
 * Created by grk on 05/12/14.
 */

public class UsersLoader extends AsyncTaskLoader<List<User>> {


    private List<User> mResult;

    public UsersLoader(Context context){
        super(context);
    }


    @Override
    public List<User> loadInBackground() {
        try {
            return new ApiClient().getUsers();
        }catch(IOException e){
            Log.e(UsersLoader.class.getName(), "Failed to download users", e);
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
    public void deliverResult(List<User> data) {
        mResult = data;
        super.deliverResult(data);
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }
}
