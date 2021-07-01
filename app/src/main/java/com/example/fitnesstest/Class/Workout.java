package com.example.fitnesstest.Class;

public class Workout {
    int image;
    String name;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Workout(int id, int image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;

    }

    public Workout(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
