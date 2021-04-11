package com.example.logintest.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.logintest.R;

public class MainActivity extends AppCompatActivity {

    Button register;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = findViewById(R.id.btnRegister);
    }

    public void onClick_btnRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}