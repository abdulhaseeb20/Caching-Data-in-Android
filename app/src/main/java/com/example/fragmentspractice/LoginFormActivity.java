package com.example.fragmentspractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFormActivity extends AppCompatActivity
{
    EditText usernameEditText, passwordEditText, mobilenoEditText;
    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        mobilenoEditText = findViewById(R.id.mobile_no);
        submitBtn = findViewById(R.id.submit_button);

        submitBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("CHECK", "button clicked!");
                Intent intent = new Intent(LoginFormActivity.this, CacheVisaAPI.class);
                //intent.putExtra("fromFragment", true);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //fetch data stored in Shared Pref
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);
        String password = sharedPreferences.getString("password", null);
        String mobileno = sharedPreferences.getString("mobileno", null);

        usernameEditText.setText(username);
        passwordEditText.setText(password);
        mobilenoEditText.setText(mobileno);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        //store the data in the Shared Pref Obj
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //write data to shared pref
        editor.putString("username", usernameEditText.getText().toString());
        editor.putString("password", passwordEditText.getText().toString());
        editor.putString("mobileno", mobilenoEditText.getText().toString());
        editor.apply();
        Log.d("CHECK", "Data Applied");

        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
    }
}