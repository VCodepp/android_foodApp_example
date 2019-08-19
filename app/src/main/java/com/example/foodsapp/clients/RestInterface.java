package com.example.foodsapp.clients;

import com.example.foodsapp.entities.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestInterface {
    @GET("menu.json")
    Call<List<Repo>> getRepo();
}
