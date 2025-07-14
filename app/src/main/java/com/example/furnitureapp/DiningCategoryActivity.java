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

public class DiningCategoryActivity extends AppCompatActivity {
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
        products.add(new Product("Dining Table", R.drawable.dining_table, 499.99));
        products.add(new Product("Dining Chairs", R.drawable.dining_chairs, 199.99));
        products.add(new Product("Sideboard", R.drawable.sideboard, 299.99));
        products.add(new Product("Bar Stools", R.drawable.bar_stools, 149.99));
        products.add(new Product("Wine Rack", R.drawable.wine_rack, 89.99));
        products.add(new Product("Chandelier", R.drawable.chandelier, 129.99));
        products.add(new Product("Table Runner", R.drawable.table_runner, 24.99));
        products.add(new Product("Napkins", R.drawable.napkins, 14.99));
        products.add(new Product("Placemats", R.drawable.placemats, 19.99));
        products.add(new Product("Centerpiece", R.drawable.centerpiece, 39.99));

        ProductAdapter adapter = new ProductAdapter(products, product -> selectedProduct = product);
        recyclerView.setAdapter(adapter);

        Button favoriteButton = findViewById(R.id.favorite_button);
        Button addToCartButton = findViewById(R.id.add_to_cart_button);
        Button viewCartButton = findViewById(R.id.view_cart_button);

        favoriteButton.setOnClickListener(v -> {
            if (selectedProduct != null) {
                if (!favoriteProducts.contains(selectedProduct)) {
                    favoriteProducts.add(selectedProduct);
                    StorageUtil.saveFavorites(DiningCategoryActivity.this, favoriteProducts);
                    Toast.makeText(DiningCategoryActivity.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DiningCategoryActivity.this, "Already in Favorites", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(DiningCategoryActivity.this, "No product selected", Toast.LENGTH_SHORT).show();
            }
        });

        addToCartButton.setOnClickListener(v -> {
            if (selectedProduct != null) {
                if (!cartProducts.contains(selectedProduct)) {
                    cartProducts.add(selectedProduct);
                    StorageUtil.saveCart(DiningCategoryActivity.this, cartProducts);
                    Toast.makeText(DiningCategoryActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DiningCategoryActivity.this, "Already in Cart", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(DiningCategoryActivity.this, "No product selected", Toast.LENGTH_SHORT).show();
            }
        });

        viewCartButton.setOnClickListener(v -> {
            Intent intent = new Intent(DiningCategoryActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }
}
