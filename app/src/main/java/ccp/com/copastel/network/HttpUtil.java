package ccp.com.copastel.network;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import ccp.com.copastel.domain.Request;
import ccp.com.copastel.domain.User;
import ccp.com.copastel.exception.UnauthorizedException;

/**
 * Created by lucasmorano on 2/22/15.
 */
public class HttpUtil {

    private static final String HOST = "http://192.168.1.11:8080";

    private static Gson gson;

    private static HttpClient httpClient;

    public static <T> T doPost(Request<T> request) throws IOException, JSONException {
        HttpPost httpPost = new HttpPost(HOST + request.getUrl());
        httpPost.setEntity(request.getLoginHttpEntity());
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        HttpResponse response = getHttpClient().execute(httpPost);
        return getJsonMapper().fromJson(EntityUtils.toString(response.getEntity()), request.getResponseClass());
    }

    public static void doLogin(Request request) throws IOException, JSONException, UnauthorizedException {
        HttpPost httpPost = new HttpPost(HOST + request.getUrl());
        httpPost.setEntity(request.getLoginHttpEntity());
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        HttpResponse response = getHttpClient().execute(httpPost);
        if(response.getStatusLine().getStatusCode() != 200){
            throw new UnauthorizedException();
        }
    }

    private static HttpClient getHttpClient() {
        if(httpClient == null){
            httpClient = new DefaultHttpClient();
        }
        return httpClient;
    }

    public static Gson getJsonMapper(){
        if(gson == null){
            gson = new Gson();
        }
        return gson;
    }

    public static UrlEncodedFormEntity createLoginPayload(User user) throws UnsupportedEncodingException {
        List<NameValuePair> values = new ArrayList<>();
        values.add(new BasicNameValuePair("username",user.getLogin()));
        values.add(new BasicNameValuePair("password",user.getPassword()));
        return new UrlEncodedFormEntity(values);
    }
}
