package com.tp1.bousseaud.ppkas.model;

import java.util.Date;

public class News {

    private long id;
    private String title;
    private String description;
    private String place;
    private Date createdAt;
    private Date updatedAt;
    private String picture;
    private String thematic;

    public static final String THEMATIC_ANIMAL_PRESERVATION = "animal_preservation";
    public static final String THEMATIC_CLIMATIC_WARMING = "climatic_warming";
    public static final String THEMATIC_WORLD_MALNUTRITION = "world_malnutrition";
    public static final String THEMATIC_PLANNING = "planning";

    public static final String[] THEMATICS = {
            THEMATIC_ANIMAL_PRESERVATION,
            THEMATIC_CLIMATIC_WARMING,
            THEMATIC_WORLD_MALNUTRITION,
            THEMATIC_PLANNING
    };

    public News() {

    }

    public News(long id, String title, String description, String place, Date createdAt, Date updatedAt, String picture, String thematic) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.place = place;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.picture = picture;
        this.thematic = thematic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getThematic() {
        return thematic;
    }

    public void setThematic(String thematic) {
        this.thematic = thematic;
    }
}
