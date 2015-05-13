package arifin.chapterhouse.Services;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by stephen on 5/10/15.
 */
public class WebServiceUtil {


    public static JSONObject requestWebService(String serviceUrl) {

        /*
        disableConnectionReuseIfNecessary();

        HttpURLConnection urlConnection = null;
        try {
            // create connection
            URL urlToRequest = new URL(serviceUrl);
            urlConnection = (HttpURLConnection)
                    urlToRequest.openConnection();
            urlConnection.setConnectTimeout(5000);      // Arbitrary 5 second timeout
            urlConnection.setReadTimeout(5000);         // Same thing

            // handle issues
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // handle unauthorized (if service requires user login)
            } else if (statusCode != HttpURLConnection.HTTP_OK) {
                // handle any other errors, like 404, 500,..
            }

            // create JSON object from content
            InputStream in = new BufferedInputStream(
                    urlConnection.getInputStream());
            return new JSONObject(getResponseText(in));

        } catch (MalformedURLException e) {
            // URL is invalid
        } catch (SocketTimeoutException e) {
            // data retrieval or connection timed out
        } catch (IOException e) {
            // could not read response body
            // (could not create input stream)
        } catch (JSONException e) {
            // response body is no valid JSON string
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null; */

        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;

        BufferedReader rd  = null;
        StringBuilder sb = null;
        String line = null;

        try {
                /* forming th java.net.URL object */
            URL url = new URL(serviceUrl);
            urlConnection = (HttpURLConnection) url.openConnection();

                 /* optional request header */
            urlConnection.setRequestProperty("Content-Type", "application/json");

                /* optional request header */
            urlConnection.setRequestProperty("Accept", "application/json");

                /* for Get request */
            urlConnection.setRequestMethod("GET");
            int statusCode = urlConnection.getResponseCode();

            urlConnection.connect();

            rd  = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            sb = new StringBuilder();

            while ((line = rd.readLine()) != null)
            {
                sb.append(line + '\n');
            }

            Log.d("Chapter HOUSE", sb.toString());

                /* 200 represents HTTP OK *//*
            if (statusCode ==  200) {
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                return new JSONObject(getResponseText(inputStream));

            }else{
               return null; //"Failed to fetch data!";
            } */
        } catch (Exception e) {
            Log.d("Chapter House", e.getLocalizedMessage());
        }
        return null; //"Failed to fetch data!";
    }

    // Hard coded
    public static void postWebService(String serviceUrl){

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(serviceUrl);

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("user[email]", "stephen.arifin@gmail.com"));
        nameValuePair.add(new BasicNameValuePair("user[password]", "testpassword"));

        try{
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        }
        catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }

        try{
            HttpResponse response = httpClient.execute(httpPost);
            String responseText = EntityUtils.toString(response.getEntity());
            Log.d("Http Post Chapter House", responseText);
        }
        catch(ClientProtocolException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * required in order to prevent issues in earlier Android version.
     */
    private static void disableConnectionReuseIfNecessary() {
        // see HttpURLConnection API doc
        if (Integer.parseInt(Build.VERSION.SDK)
                < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private static String getResponseText(InputStream inStream) {
        // very nice trick from
        // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
        return new Scanner(inStream).useDelimiter("\\A").next();
    }

}
