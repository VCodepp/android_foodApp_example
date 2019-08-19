package com.example.foodsapp.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodsapp.R;
import com.example.foodsapp.activities.FoodInfoActivity;
import com.example.foodsapp.activities.MenuListActivity;
import com.example.foodsapp.entities.Repo;
import com.example.foodsapp.entities.Shop;
import com.example.foodsapp.storage.ShoppingList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodsRecyclerAdapter extends RecyclerView.Adapter<FoodsRecyclerAdapter.MyViewHolder> {

    private List<Repo> list = new ArrayList<Repo>();

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView price, name, totalAmount, totalQuantity, itemQuantity;
        CircleImageView url_image;
        Button btn_decrement, btn_increment;


        MyViewHolder(View view) {
            super(view);
            price = (TextView) view.findViewById(R.id.row_food_item_price);
            name = (TextView) view.findViewById(R.id.row_food_item_name);
            url_image = (CircleImageView) view.findViewById(R.id.row_food_item_image);
            itemQuantity = (TextView) view.findViewById(R.id.row_food_quantity);
            btn_decrement = (Button) view.findViewById(R.id.btn_decrease);
            btn_increment = (Button) view.findViewById(R.id.btn_increase);
        }
    }


    public FoodsRecyclerAdapter(List<Repo> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_food_recyclerview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Repo food = list.get(position);

        final Shop foodItem = new Shop();
        foodItem.setId(food.id);
        foodItem.setName(food.name);
        foodItem.setPrice(food.price);
        foodItem.setQuantity(1);

        holder.name.setText(food.name);
        holder.price.setText(food.price + " ₺");
        Picasso.get()
                .load(food.image)
                .resize(50, 50)
                .centerCrop()
                .into(holder.url_image);
        holder.url_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // shared animator activity
                Pair[] pairs = new Pair[3];
                pairs[0] = new Pair<View, String>(holder.url_image, "imageMain");
                pairs[1] = new Pair<View, String>(holder.name, "nameMain");
                pairs[2] = new Pair<View, String>(holder.price, "priceMain");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) holder.name.getContext(), pairs);

                Intent intent = new Intent(holder.name.getContext(), FoodInfoActivity.class);
                intent.putExtra("feature", food.feature);
                intent.putExtra("image", food.image);
                intent.putExtra("name", food.name);
                intent.putExtra("price", food.price);

                holder.name.getContext().startActivity(intent, options.toBundle());

            }
        });

        holder.btn_increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShoppingList.instance().setShoppingList((Shop) foodItem);

                // update status bar shopping info
                String totalAmountText = String.valueOf(ShoppingList.instance().getTotalQuantitiy());
                MenuListActivity.totalQuantity.setText(totalAmountText);
                float totalQuantityText = Float.parseFloat(String.valueOf(ShoppingList.instance().getTotalAmount()));
                MenuListActivity.totalAmount.setText(String.valueOf(totalQuantityText));

                // increment quantity
                String getTextQuantity = holder.itemQuantity.getText().toString();
                holder.itemQuantity.setText(String.valueOf(Integer.parseInt(getTextQuantity) + 1));

            }
        });

        holder.btn_decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShoppingList.instance().decrementItem(foodItem);

                // update status bar shopping info
                String totalAmountText = String.valueOf(ShoppingList.instance().getTotalQuantitiy());
                MenuListActivity.totalQuantity.setText(totalAmountText);
                float totalQuantityText = Float.parseFloat(String.valueOf(ShoppingList.instance().getTotalAmount()));
                MenuListActivity.totalAmount.setText(String.valueOf(totalQuantityText));

                // decrement quantity
                if (Integer.parseInt(holder.itemQuantity.getText().toString()) > 0) {
                    String getTextQuantity = holder.itemQuantity.getText().toString();
                    holder.itemQuantity.setText(String.valueOf(Integer.parseInt(getTextQuantity) - 1));
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}