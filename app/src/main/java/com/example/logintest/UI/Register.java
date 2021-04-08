package com.example.logintest.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);
        button = findViewById(R.id.btnAdd);
    }

    public void onClick_btnRegister(View view) {
        RegisterBackground rb = new RegisterBackground(username.getText().toString(), email.getText().toString(), password.getText().toString());
        rb.response();
    }
}