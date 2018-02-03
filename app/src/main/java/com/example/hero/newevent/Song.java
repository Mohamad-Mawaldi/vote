package com.example.hero.newevent;

/**
 * Created by Hammode on 2/3/18.
 */

public class Song {
    String name;
    Integer id;

    public Song(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



}
