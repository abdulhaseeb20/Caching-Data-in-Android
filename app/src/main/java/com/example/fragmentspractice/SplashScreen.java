package com.example.fragmentspractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity
{
    private static final String PREF_NAME = "MySharedPref";
    private static final String FIRST_TIME_KEY = "isFirstTime";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        // Check if it's the first time the user is opening the app
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean(FIRST_TIME_KEY, true);
        if (!isFirstTime)
        {
            // Skip the splash screen if coming from a fragment or if not the first time
            navigateToNextScreen();
        } else
        {
            // Show the splash screen with a delay if it's the first time
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    // Save the flag indicating the splash screen has been shown
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(FIRST_TIME_KEY, false);
                    editor.apply();

                    navigateToNextScreen();
                }
            }, 3000); // 3 seconds delay
        }
    }

    private void navigateToNextScreen()
    {
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
