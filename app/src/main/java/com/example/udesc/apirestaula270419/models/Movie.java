package com.example.udesc.apirestaula270419.models;

public class Movie {
    String id;
    private String title;
    private int duration;
    private double cost;
    private int year;
    private int director_id;
    private String currency;

    public Movie() {}

    public Movie(String id, String title, int duration, double cost, int year, int director_id, String currency) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.cost = cost;
        this.year = year;
        this.director_id = director_id;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDirector_id() {
        return director_id;
    }

    public void setDirector_id(int director_id) {
        this.director_id = director_id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", cost=" + cost +
                ", year=" + year +
                ", director_id=" + director_id +
                ", currency='" + currency + '\'' +
                '}';
    }
}