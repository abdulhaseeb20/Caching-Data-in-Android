package com.example.fragmentspractice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private Button moveToBtn;
    private Button webViewBtn;
    private Button apiCallBtn;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize buttons
        moveToBtn = findViewById(R.id.move_to_btn);
        webViewBtn = findViewById(R.id.web_view_btn);
        apiCallBtn = findViewById(R.id.api_call_btn);
        // Set onClick listeners
        moveToBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Move to LoginFormActivity
                Intent intent = new Intent(MainActivity.this, LoginFormActivity.class);
                startActivity(intent);
            }
        });
        webViewBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Move to WebViewActivity
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });
        apiCallBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                // Move to CacheVisaAPI Activity
                Intent intent = new Intent(MainActivity.this, CacheVisaAPI.class);
                startActivity(intent);
            }
        });
    }
}
