package com.example.furnitureapp;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class StorageUtil {

    private static final String PREFERENCES_FILE = "com.example.furnitureapp.preferences";
    private static final String FAVORITES_KEY = "favorites";
    private static final String CART_KEY = "cart";

    public static void saveFavorites(Context context, List<Product> favorites) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(favorites);
        editor.putString(FAVORITES_KEY, json);
        editor.apply();
    }

    public static List<Product> loadFavorites(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(FAVORITES_KEY, null);
        Type type = new TypeToken<List<Product>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void saveCart(Context context, List<Product> cart) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cart);
        editor.putString(CART_KEY, json);
        editor.apply();
    }

    public static List<Product> loadCart(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(CART_KEY, null);
        Type type = new TypeToken<List<Product>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
