package com.example.prodromos.vrmkls_movies;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prodromos.vrmkls_movies.MovieContract.*;

import java.util.Date;

public class AddMovie extends AppCompatActivity {

    private DBHelper dbHelper;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
    }

    public void save(View view) {
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        String title = ((EditText) findViewById(R.id.title)).getText().toString();
        String releasedate = ((EditText) findViewById(R.id.releasedate)).getText().toString();
        String rating = ((EditText) findViewById(R.id.rating)).getText().toString();



        Log.v("opaaaa"," "+releasedate);

        if(title.isEmpty()) {
            Toast.makeText(this, this.getString(R.string.emptytitle), Toast.LENGTH_LONG).show();

        }else if(releasedate.isEmpty()){
            Toast.makeText(this, this.getString(R.string.emptydate) , Toast.LENGTH_LONG).show();

        }else if(rating.isEmpty()  || Integer.valueOf(rating)>10 || Integer.valueOf(rating)<0  ){
            Toast.makeText(this, this.getString(R.string.invalidrating) , Toast.LENGTH_LONG).show();

        }else{

            Log.v("sadadasd"," " + "asda");
            values.put(MovieEntry.COLUMN_NAME_TITLE, title);
            values.put(MovieEntry.COLUMN_NAME_RELEASE_DATE, Integer.valueOf(releasedate));
            values.put(MovieEntry.COLUMN_NAME_RATING, Integer.valueOf(rating));




            long newRowId = db.insert(
                    MovieEntry.TABLE_NAME,
                    null,
                    values);
            if(newRowId==-1){
                Toast.makeText(this, this.getString(R.string.failedinsert) , Toast.LENGTH_LONG).show();
            }else{
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);

                finish();
            }
        }
    }
}
