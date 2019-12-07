package com.example.tourism.Countries;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.tourism.Data.LandMarks.DahabLandRecycle;
import com.example.tourism.Data.Restaurant.DahabRestaurantRecycle;
import com.example.tourism.Data.hotel.DahabHotelRecycle;
import com.example.tourism.R;

public class DahabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dahab);

        //Action Card Hotel
        CardView hotel = findViewById(R.id.card_hotels_dahab);
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent LandMarks = new Intent(DahabActivity.this, DahabHotelRecycle.class);
                startActivity(LandMarks);
                Toast.makeText(DahabActivity.this, "Welcome ,Choose Your Hotel ..!", Toast.LENGTH_SHORT).show();
            }
        });


        //Action Card Restaurant
        CardView Restaurant = findViewById(R.id.card_restaurant_dahab);
        Restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Restaurant = new Intent(DahabActivity.this, DahabRestaurantRecycle.class);
                startActivity(Restaurant);
                Toast.makeText(DahabActivity.this, "Welcome ,Choose Your Restaurant ..!", Toast.LENGTH_SHORT).show();
            }
        });

        //Action Card LandMarks
        CardView LandMarks = findViewById(R.id.card_streets_dahab);
        LandMarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent LandMarks = new Intent(DahabActivity.this, DahabLandRecycle.class);
                startActivity(LandMarks);
                Toast.makeText(DahabActivity.this, "Welcome ,Choose Your LandMarks ..!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
