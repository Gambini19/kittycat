package ru.artemdivin.kittycat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class CatActivity  extends AppCompatActivity {

    Intent intent;
    String titleId;
    DBHelper dbHelper;
    TextView catName, catBread, catDescription;
    Cat cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);

        catName = (TextView)findViewById(R.id.nameCat);
        catBread = (TextView) findViewById(R.id.breedCat);
        catDescription = (TextView) findViewById (R.id.description);


        intent = getIntent();
        titleId = intent.getStringExtra("titleId");

       // dbHelper = new DBHelper(this);

        Cursor cursor = App.dbHelper
                .getReadableDatabase()
                .rawQuery("SELECT * FROM mytable WHERE catname LIKE ? ", new String[] {titleId});

        Log.i("catID", String.valueOf(titleId));
        Log.i("getColumnCount()", String.valueOf(cursor.getColumnCount()));
//cursor.getColumnCount();

        if (cursor.moveToFirst())
        {
            cat = Cat.fromCursor(cursor);
            Log.i("catName", cat.catName );
            Log.i("catName", cat.catBreed );

            catName.setText(cat.catName);



        }
        Log.i("cursor.movetofirst", String.valueOf(cursor.moveToFirst()));
      catName.setText("testtt");






    }
}
