package com.example.tourism.Data.ClickItem;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tourism.R;


public class DetailsActivity extends AppCompatActivity {
    /**
     * Created by : Ahmed Ramadan
     * date : 9 / 2019
     * ahmedtramadan4@gmail.com
     */

    TextView tv_nameItem, tv_descriptionItem, tv_location_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // initialize Views
        tv_nameItem = findViewById(R.id.tv_name_detail);
        tv_descriptionItem = findViewById(R.id.tv_des_detail);
        tv_location_item = findViewById(R.id.tv_Location_detail);

        //Get Data from intent
        String name = getIntent().getStringExtra("name");
        String desc = getIntent().getStringExtra("des");
        String loc = getIntent().getStringExtra("location");

        // Set Data To Views ....
        tv_nameItem.setText(name);
        tv_descriptionItem.setText(desc);
        tv_location_item.setText(loc);


    }
}
