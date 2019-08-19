package com.example.foodsapp.storage;

import com.example.foodsapp.entities.Shop;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {
    private static ShoppingList instance;
    private List<Shop> shoppingList = new ArrayList<Shop>();

    public static ShoppingList instance() {
        if (instance == null) {
            instance = new ShoppingList();
        }
        return instance;
    }

    public List<Shop> getShoppingList() {
        return shoppingList;
    }

    // increment or add list
    public void setShoppingList(Shop food) {
        List<Shop> getList = this.shoppingList;

        if(getList.size() != 0){
            for(Shop item : getList){
                if(item.id.equals(food.id)){
                    item.setQuantity(item.quantity+1);
                    return;
                }else{
                    this.shoppingList.add((Shop) food);
                    return;
                }
            }
        }else{
            // add item
            this.shoppingList.add((Shop) food);
        }
    }

    // SHOP CARD COUNT QUANTITY
    public int getTotalQuantitiy() {
        List<Shop> list = this.getShoppingList();
        int totalQuantity = 0;
        for(Shop item : list){
            totalQuantity = totalQuantity+item.quantity;
        }
        return totalQuantity;
    }

    // total amount calculation
    public float getTotalAmount() {
        float totalAmount = (float) 0.0;
        for(Shop item : this.getShoppingList()){
            totalAmount = (item.price * item.quantity) + totalAmount;
        }
        return totalAmount;
    }

    // decrement list
    public void decrementItem(Shop food) {
        List<Shop> getList = this.getShoppingList();
        for(Shop item : getList){
            if(item.getId().equals(food.id) && item.getQuantity()>1){
                if(item.getQuantity() == 1){
                    getList.remove(item);
                }else if(item.getQuantity()>1){
                    item.setQuantity(item.getQuantity() - 1);
                }
            }
        }
    }
}
