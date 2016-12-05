package com.tp1.bousseaud.ppkas.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tp1.bousseaud.ppkas.databasehandler.DatabaseHandler;
import com.tp1.bousseaud.ppkas.model.News;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsDAO {

    private static NewsDAO sInstance;
    private DatabaseHandler dbh;

    /**
     * NewsDAO as a singleton
     */
    public static synchronized NewsDAO getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new NewsDAO(context.getApplicationContext());
        }

        return sInstance;
    }

    private NewsDAO(Context context) {
        this.dbh = DatabaseHandler.getInstance(context);
    }

    public void add(News news) {
        SQLiteDatabase db = dbh.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.COLUMN_ID, news.getId());
        values.put(DatabaseHandler.COLUMN_TITLE, news.getTitle());
        values.put(DatabaseHandler.COLUMN_DESCRIPTION, news.getDescription());
        values.put(DatabaseHandler.COLUMN_PLACE, news.getPlace());
        values.put(DatabaseHandler.COLUMN_CREATED_AT, news.getCreatedAt().toString());
        values.put(DatabaseHandler.COLUMN_UPDATED_AT, news.getUpdatedAt().toString());
        values.put(DatabaseHandler.COLUMN_PICTURE, news.getPicture());
        values.put(DatabaseHandler.COLUMN_THEMATIC, news.getThematic());

        db.insert(DatabaseHandler.TABLE_NEWS, null, values);
    }

    public News getById(int id) {
        String selectQuery = "SELECT * FROM " + DatabaseHandler.TABLE_NEWS + " WHERE " + DatabaseHandler.COLUMN_ID + "=?";

        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, new String[] { String.valueOf(id) });

        if (c != null) {
            c.moveToFirst();
        }

        News news = cursorToNews(c);

        c.close();

        return news;
    }

    public List<News> getByThematic(String thematic) {
        List<News> newsList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DatabaseHandler.TABLE_NEWS + " WHERE " +  DatabaseHandler.COLUMN_THEMATIC + "=?";

        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, new String[] { thematic });

        if (c.moveToFirst()) {
            do {
                newsList.add(cursorToNews(c));

            } while (c.moveToNext());
        }

        c.close();

        return newsList;
    }

    private News cursorToNews(Cursor c) {
        News news = new News();
        news.setId(Integer.parseInt(c.getString(0)));
        news.setTitle(c.getString(1));
        news.setDescription(c.getString(2));
        news.setPlace(c.getString(3));
        news.setCreatedAt(stringToDate(c.getString(4)));
        news.setUpdatedAt(stringToDate(c.getString(5)));
        news.setPicture(c.getString(6));
        news.setThematic(c.getString(7));

        return news;
    }

    private Date stringToDate(String dateAsString) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE);
        try {
            date = format.parse(dateAsString);
        } catch(ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
