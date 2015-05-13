package arifin.chapterhouse.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import arifin.chapterhouse.Services.WebServiceUtil;

/**
 * Created by stephen on 5/10/15.
 */
public class AsyncTaskPopulateMembers extends AsyncTask<String, Void, JSONObject> {


    HttpURLConnection urlConnection;

    @Override
    protected JSONObject doInBackground(String... urls){
/*
        JSONObject JSONmembers = WebServiceUtil.requestWebService("http://chapter-house-test.herokuapp.com/users");
        Log.d("JSON MEMBERS", JSONmembers.toString());
        return JSONmembers; */

        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL("http://chapter-house-test.herokuapp.com/users");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            Log.d("PLEASE WORK", result.toString());

        }catch( Exception e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }


        return null;

    }

}
