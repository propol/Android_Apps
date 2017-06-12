package com.example.prodromos.vrmkls_movies;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.prodromos.vrmkls_movies.MovieContract.*;

import java.util.ArrayList;
import java.util.Vector;

public class UpdateList extends AppCompatActivity {

    private SimpleCursorAdapter adapter;
    SQLiteDatabase db;
    String[] projection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_list);
        ListView lv = (ListView) findViewById(R.id.listView);

        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();

        projection = new String[]{
                MovieEntry._ID,
                MovieEntry.COLUMN_NAME_TITLE,
                "rowid"

        };

        Cursor c = db.query(
                MovieEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null




        );

        String[] fromColumns = {MovieEntry.COLUMN_NAME_TITLE};
        int[] toViews = {R.id.item};
        adapter = new SimpleCursorAdapter(this,
                R.layout.list_items, c, fromColumns, toViews, 0);

        lv.setAdapter(adapter);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Log.v("Yourlist", ""+id);
                    Intent intent = new Intent(UpdateList.this, MovieInfo.class);
                    intent.putExtra("key1", id);
                    startActivityForResult(intent, 1);


            }
        });

    }

}
