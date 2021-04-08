package com.example.logintest.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logintest.Background.RegisterBackground;
import com.example.logintest.R;

public class Register extends AppCompatActivity {

    TextView username;
    TextView email;
    TextView password;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPassword);
        button = findViewById(R.id.btnAdd);
    }

    public void onClick_btnRegister(View view) {
        String text_user = username.getText().toString();
        String text_password = password.getText().toString();

        AsyncTask<String, Void, String> rb = new RegisterBackground().execute(text_user, text_password);

        try {
            Toast.makeText(this, rb.get(), Toast.LENGTH_LONG);
        } catch (Exception e) { e.printStackTrace(); }
    }
}