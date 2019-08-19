package com.example.foodsapp.activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.example.foodsapp.R;
import com.example.foodsapp.clients.ApiClient;
import com.example.foodsapp.clients.RestInterface;
import com.example.foodsapp.entities.Repo;
import com.example.foodsapp.storage.FoodRepo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RestInterface restInterface;
    ImageView background;
    MaterialRippleLayout ripple;
    int animationTiming = 1250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ripple = findViewById(R.id.btn_joinApp);
        background = findViewById(R.id.background);

        // I didn't understand that either :/
        // But I think it's about request :|
        restInterface = ApiClient.getClient().create(RestInterface.class);
        Call<List<Repo>> call = restInterface.getRepo();

        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                List<Repo> repoList = new ArrayList<>();
                repoList = response.body();
                for (int i = 0; i < repoList.size(); i++) {
                    // Add items FoodRepo list
                    FoodRepo.Instance().setFoodsList(repoList);
                }
            }


            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                // Maybe some error write
            }
        });

        // Start First Animation
        animationButton(); // button animator
        animationBackground(); // bg animator

        // SECONT ACTIVITY
        ripple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MenuListActivity.class));
            }
        });

    }

    private void animationBackground() {
        // Background scale animation
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(background, "scaleX", 1.5f, 1f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(background, "scaleY", 1.5f, 1f);

        scaleDownX.setDuration(animationTiming);
        scaleDownY.setDuration(animationTiming);

        // animator set and start
        AnimatorSet animation = new AnimatorSet();
        animation.play(scaleDownX)
                .with(scaleDownY);
        animation.start();
    }

    private void animationButton() {
        // animator (Translate)
        ObjectAnimator Translate = ObjectAnimator.ofFloat(ripple, "translationY", 500f, 0f);

        Translate.setDuration(animationTiming);

        // animator set and start
        AnimatorSet animation = new AnimatorSet();
        animation.play(Translate);
        animation.start();
    }
}
