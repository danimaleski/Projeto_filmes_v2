package com.example.udesc.apirestaula270419.models;

import java.util.List;

public class Director {
    private String id;
    private String name;
    private String birth;
    private String country;
    private List<Movie> movies;

    public Director() { }

    public Director(String Id, String name) {
        this.id = Id;
        this.name = name;
    }
    //alt + insert para criar os getter and setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return name;
    }
}
