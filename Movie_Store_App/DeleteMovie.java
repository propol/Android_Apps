package com.example.prodromos.vrmkls_movies;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


import com.example.prodromos.vrmkls_movies.MovieContract.*;



public class DeleteMovie extends AppCompatActivity {

    private SimpleCursorAdapter adapter;
    SQLiteDatabase db;
    String[] projection;
     long ideeee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_movie);
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

                ideeee = id;
                Log.v("Yourlist", ""+id);

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    public boolean deleteTitle(String id)
                    {
                        return db.delete("movies", "_ID" + " = " + id, null) > 0;
                    }

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                deleteTitle(String.valueOf(ideeee));
                                Intent intent = new Intent(DeleteMovie.this,DeleteMovie.class);
                                startActivity(intent);
                                finish();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage(DeleteMovie.this.getString(R.string.deletemessage)).setPositiveButton(DeleteMovie.this.getString(R.string.yes), dialogClickListener)
                        .setNegativeButton(DeleteMovie.this.getString(R.string.no), dialogClickListener).show();


            }
        });

    }
}
