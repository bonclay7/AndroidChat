package fr.grk.ecpstart.network;

import android.net.Uri;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import fr.grk.ecpstart.model.Tweet;
import fr.grk.ecpstart.model.User;

/**
 * Created by grk on 12/12/14.
 */
public class ApiClient {

    //private static final String API_BASE = "http://www.wapplix.com/ecp/";
    private static final String API_BASE = "http://54.148.197.52:8080/MicroBlogging/api/";


    public String login (String handle, String password) throws IOException{

        String url = Uri.parse(API_BASE + "session").buildUpon()
                .appendQueryParameter("handle", handle)
                .appendQueryParameter("password", password)
                .build().toString();
        InputStream stream = new URL(url).openStream();
        return IOUtils.toString(stream);
    }

    public List<User> getUsers() throws IOException{
        InputStream stream = new URL(API_BASE + "users").openStream();
        String response = IOUtils.toString(stream);
        return Arrays.asList(new Gson().fromJson(response, User[].class));
    }


    public List<Tweet> getUserTweets(String handle) throws IOException{
        InputStream stream = new URL(API_BASE + handle +"/tweets").openStream();
        String response = IOUtils.toString(stream);
        Tweet[] tweets = new Gson().fromJson(response, Tweet[].class);
        return Arrays.asList(tweets);
    }


    public void postTweet(String handle, String content) throws IOException {
        String url = Uri.parse(API_BASE + handle + "/tweets/post").buildUpon()
                .appendQueryParameter("content", content)
                .build().toString();
        new URL(url).openStream();
    }



}
