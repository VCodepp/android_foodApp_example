package com.example.foodsapp.storage;

import com.example.foodsapp.entities.Repo;

import java.util.ArrayList;
import java.util.List;

public class FoodRepo {

    private static FoodRepo instance;
    private List<Repo> foodsList = new ArrayList<Repo>();
    private FoodRepo(){}

    public static FoodRepo Instance() {
        if(instance == null){
            instance = new FoodRepo();
        }
        return instance;
    }

    public List<Repo> getFoodsList() {
        return foodsList;
    }

    public void setFoodsList(List<Repo> list){
        this.foodsList = list;
    }
}
