package com.example.user.derlengbeta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.derlengbeta.Common.Common;
import com.example.user.derlengbeta.Interface.ItemClickListener;
import com.example.user.derlengbeta.Model.Food;
import com.example.user.derlengbeta.Model.Restaurants;
import com.example.user.derlengbeta.ViewHolder.FoodViewHolder;
import com.example.user.derlengbeta.ViewHolder.RestaurantViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FoodListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodList;

    FirebaseRecyclerAdapter<Food,FoodViewHolder> adapter;

//    String restaurantID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

//        firebase
        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Food");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("FOOD");
        setSupportActionBar(toolbar);
        getFoodID();
//        if(getIntent()!=null){
//            restaurantID=getIntent().getStringExtra("RestaurantID");
//        }
//        if(!restaurantID.isEmpty()){
//            getFoodID(restaurantID);
//        }

    }

    private void getFoodID() {
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class, R.layout.food_item_activity, FoodViewHolder.class, foodList) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, int position) {
                viewHolder.food_txt.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.food_img);

                final Food food = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(FoodListActivity.this, ""+food.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        Log.d("TAG","LoadListFood"+adapter.getItemCount());
        recyclerView.setAdapter(adapter);
    }
}
