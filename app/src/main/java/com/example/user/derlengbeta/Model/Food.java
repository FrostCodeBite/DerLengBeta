package com.example.user.derlengbeta.Model;

/**
 * Created by user on 26-Nov-18.
 */

public class Food {
    private String Name, Image, Price, FoodID;

    public Food() {
    }

    public Food(String name, String image, String price, String foodID) {
        Name = name;
        Image = image;
        Price = price;
        FoodID = foodID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getFoodID() {
        return FoodID;
    }

    public void setFoodID(String foodID) {
        FoodID = foodID;
    }
}
