package com.example.tourism;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tourism.Countries.DahabActivity;
import com.example.tourism.Countries.IsmailliaActivity;
import com.example.tourism.Countries.SharmActivity;
import com.example.tourism.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    /**
     * Created by : Ahmed Ramadan
     * date : 9 / 2019
     * ahmedtramadan4@gmail.com
     */


    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        // Sharm El-Shi Activity BuTTON
        Button btn_sharm = findViewById(R.id.btn_sharmElShaikh);
        btn_sharm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sharm = new Intent(HomeActivity.this, SharmActivity.class);
                startActivity(sharm);

            }
        });

        // Ismailia Activity Button
        Button btn_Ismailia = findViewById(R.id.btn_ismalia);
        btn_Ismailia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ismalia = new Intent(HomeActivity.this, IsmailliaActivity.class);
                startActivity(ismalia);

            }
        });


        // Dahab Activity Button
        Button btn_Dahab = findViewById(R.id.btn_dahab);
        btn_Dahab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Dahab = new Intent(HomeActivity.this, DahabActivity.class);
                startActivity(Dahab);

            }
        });


        Button out = findViewById(R.id.btn_gologin);
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.signOut();

                Intent log = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(log);
                finish();

            }
        });

    }
}
