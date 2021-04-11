package com.example.logintest.Background;

import android.os.AsyncTask;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.logintest.UI.Register;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class RegisterBackground {

    private String username;
    private String password;
    private String result;
    private static final String ADD_URL = "https://studev.groept.be/api/a20sd505/addLogIn/";
    private static final String CHECK_URL = "https://studev.groept.be/api/a20sd505/checkUsernames";
    RequestQueue requestQueue;

    public RegisterBackground(RequestQueue rq, String... strings) {
        this.username = strings[0];
        this.password = strings[1];
        this.result = "Default";
        this.requestQueue = rq;

        checker();
    }

    private void checker() {
        System.out.println("Checking");

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, CHECK_URL, null,
            response -> {
                try {
                    boolean valid = true;

                    for (int i = 0; i < response.length(); i++) {
                         System.out.println("Username: " + i);
                         JSONObject o = response.getJSONObject(i);

                         String temp_name = o.get("Username").toString();
                         System.out.println(temp_name);

                         if (temp_name.equals(username)) {
                             result = "Username already exists!";
                             valid = false;
                             break;
                         }
                    }
                    if (valid) {
                        System.out.println("Username doesn't exist, attempting to add.");
                        register();
                    }
                 }
                catch (JSONException e) {
                    System.out.println("Error iterating through JSon: " + e.toString());
                 }
            },
            error -> System.out.println("Error array request: " + error.toString())
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void register() {
        System.out.println("Adding JSon");
        StringRequest submitRequest = new StringRequest(Request.Method.POST, ADD_URL,
                response -> System.out.println("Success: " + response),
                error -> System.out.println("Error adding JSon: " + error.toString())) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                System.out.println("Adding map");
                Map<String, String> params = new HashMap<>();

                params.put("username", username);
                params.put("password", password);
                result = "Username " + username + " created";

                return params;
            }

        };

        requestQueue.add(submitRequest);
    }

    public String getResult() {
        return result;
    }
}