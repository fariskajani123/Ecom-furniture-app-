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

public class LivingCategoryActivity extends AppCompatActivity {
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
        products.add(new Product("Sofa", R.drawable.sofa, 499.99));
        products.add(new Product("Armchair", R.drawable.armchair, 199.99));
        products.add(new Product("Coffee Table", R.drawable.coffee_table, 99.99));
        products.add(new Product("Bookshelf", R.drawable.bookshelf, 149.99));
        products.add(new Product("TV Stand", R.drawable.tv_stand, 199.99));
        products.add(new Product("Rug", R.drawable.rug, 79.99));
        products.add(new Product("Lamp", R.drawable.lamp, 39.99));
        products.add(new Product("Curtains", R.drawable.curtains, 29.99));
        products.add(new Product("Wall Art", R.drawable.wall_art, 49.99));
        products.add(new Product("Cushions", R.drawable.cushions, 19.99));

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
                    StorageUtil.saveFavorites(LivingCategoryActivity.this, favoriteProducts);
                    Toast.makeText(LivingCategoryActivity.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LivingCategoryActivity.this, "Already in Favorites", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LivingCategoryActivity.this, "No product selected", Toast.LENGTH_SHORT).show();
            }
        });

        addToCartButton.setOnClickListener(v -> {
            if (selectedProduct != null) {
                if (!cartProducts.contains(selectedProduct)) {
                    cartProducts.add(selectedProduct);
                    StorageUtil.saveCart(LivingCategoryActivity.this, cartProducts);
                    Toast.makeText(LivingCategoryActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LivingCategoryActivity.this, "Already in Cart", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LivingCategoryActivity.this, "No product selected", Toast.LENGTH_SHORT).show();
            }
        });

        viewCartButton.setOnClickListener(v -> {
            Intent intent = new Intent(LivingCategoryActivity.this, CartActivity.class);
            startActivity(intent);
        });

        viewFavoritesButton.setOnClickListener(v -> {
            Intent intent = new Intent(LivingCategoryActivity.this, FavouritesActivity.class);
            startActivity(intent);
        });
    }
}
