package com.example.prodromos.vrmkls_movies;

import android.provider.BaseColumns;


public final class MovieContract {


    public MovieContract() {}

    public static abstract class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_RELEASE_DATE = "release";
        public static final String COLUMN_NAME_RATING = "rating";

    }


    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MovieEntry.TABLE_NAME + " (" +
                    MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    MovieEntry.COLUMN_NAME_TITLE + " TEXT,"  +
                    MovieEntry.COLUMN_NAME_RELEASE_DATE + " INTEGER," +
                    MovieEntry.COLUMN_NAME_RATING + " INTEGER," +
                    "UNIQUE (" +MovieEntry.COLUMN_NAME_TITLE + "," + MovieEntry.COLUMN_NAME_RELEASE_DATE + ")" +" )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME;
}
