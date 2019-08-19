package com.example.foodsapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodsapp.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodInfoActivity extends AppCompatActivity {
    TextView name,price;
    CircleImageView image;
    WebView feature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);

        final Intent intent = getIntent();

        name = findViewById(R.id.food_info_item_name);
        name.setText(intent.getStringExtra("name"));

        price = findViewById(R.id.food_info_item_price);
        price.setText(intent.getStringExtra("price")+" ₺");

        feature = findViewById(R.id.food_info_item_feature);
        WebSettings ws = feature.getSettings();
        feature.setBackgroundColor(Color.TRANSPARENT);
        ws.setJavaScriptEnabled(true);

        // webview options
        feature.setWebViewClient(new WebViewClient() {
            // HTML page before load
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
            }

            // HTML page after load
            @Override
            public void onPageFinished(WebView view, String url) {
                // HTML page finish loaded run javascript function HTML inside;
                feature.loadUrl("javascript:plot('"+ intent.getStringExtra("feature") + "');");
            }
        });
        // webview include HTML file
        feature.loadUrl("file:///android_asset/index.html");

        image = findViewById(R.id.food_info_item_image);
        // picasso Library
        Picasso.get()
                .load(intent.getStringExtra("image"))
                .resize(250, 250)
                .centerCrop()
                .into(image);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // HTML file inside run javascript function
        feature.loadUrl("javascript:destroy();");

    }
}
