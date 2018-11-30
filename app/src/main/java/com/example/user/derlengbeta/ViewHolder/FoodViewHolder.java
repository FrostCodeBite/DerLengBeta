package com.example.user.derlengbeta.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.derlengbeta.Interface.ItemClickListener;
import com.example.user.derlengbeta.R;

/**
 * Created by user on 26-Nov-18.
 */

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView food_txt;
    public ImageView food_img;

    private ItemClickListener itemClickListener;

    public FoodViewHolder(View itemView) {
        super(itemView);

        food_txt = (TextView)itemView.findViewById(R.id.food_txt);
        food_img = (ImageView)itemView.findViewById(R.id.food_img);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(),false);
    }
}
