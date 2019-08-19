package com.example.foodsapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.foodsapp.R;
import com.example.foodsapp.adapters.FoodsRecyclerAdapter;
import com.example.foodsapp.entities.Repo;
import com.example.foodsapp.storage.FoodRepo;

import java.util.ArrayList;
import java.util.List;

public class MenuListActivity extends AppCompatActivity {
    private List<Repo> list = new ArrayList<>();
    public static TextView totalAmount, totalQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        totalAmount = findViewById(R.id.totalAmount);
        totalQuantity = findViewById(R.id.totalQuantity);

        // get food list
        List<Repo> foodlist = FoodRepo.Instance().getFoodsList();

        RecyclerView recyclerView = findViewById(R.id.food_recyclerview);

        // new adapter
        FoodsRecyclerAdapter mAdapter = new FoodsRecyclerAdapter(foodlist);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }
}
