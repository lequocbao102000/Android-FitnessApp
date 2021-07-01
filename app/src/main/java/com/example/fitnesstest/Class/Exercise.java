package com.example.fitnesstest.Class;

import android.widget.ListView;

import java.io.Serializable;

public class Exercise implements Serializable {
    int id;
    String name;
    String desription;
    int gif;
    int level;
    int idworkout;

    public int getIdworkout() {
        return idworkout;
    }

    public void setIdworkout(int idworkout) {
        this.idworkout = idworkout;
    }

    public Exercise(String name, String desription, int gif, int level, int idworkout) {
        this.name = name;
        this.desription = desription;
        this.gif = gif;
        this.level = level;
        this.idworkout = idworkout;
    }

    public Exercise(int id, String name, String desription, int gif, int level,int idworkout){
        this.id = id;
        this.name = name;
        this.desription = desription;
        this.gif = gif;
        this.level = level;
        this.idworkout=idworkout;
    }

    public Exercise(String name, String desription, int gif, int level) {
        this.name = name;
        this.desription = desription;
        this.gif = gif;
        this.level = level;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public int getGif() {
        return gif;
    }

    public void setGif(int gif) {
        this.gif = gif;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
