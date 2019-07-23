package com.example.user.derlengbeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.derlengbeta.Common.Common;
import com.example.user.derlengbeta.Interface.ItemClickListener;
import com.example.user.derlengbeta.Model.Food;
import com.example.user.derlengbeta.ViewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * Created by user on 27-Nov-18.
 */

public class RestaurantListActivity extends AppCompatActivity {
    TextView restaurant_name, restaurant_price;
    ImageView restaurant_img;

    String restaurantID = "";

    CollapsingToolbarLayout collapsingToolbarLayout;

    FirebaseDatabase database;
    DatabaseReference foods;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Food,FoodViewHolder> adapter;

    Button mapBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

//        Firebase
        database = FirebaseDatabase.getInstance();
        foods = database.getReference("Food");


        restaurant_name = (TextView)findViewById(R.id.food_name);
        restaurant_price = (TextView)findViewById(R.id.food_price);
//        restaurant_description = (TextView)findViewById(R.id.food_description);
        restaurant_img = (ImageView)findViewById(R.id.img_food);

//        food_name = (TextView)findViewById(R.id.food_txt);
//        food_img=(ImageView)findViewById(R.id.food_img);

//        foodbtn = (Button)findViewById(R.id.food_link);
        mapBtn = (Button) findViewById(R.id.map_link);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(RestaurantListActivity.this,MapsActivity.class);
                mapIntent.putExtra("RestaurantID",Common.currentCategories.getCategoriesID());
                startActivity(mapIntent);
            }
        });

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);

        //        Load Menu
                recyclerView = (RecyclerView)findViewById(R.id.recycler_food);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
//                getFoodDetail();
//                restaurant_description.setText(restaurants.getDescription());

//        Get Id from Intent
        if(getIntent()!=null)
            restaurantID=getIntent().getStringExtra("RestaurantID");
        if(!restaurantID.isEmpty()&&restaurantID!=null){
            getDetailRestaurant(restaurantID);
        }
    }

    private void getDetailRestaurant(String restaurantID) {
        Picasso.with(getBaseContext()).load(Common.currentCategories.getImage())
                .into(restaurant_img);

        collapsingToolbarLayout.setTitle(Common.currentCategories.getName());
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorTransparent));

        restaurant_price.setText(Common.currentCategories.getPrice());

        restaurant_name.setText(Common.currentCategories.getName());
        
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,
                R.layout.food_item_activity,
                FoodViewHolder.class,
                foods.orderByChild("FoodID").equalTo(restaurantID)) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, int position) {
                viewHolder.food_name_txt.setText(model.getName());
                viewHolder.food_price_txt.setText(model.getPrice());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.food_img);

                final Food foodItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(RestaurantListActivity.this,""+foodItem.getName(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

//    private void getDetailRestaurant(String restaurantID) {
//
//        foods.child(restaurantID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Restaurants restaurants = dataSnapshot.getValue(Restaurants.class);
//                Picasso.with(getBaseContext()).load(restaurants.getImage())
//                        .into(restaurant_img);
//
//                collapsingToolbarLayout.setTitle(restaurants.getName());
//                collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorTransparent));
//
//                restaurant_price.setText(restaurants.getPrice());
//
//                restaurant_name.setText(restaurants.getName());
//
////                restaurant_description.setText(restaurants.getDescription());
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

//    private void getFoodDetail(){
//        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,R.layout.food_item_activity,FoodViewHolder.class,foods) {
//            @Override
//            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, int position) {
//                viewHolder.food_txt.setText(model.getName());
//                Picasso.with(getBaseContext()).load(model.getImage())
//                        .into(viewHolder.food_img);
//                final Food foodItem = model;
//                viewHolder.setItemClickListener(new ItemClickListener() {
//                    @Override
//                    public void onClick(View view, int position, boolean isLongClick) {
//                        Toast.makeText(RestaurantListActivity.this,""+foodItem.getName(),Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
//                });
//            }
//        };
//        recyclerView.setAdapter(adapter);
//    }
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