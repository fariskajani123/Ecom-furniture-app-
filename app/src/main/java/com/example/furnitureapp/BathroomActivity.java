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

public class BathroomActivity extends AppCompatActivity {
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
        products.add(new Product("Bathroom Cabinet", R.drawable.bathroom_cabinet, 150.00));
        products.add(new Product("Shower Curtain", R.drawable.shower_curtain, 20.00));
        products.add(new Product("Towel Rack", R.drawable.towel_rack, 35.00));
        products.add(new Product("Bath Mat", R.drawable.bath_mat, 25.00));
        products.add(new Product("Mirror", R.drawable.mirror, 50.00));
        products.add(new Product("Soap Dispenser", R.drawable.soap_dispenser, 10.00));
        products.add(new Product("Toilet Brush", R.drawable.toilet_brush, 15.00));
        products.add(new Product("Laundry Basket", R.drawable.laundry_basket, 40.00));
        products.add(new Product("Storage Shelves", R.drawable.storage_shelves, 60.00));
        products.add(new Product("Bathroom Rug", R.drawable.bathroom_rug, 30.00));

        ProductAdapter adapter = new ProductAdapter(products, product -> selectedProduct = product);
        recyclerView.setAdapter(adapter);

        Button favoriteButton = findViewById(R.id.favorite_button);
        Button addToCartButton = findViewById(R.id.add_to_cart_button);
        Button viewCartButton = findViewById(R.id.view_cart_button);

        favoriteButton.setOnClickListener(v -> {
            if (selectedProduct != null) {
                if (!favoriteProducts.contains(selectedProduct)) {
                    favoriteProducts.add(selectedProduct);
                    StorageUtil.saveFavorites(BathroomActivity.this, favoriteProducts);
                    Toast.makeText(BathroomActivity.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BathroomActivity.this, "Already in Favorites", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(BathroomActivity.this, "No product selected", Toast.LENGTH_SHORT).show();
            }
        });


        addToCartButton.setOnClickListener(v -> {
            if (selectedProduct != null) {
                if (!cartProducts.contains(selectedProduct)) {
                    cartProducts.add(selectedProduct);
                    StorageUtil.saveCart(BathroomActivity.this, cartProducts);
                    Toast.makeText(BathroomActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BathroomActivity.this, "Already in Cart", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(BathroomActivity.this, "No product selected", Toast.LENGTH_SHORT).show();
            }
        });

        viewCartButton.setOnClickListener(v -> {
            Intent intent = new Intent(BathroomActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }
}
