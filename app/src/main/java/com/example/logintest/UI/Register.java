package com.example.logintest.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.logintest.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class Register extends AppCompatActivity {

    TextView username;
    TextView password;
    Button button;
    RequestQueue requestQueue;

    private static final String ADD_URL = "https://studev.groept.be/api/a20sd505/addLogIn/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPassword);
        button = findViewById(R.id.btnAdd);
        requestQueue = Volley.newRequestQueue(this);
    }

    public void onClick_btnAdd(View view) {
        System.out.println("Register button clicked");
        String text_user = username.getText().toString();
        String text_password = password.getText().toString();

        System.out.println("Adding JSon");
        StringRequest submitRequest = new StringRequest(Request.Method.POST, ADD_URL,
                response -> System.out.println("Success: " + response),
                error -> System.out.println("Error: " + error.toString())) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                System.out.println("Adding map");
                Map<String, String> params = new HashMap<>();

                params.put("username", text_user);
                params.put("password", text_password);

                return params;
            }

        };

        requestQueue.add(submitRequest);
    }
}