package com.example.user.derlengbeta.Model;

/**
 * Created by user on 26-Nov-18.
 */

public class Restaurants {
    private String Name, Image, Description, Price, Discount, RestaurantID;

    public Restaurants() {
    }

    public Restaurants(String name, String image, String description, String price, String discount, String restaurantID) {
        Name = name;
        Image = image;
        Description = description;
        Price = price;
        Discount = discount;
        RestaurantID = restaurantID;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getRestaurantID() {
        return RestaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        RestaurantID = restaurantID;
    }
}
