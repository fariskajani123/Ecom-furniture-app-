package com.example.furnitureapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        findViewById(R.id.living_button).setOnClickListener(v -> startActivity(new Intent(this, LivingCategoryActivity.class)));
        findViewById(R.id.dining_button).setOnClickListener(v -> startActivity(new Intent(this, DiningCategoryActivity.class)));
        findViewById(R.id.outdoor_button).setOnClickListener(v -> startActivity(new Intent(this, OutdoorCategoryActivity.class)));
        findViewById(R.id.bathroom_button).setOnClickListener(v -> startActivity(new Intent(this, BathroomActivity.class)));
        findViewById(R.id.kitchen_button).setOnClickListener(v -> startActivity(new Intent(this, KitchenCategoryActivity.class)));
        findViewById(R.id.location_button).setOnClickListener(v -> startActivity(new Intent(this, LocationActivity.class)));
        findViewById(R.id.about_button).setOnClickListener(v -> startActivity(new Intent(this, About.class)));
    }
}
