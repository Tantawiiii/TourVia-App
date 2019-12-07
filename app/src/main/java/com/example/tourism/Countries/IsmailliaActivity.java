package com.example.tourism.Countries;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.tourism.Data.LandMarks.IsmiliaLandMarkRecycle;
import com.example.tourism.Data.Restaurant.IsmiliaRestaurantRecycle;
import com.example.tourism.Data.hotel.IsmiliaHotelRecycle;
import com.example.tourism.R;

public class IsmailliaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ismaillia);


        //Action Card Hotel
        CardView hotel = findViewById(R.id.card_hotels_ismalia);
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent hotle = new Intent(IsmailliaActivity.this, IsmiliaHotelRecycle.class);
                startActivity(hotle);
                Toast.makeText(IsmailliaActivity.this, "Welcome ,Choose Your Hotels ..!", Toast.LENGTH_SHORT).show();
            }
        });


        //Action Card Restaurant
        CardView Restaurant = findViewById(R.id.card_restaurant_ismalia);
        Restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Restaurant = new Intent(IsmailliaActivity.this, IsmiliaRestaurantRecycle.class);
                startActivity(Restaurant);
                Toast.makeText(IsmailliaActivity.this, "Welcome ,Choose Your Restaurant ..!", Toast.LENGTH_SHORT).show();


            }
        });

        //Action Card LandMarks
        CardView LandMarks = findViewById(R.id.card_streets_ismailia);
        LandMarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent LandMarks = new Intent(IsmailliaActivity.this, IsmiliaLandMarkRecycle.class);
                startActivity(LandMarks);
                Toast.makeText(IsmailliaActivity.this, "Welcome ,Choose Your LandMarks ..!", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
