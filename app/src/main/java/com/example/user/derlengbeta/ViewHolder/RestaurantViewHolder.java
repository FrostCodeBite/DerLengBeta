package com.example.user.derlengbeta.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.derlengbeta.Interface.ItemClickListener;
import com.example.user.derlengbeta.R;

import org.w3c.dom.Text;

/**
 * Created by user on 26-Nov-18.
 */

public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView restaurant_txt;
    public ImageView restaurant_img;

    private ItemClickListener itemClickListener;

    public RestaurantViewHolder(View itemView) {
        super(itemView);

        restaurant_txt = (TextView)itemView.findViewById(R.id.restaurant_txt);
        restaurant_img = (ImageView)itemView.findViewById(R.id.restaurant_img);

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
