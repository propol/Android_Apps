package com.example.prodromos.vrmkls_movies;

import android.content.Intent;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


import com.example.prodromos.vrmkls_movies.MovieContract.*;



public class YourList extends AppCompatActivity {

    private SimpleCursorAdapter adapter;
    SQLiteDatabase db;
    String[] projection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_list);
        ListView lv = (ListView) findViewById(R.id.listView);
        ListView lv1 = (ListView) findViewById(R.id.listView1);


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
                "rating DESC",
                "3"
        );

        String[] fromColumns = {MovieEntry.COLUMN_NAME_TITLE};
        int[] toViews = {R.id.item};
        adapter = new SimpleCursorAdapter(this,
                R.layout.list_items, c, fromColumns, toViews, 0);

        lv.setAdapter(adapter);

        Cursor c1 = db.rawQuery("select * from (select * from movies order by rating ASC LIMIT 3 )  order by rating DESC ", new String[]{} );

        String[] fromColumns1 = {MovieEntry.COLUMN_NAME_TITLE};
        int[] toViews1 = {R.id.item};
        adapter = new SimpleCursorAdapter(this,
                R.layout.list_items, c1, fromColumns1, toViews1, 0);

        lv1.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Log.v("Yourlist", ""+id);
                    Intent intent = new Intent(YourList.this, UneditableList.class);
                    intent.putExtra("key1", id);
                    startActivityForResult(intent, 1);


            }
        });

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Log.v("Yourlist", ""+id);
                    Intent intent = new Intent(YourList.this, UneditableList.class);
                    intent.putExtra("key1", id);
                    startActivityForResult(intent, 1);


            }
        });


    }
}
