package com.tp1.bousseaud.ppkas.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.tp1.bousseaud.ppkas.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

@Table(name = "News")
public class News extends Model {

    public static final int THEMATIC_OTHER = 0;
    public static final int THEMATIC_ANIMAL_PRESERVATION = 1;
    public static final int THEMATIC_CLIMATIC_WARMING = 2;
    public static final int THEMATIC_WORLD_MALNUTRITION = 3;
    public static final int THEMATIC_PLANNING = 4;

    public static final int[] THEMATICS = {
            THEMATIC_OTHER,
            THEMATIC_ANIMAL_PRESERVATION,
            THEMATIC_CLIMATIC_WARMING,
            THEMATIC_WORLD_MALNUTRITION,
            THEMATIC_PLANNING
    };

    // (Temporary) Because the news of "News API" don't have any ID, set "title" as the primary key
    @Column(name = "Title", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
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
    private int thematic;

    public News() {
        super();
    }

    public News(String title, String description, String place, Date createdAt, Date updatedAt, String picture, int thematic) {
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
            news.setCreatedAt(Util.stringToDate(o.getString("publishedAt"), Util.DATE_FORMAT));
            news.setUpdatedAt(null);
            news.setPicture(o.getString("urlToImage"));
            news.setThematic(0);
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

    public static List<News> getByThematic(int thematic) {
        return new Select()
                .from(News.class)
                .where("Thematic = ? ", thematic)
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

    public int getThematic() {
        return thematic;
    }

    public void setThematic(int thematic) {
        this.thematic = thematic;
    }
}
