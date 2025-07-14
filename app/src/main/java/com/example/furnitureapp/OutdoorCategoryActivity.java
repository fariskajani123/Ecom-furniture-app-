package com.example.furnitureapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class OutdoorCategoryActivity extends AppCompatActivity {
    private List<Product> favoriteProducts;
    private List<Product> cartProducts;
    private Product selectedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        favoriteProducts = StorageUtil.loadFavorites(this);
        if (favoriteProducts == null) {
            favoriteProducts = new ArrayList<>();
        }

        cartProducts = StorageUtil.loadCart(this);
        if (cartProducts == null) {
            cartProducts = new ArrayList<>();
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Product> products = new ArrayList<>();
        products.add(new Product("Outdoor Sofa", R.drawable.outdoor_sofa, 299.99));
        products.add(new Product("Patio Table", R.drawable.patio_table, 199.99));
        products.add(new Product("Patio Chairs", R.drawable.patio_chairs, 149.99));
        products.add(new Product("Sun Lounger", R.drawable.sun_lounger, 199.99));
        products.add(new Product("BBQ Grill", R.drawable.bbq_grill, 399.99));
        products.add(new Product("Outdoor Rug", R.drawable.outdoor_rug, 79.99));
        products.add(new Product("Garden Bench", R.drawable.garden_bench, 129.99));
        products.add(new Product("Hammock", R.drawable.hammock, 49.99));
        products.add(new Product("Fire Pit", R.drawable.fire_pit, 229.99));
        products.add(new Product("Outdoor Lantern", R.drawable.outdoor_lantern, 29.99));

        ProductAdapter adapter = new ProductAdapter(products, product -> selectedProduct = product);
        recyclerView.setAdapter(adapter);

        Button favoriteButton = findViewById(R.id.favorite_button);
        Button addToCartButton = findViewById(R.id.add_to_cart_button);
        Button viewCartButton = findViewById(R.id.view_cart_button);
        Button viewFavoritesButton = findViewById(R.id.view_favorites_button);

        favoriteButton.setOnClickListener(v -> {
            if (selectedProduct != null) {
                if (!favoriteProducts.contains(selectedProduct)) {
                    favoriteProducts.add(selectedProduct);
                    StorageUtil.saveFavorites(OutdoorCategoryActivity.this, favoriteProducts);
                    Toast.makeText(OutdoorCategoryActivity.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OutdoorCategoryActivity.this, "Already in Favorites", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(OutdoorCategoryActivity.this, "No product selected", Toast.LENGTH_SHORT).show();
            }
        });

        addToCartButton.setOnClickListener(v -> {
            if (selectedProduct != null) {
                if (!cartProducts.contains(selectedProduct)) {
                    cartProducts.add(selectedProduct);
                    StorageUtil.saveCart(OutdoorCategoryActivity.this, cartProducts);
                    Toast.makeText(OutdoorCategoryActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OutdoorCategoryActivity.this, "Already in Cart", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(OutdoorCategoryActivity.this, "No product selected", Toast.LENGTH_SHORT).show();
            }
        });

        viewCartButton.setOnClickListener(v -> {
            Intent intent = new Intent(OutdoorCategoryActivity.this, CartActivity.class);
            startActivity(intent);
        });

        viewFavoritesButton.setOnClickListener(v -> {
            Intent intent = new Intent(OutdoorCategoryActivity.this, FavouritesActivity.class);
            startActivity(intent);
        });
    }
}
