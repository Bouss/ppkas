package com.tp1.bousseaud.ppkas.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.tp1.bousseaud.ppkas.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

@Table(name = "News")
public class News extends Model {

    @Column(name = "Title")
    private String title;
    @Column(name = "Description")
    private String description;
    @Column(name = "Place")
    private String place;
    @Column(name = "CreatedAt")
    private Date createdAt;
    @Column(name = "UpdatedAt")
    private Date updatedAt;
    @Column(name = "Picture")
    private String picture;
    @Column(name = "Thematic")
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
        super();
    }

    public News(String title, String description, String place, Date createdAt, Date updatedAt, String picture, String thematic) {
        super();
        this.title = title;
        this.description = description;
        this.place = place;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.picture = picture;
        this.thematic = thematic;
    }

    public static News JSONObjectToNews(JSONObject o) {
        News news = new News();

        try {
            news.setTitle(o.getString("title"));
            news.setDescription(o.getString("description"));
            news.setPlace(null);
            news.setCreatedAt(Utils.stringToDate(o.getString("publishedAt"), Utils.DATE_FORMAT));
            news.setUpdatedAt(null);
            news.setPicture(o.getString("urlToImage"));
            news.setThematic(null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return news;
    }

    public static List<News> getAll() {
        return new Select()
                .from(News.class)
                .orderBy("CreatedAt DESC")
                .execute();
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
