package arifin.chapterhouse.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import arifin.chapterhouse.Services.WebServiceUtil;

/**
 * Created by stephen on 5/11/15.
 */
public class AsyncTaskLogin extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls){
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(urls[0]);

        // Setup cookies
        CookieStore cookieStore = new BasicCookieStore();
        HttpContext localContext = new BasicHttpContext();
        localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);


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
            HttpResponse response = httpClient.execute(httpPost, localContext);
            String responseText = EntityUtils.toString(response.getEntity());
            Log.d("Http Post Chapter House", responseText);

            List<Cookie> cookies = cookieStore.getCookies();
            Log.d("COOKIE SIZE", Integer.toString(cookies.size()));

            Log.d("FIRST ELEMENT", cookies.get(0).getValue());
            return cookies.get(0).getValue();   // return the x-xsrf token

            /*
            for(Cookie cookie : cookies){
                Log.d("Chapter House COOKIES", cookie.toString());
            } */

        }
        catch(ClientProtocolException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
