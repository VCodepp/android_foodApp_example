package com.example.foodsapp.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repo {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("feature")
    @Expose
    public String feature;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("price")
    @Expose
    public float price;

}