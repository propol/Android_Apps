package com.example.prodromos.vrmkls_movies;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.prodromos.vrmkls_movies.MovieContract.*;

public class UneditableList extends AppCompatActivity {

    private DBHelper dbHelper;
    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uneditable_list);

        Intent intent = getIntent();
        id = intent.getLongExtra("key1", -99);
        if (id != -99) {
            String[] projection = new String[]{

                    MovieEntry._ID,
                    MovieEntry.COLUMN_NAME_TITLE,
                    MovieEntry.COLUMN_NAME_RELEASE_DATE,
                    MovieEntry.COLUMN_NAME_RATING
            };

            dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(MovieEntry.TABLE_NAME, projection, "_ID = ?", new String[]{"" + id}, null, null, null, null);
            cursor.moveToFirst();

            String title = cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_NAME_TITLE));
            EditText etTitle = (EditText) findViewById(R.id.title);
            etTitle.setText(title);

            String date = cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_NAME_RELEASE_DATE));
            EditText etReleaseDate = (EditText) findViewById(R.id.releasedate);
            etReleaseDate.setText(date);

            String rating = cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_NAME_RATING));
            EditText etNote = (EditText) findViewById(R.id.rating);
            etNote.setText(rating);

        }
    }
}

