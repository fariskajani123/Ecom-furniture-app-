package com.example.furnitureapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {
    private List<Product> favoriteProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        favoriteProducts = StorageUtil.loadFavorites(this);
        if (favoriteProducts == null) {
            favoriteProducts = new ArrayList<>();
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ProductAdapter adapter = new ProductAdapter(favoriteProducts, product -> {});
        recyclerView.setAdapter(adapter);

        TextView totalTextView = findViewById(R.id.total_text_view);
        double total = 0;
        for (Product product : favoriteProducts) {
            total += product.getPrice();
        }
        totalTextView.setText(String.format("Total: $%.2f", total));
    }
}
