package com.example.user.derlengbeta.Model;

/**
 * Created by user on 26-Nov-18.
 */

public class Categories {
    private String Name;
    private String Image;

    public Categories() {
    }

    public Categories(String name, String image) {
        Name = name;
        Image = image;
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
}
