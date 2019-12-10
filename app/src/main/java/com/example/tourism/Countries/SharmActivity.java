package com.example.tourism.Countries;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.tourism.Data.LandMarks.SharmLandMarksRecycle;
import com.example.tourism.Data.Restaurant.SharmRestaurantRecycle;
import com.example.tourism.Data.hotel.SharmhotelRecycle;
import com.example.tourism.R;

public class SharmActivity extends AppCompatActivity {

    /**
     * Created by : Ahmed Ramadan
     * date : 9 / 2019
     * ahmedtramadan4@gmail.com
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharm);


        //Action Card Hotel
        CardView hotel = findViewById(R.id.card_hotels_sharm);
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent hotle = new Intent(SharmActivity.this, SharmhotelRecycle.class);
                startActivity(hotle);
                Toast.makeText(SharmActivity.this, "Welcome ,Choose Your Hotels ..!", Toast.LENGTH_SHORT).show();

            }
        });

        //Action Card Restaurant
        CardView Rest = findViewById(R.id.card_restaurant_sharm);
        Rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Restaurant = new Intent(SharmActivity.this, SharmRestaurantRecycle.class);
                startActivity(Restaurant);
                Toast.makeText(SharmActivity.this, "Welcome ,Choose Your Restaurant..!", Toast.LENGTH_SHORT).show();

            }
        });

        //Action Card LandMarks
        CardView LandMarks = findViewById(R.id.card_streets_sharm);
        LandMarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent LandMarks = new Intent(SharmActivity.this, SharmLandMarksRecycle.class);
                startActivity(LandMarks);
                Toast.makeText(SharmActivity.this, "Welcome ,Choose Your LandMarks ..!", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
