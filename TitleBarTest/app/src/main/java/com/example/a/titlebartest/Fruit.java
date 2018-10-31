package com.example.a.titlebartest;

public class Fruit {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Fruit(String name, int imageId) {

        this.name = name;
        this.imageId = imageId;
    }

    private int imageId;

}
