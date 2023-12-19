package com.atenea.ecoevent.models;

import java.util.Date;

public class Event {

    private String name;
    private Date date;
    private Integer food;
    private Integer drink;
    private Integer decoration;
    private Integer reused;
    private Integer recycled;

    public Event(String name, Date date, Integer food, Integer drink, Integer decoration, Integer reused, Integer recycled) {
        this.name = name;
        this.date = date;
        this.food = food;
        this.drink = drink;
        this.decoration = decoration;
        this.reused = reused;
        this.recycled = recycled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getFood() {
        return food;
    }

    public void setFood(Integer food) {
        this.food = food;
    }

    public Integer getDrink() {
        return drink;
    }

    public void setDrink(Integer drink) {
        this.drink = drink;
    }

    public Integer getDecoration() {
        return decoration;
    }

    public void setDecoration(Integer decoration) {
        this.decoration = decoration;
    }

    public Integer getReused() {
        return reused;
    }

    public void setReused(Integer reused) {
        this.reused = reused;
    }

    public Integer getRecycled() {
        return recycled;
    }

    public void setRecycled(Integer recycled) {
        this.recycled = recycled;
    }

}
