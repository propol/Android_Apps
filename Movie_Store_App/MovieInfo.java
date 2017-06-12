package com.example.prodromos.vrmkls_movies;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prodromos.vrmkls_movies.MovieContract.*;

public class MovieInfo extends AppCompatActivity {

    private DBHelper dbHelper;
    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

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

    public void save(View view) {
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        String title = ((EditText) findViewById(R.id.title)).getText().toString();
        String releasedate = ((EditText) findViewById(R.id.releasedate)).getText().toString();
        String rating = ((EditText) findViewById(R.id.rating)).getText().toString();

        if (title.isEmpty()) {
            Toast.makeText(this, this.getString(R.string.emptytitle), Toast.LENGTH_LONG).show();

        } else if (releasedate.isEmpty()) {
            Toast.makeText(this, this.getString(R.string.emptydate), Toast.LENGTH_LONG).show();

        }else if(rating.isEmpty()  || Integer.valueOf(rating)>10 || Integer.valueOf(rating)<0  ){
            Toast.makeText(this, this.getString(R.string.invalidrating), Toast.LENGTH_LONG).show();

        } else {

            values.put(MovieEntry.COLUMN_NAME_TITLE, title);
            values.put(MovieEntry.COLUMN_NAME_RELEASE_DATE, Integer.valueOf(releasedate));
            values.put(MovieEntry.COLUMN_NAME_RATING, Integer.valueOf(rating));


            long newRowId = db.update(
                    MovieEntry.TABLE_NAME,
                    values,
                    "_id=" + id, null);

            if (newRowId == -1) {
                Toast.makeText(this, this.getString(R.string.failedinsert), Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);

                finish();
            }
        }
    }

}