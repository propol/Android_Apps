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

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);

    }

    public void add(View view){
        Intent intent = new Intent(this, AddMovie.class);
        startActivityForResult(intent, 1);
    }

    public void showList(View view){
        Intent intent = new Intent(this, YourList.class);
        startActivityForResult(intent, 1);
    }

    public void UpdateList(View view){
        Intent intent = new Intent(this, UpdateList.class);
        startActivityForResult(intent, 1);
    }

    public void Exit(View view){
        finish();
        System.exit(0);
    }


    public void DeleteMovie(View view){
        Intent intent = new Intent(this, DeleteMovie.class);
        startActivityForResult(intent, 1);
    }


}
