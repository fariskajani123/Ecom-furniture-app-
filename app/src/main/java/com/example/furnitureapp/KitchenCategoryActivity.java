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

public class KitchenCategoryActivity extends AppCompatActivity {
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
        products.add(new Product("Kitchen Island", R.drawable.kitchen_island, 699.99));
        products.add(new Product("Bar Stools", R.drawable.bar_stools_kitchen, 129.99));
        products.add(new Product("Cutlery Set", R.drawable.cutlery_set, 49.99));
        products.add(new Product("Cookware Set", R.drawable.cookware_set, 179.99));
        products.add(new Product("Spice Rack", R.drawable.spice_rack, 39.99));
        products.add(new Product("Dish Rack", R.drawable.dish_rack, 24.99));
        products.add(new Product("Kitchen Towels", R.drawable.kitchen_towels, 19.99));
        products.add(new Product("Blender", R.drawable.blender, 79.99));
        products.add(new Product("Coffee Maker", R.drawable.coffee_maker, 99.99));
        products.add(new Product("Microwave", R.drawable.microwave, 139.99));

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
                    StorageUtil.saveFavorites(KitchenCategoryActivity.this, favoriteProducts);
                    Toast.makeText(KitchenCategoryActivity.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(KitchenCategoryActivity.this, "Already in Favorites", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(KitchenCategoryActivity.this, "No product selected", Toast.LENGTH_SHORT).show();
            }
        });

        addToCartButton.setOnClickListener(v -> {
            if (selectedProduct != null) {
                if (!cartProducts.contains(selectedProduct)) {
                    cartProducts.add(selectedProduct);
                    StorageUtil.saveCart(KitchenCategoryActivity.this, cartProducts);
                    Toast.makeText(KitchenCategoryActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(KitchenCategoryActivity.this, "Already in Cart", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(KitchenCategoryActivity.this, "No product selected", Toast.LENGTH_SHORT).show();
            }
        });

        viewCartButton.setOnClickListener(v -> {
            Intent intent = new Intent(KitchenCategoryActivity.this, CartActivity.class);
            startActivity(intent);
        });

        viewFavoritesButton.setOnClickListener(v -> {
            Intent intent = new Intent(KitchenCategoryActivity.this, FavouritesActivity.class);
            startActivity(intent);
        });
    }
}
