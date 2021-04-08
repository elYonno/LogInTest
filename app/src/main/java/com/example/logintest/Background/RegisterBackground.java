package com.example.logintest.Background;

import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicBoolean;

public class RegisterBackground extends AsyncTask<String, Void, String> {

    private String username;
    private String password;
    private String result;
    private static final String ADD_URL = "https://studev.groept.be/api/a20sd505/addLogIn/"; // username/email/password
    private static final String CHECK_URL = "https://studev.groept.be/api/a20sd505/checkLogIn/";

    @Override
    protected String doInBackground(String... strings) {
        System.out.println("Register background made!");
        this.username = strings[0];
        this.password = strings[1];

        if (checker()) register();

        return null;
    }

    private boolean checker() {
        System.out.println("Checking");
        AtomicBoolean check = new AtomicBoolean(false);

        new JsonArrayRequest(Request.Method.GET, CHECK_URL, null,
                response -> {
                    boolean test = true;
                    for (int i = 0; i < response.length(); i++) {
                         try {
                             JSONObject o = response.getJSONObject(i);

                             String temp_name = o.get("Username").toString();

                             if (temp_name.equals(username)) {
                                 test = false;
                                 result = "Username already exists!";
                                 break;
                             }
                         } catch (JSONException e) {
                             System.out.println("-------------------------------------ERROR------------------------------------------");
                             result = e.toString();
                             e.printStackTrace();
                         }
                     }
                    check.set(test);
                }, error -> {
                    result = "Cannot return the database!";
                });

        return check.get();
    }

    private void register() {
        JSONObject data = new JSONObject();
        try {
            data.put("username", username);
            data.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new JsonObjectRequest(Request.Method.PUT, ADD_URL, data,
                response -> {
                    result = "Username " + username + " successfully been created!";
                }, error -> {
                    result = "There was an error!";
        });

    }
}