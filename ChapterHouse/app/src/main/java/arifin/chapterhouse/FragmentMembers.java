package arifin.chapterhouse;



import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import arifin.chapterhouse.AsyncTasks.AsyncTaskLogin;
import arifin.chapterhouse.AsyncTasks.AsyncTaskPopulateMembers;
import arifin.chapterhouse.Services.WebServiceUtil;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class FragmentMembers extends Fragment {


    public FragmentMembers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        try {
            JSONObject JSONmembers = new AsyncTaskPopulateMembers().execute().get();
            Log.d("JSON MEMBERS", JSONmembers.toString());
        }
        catch(InterruptedException e){
        }
        catch(ExecutionException e){
        }


//        new AsyncTaskLogin().execute();


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_members, container, false);
    }

}
