package com.example.logintest.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.Volley;
import com.example.logintest.Background.RegisterBackground;
import com.example.logintest.R;

public class Register extends AppCompatActivity {

    TextView username;
    TextView password;
    Button button;
    RequestQueue requestQueue;

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

<<<<<<< HEAD
        RegisterBackground registerBackground = new RegisterBackground(requestQueue, text_user, text_password);
        try {
            wait(1000);
        }
        catch (Exception e) {}
        toast(registerBackground.getResult());

    }

    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
=======
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
>>>>>>> e404af9e23896f1a043e949ec0a2eb869227a07f
    }
}