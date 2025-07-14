package com.example.furnitureapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private List<Product> cartProducts;
    private ProductAdapter adapter;
    private TextView totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartProducts = StorageUtil.loadCart(this);
        if (cartProducts == null) {
            cartProducts = new ArrayList<>();
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductAdapter(cartProducts, product -> {});
        recyclerView.setAdapter(adapter);

        totalTextView = findViewById(R.id.total_text_view);
        updateTotal();

        Button clearCartButton = findViewById(R.id.clear_cart_button);
        clearCartButton.setOnClickListener(v -> {
            cartProducts.clear();
            StorageUtil.saveCart(CartActivity.this, cartProducts);
            adapter.notifyDataSetChanged();
            updateTotal();
            Toast.makeText(CartActivity.this, "Cart cleared", Toast.LENGTH_SHORT).show();
        });
    }

    private void updateTotal() {
        double total = 0;
        for (Product product : cartProducts) {
            total += product.getPrice();
        }
        totalTextView.setText(String.format("Total: $%.2f", total));
    }
}
