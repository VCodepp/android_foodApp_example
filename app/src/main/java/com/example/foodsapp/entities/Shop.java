package com.example.foodsapp.entities;

public class Shop {
    public String id;

    public String name;

    public float price;

    public int quantity = 0; // default  value

    // get

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getId(){return id;}

    // set

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantitiy) {
        this.quantity = quantitiy;
    }

}

