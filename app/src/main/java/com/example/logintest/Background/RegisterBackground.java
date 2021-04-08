package com.example.logintest.Background;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicBoolean;

public class RegisterBackground {

    private String username;
    private String email;
    private String password;
    private String result;
    private static final String ADD_URL = "https://studev.groept.be/api/a20sd508/add/"; // username/email/password
    private static final String CHECK_URL = "https://studev.groept.be/api/a20sd508/check/";

    public RegisterBackground(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;

        if (checker()) register();
        else result = "Username already exists!";
    }

    private boolean checker() {
        AtomicBoolean check = new AtomicBoolean(false);

        new JsonArrayRequest(Request.Method.GET, CHECK_URL, null,
                response -> {
                    boolean test = true;
                    for (int i = 0; i < response.length(); i++) {
                         try {
                             JSONObject o = response.getJSONObject(i);

                             String temp_mail = o.get("email").toString();
                             String temp_name = o.get("name").toString();

                             if (temp_mail.equals(email)) {
                                 test = false;
                                 result = "Email already taken!";
                                 break;
                             }

                             if (temp_name.equals(username)) {
                                 test = false;
                                 result = "Username already exists!";
                                 break;
                             }
                         } catch (JSONException e) { e.printStackTrace(); }
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
            data.put("nm", username);
            data.put("em", email);
            data.put("pas", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new JsonObjectRequest(Request.Method.PUT, ADD_URL, data,
                response -> {
                    result = "Username " + username + " successfully been created?";
                }, error -> {
            result = "There was an error!";
        }
        );

    }


    public String response() {
        return result;
    }
}