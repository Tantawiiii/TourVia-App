package com.example.tourism.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.tourism.R;

public class Splash extends AppCompatActivity {

    /**
     * Created by : Ahmed Ramadan
     * date : 9 / 2019
     * ahmedtramadan4@gmail.com
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        YoYo.with(Techniques.FadeIn)
                .duration(2000)
                .repeat(5)
                .playOn(findViewById(R.id.textSplash));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent splash = new Intent(Splash.this, LoginActivity.class);
                startActivity(splash);
                finish();

            }
        }, 2000);
    }
}
