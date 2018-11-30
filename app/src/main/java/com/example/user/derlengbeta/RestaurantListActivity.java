package com.example.user.derlengbeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.derlengbeta.Interface.ItemClickListener;
import com.example.user.derlengbeta.Model.Food;
import com.example.user.derlengbeta.Model.Restaurants;
import com.example.user.derlengbeta.ViewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * Created by user on 27-Nov-18.
 */

public class RestaurantListActivity extends AppCompatActivity{
    TextView restaurant_name, restaurant_price, restaurant_description;
//    TextView food_name;
    ImageView restaurant_img;
//    ImageView food_img;

    CollapsingToolbarLayout collapsingToolbarLayout;

    String restaurantID="";

    FirebaseDatabase database;
    DatabaseReference restaurants,foods;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    Button foodbtn;

    FirebaseRecyclerAdapter<Restaurants,FoodViewHolder> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

//        Firebase
        database = FirebaseDatabase.getInstance();
        restaurants = database.getReference("Restaurant");
//        foods=database.getReference("Restaurant").child(restaurantID).child("Detail").child("Food");

        restaurant_name = (TextView)findViewById(R.id.food_name);
        restaurant_price = (TextView)findViewById(R.id.food_price);
//        restaurant_description = (TextView)findViewById(R.id.food_description);
        restaurant_img = (ImageView)findViewById(R.id.img_food);

//        food_name = (TextView)findViewById(R.id.food_txt);
//        food_img=(ImageView)findViewById(R.id.food_img);

//        //        Load Menu
//        recyclerView = (RecyclerView)findViewById(R.id.recycler_food);
//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

//        foodbtn = (Button)findViewById(R.id.food_link);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);

//        Get Id from Intent
        if(getIntent()!=null)
            restaurantID=getIntent().getStringExtra("RestaurantID");
        if(!restaurantID.isEmpty()){
            getDetailRestaurant(restaurantID);
        }

//        foodbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent foodIntent = new Intent(RestaurantListActivity.this,FoodListActivity.class);
//                startActivity(foodIntent);
//
////                restaurants.addValueEventListener(new ValueEventListener() {
////                    @Override
////                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//////                        String rest = dataSnapshot.getKey();
////                        Intent foodIntent = new Intent(RestaurantListActivity.this,FoodListActivity.class);
//////                        foodIntent.putExtra("RestaurantID",restaurantID);
////                        startActivity(foodIntent);
////                    }
////
////                    @Override
////                    public void onCancelled(@NonNull DatabaseError databaseError) {
////
////                    }
////                });
//            }
//        });
    }

    private void getDetailRestaurant(String restaurantID) {

        restaurants.child(restaurantID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Restaurants restaurants = dataSnapshot.getValue(Restaurants.class);
                Picasso.with(getBaseContext()).load(restaurants.getImage())
                        .into(restaurant_img);

                collapsingToolbarLayout.setTitle(restaurants.getName());
                restaurant_price.setText(restaurants.getPrice());

                restaurant_name.setText(restaurants.getName());

//                restaurant_description.setText(restaurants.getDescription());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
