package com.example.user.derlengbeta.Model;

/**
 * Created by user on 26-Nov-18.
 */

public class Categories {
    private String CategoriesID;
    private String Name;
    private String Image;
    private String Price;

    public Categories() {
    }

    public Categories(String categoriesID, String name, String image, String price) {
        CategoriesID = categoriesID;
        Name = name;
        Image = image;
        Price = price;
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

    public String getCategoriesID() {
        return CategoriesID;
    }

    public void setCategoriesID(String categoriesID) {
        CategoriesID = categoriesID;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
