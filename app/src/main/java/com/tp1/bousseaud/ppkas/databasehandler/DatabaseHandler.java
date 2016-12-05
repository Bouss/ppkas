package com.tp1.bousseaud.ppkas.databasehandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static DatabaseHandler sInstance;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ppkas";

    public static final String TABLE_NEWS = "news";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PLACE = "place";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";
    public static final String COLUMN_PICTURE = "picture";
    public static final String COLUMN_THEMATIC = "thematic";

    private static final String SQL_CREATE_NEWS_TABLE =
            "CREATE TABLE " + TABLE_NEWS + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY, "
                    + COLUMN_TITLE + " TEXT, "
                    + COLUMN_DESCRIPTION + " TEXT, "
                    + COLUMN_PLACE + " TEXT, "
                    + COLUMN_CREATED_AT + " TEXT, "
                    + COLUMN_UPDATED_AT + " TEXT, "
                    + COLUMN_PICTURE + " TEXT, "
                    + COLUMN_THEMATIC + " TEXT )";

    private static final String SQL_DROP_NEWS_TABLE = "DROP TABLE IF EXISTS " + TABLE_NEWS;

    /**
     * DatabaseHandler as a singleton
     */
    public static synchronized DatabaseHandler getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHandler(context.getApplicationContext());
        }

        return sInstance;
    }

    private DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_NEWS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_NEWS_TABLE);
        onCreate(db);
    }
}
