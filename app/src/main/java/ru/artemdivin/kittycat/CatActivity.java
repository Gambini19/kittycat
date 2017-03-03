package ru.artemdivin.kittycat;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CatActivity extends AppCompatActivity {

    Intent intent;
    String titleId;
    TextView catName, catBread, catDescription;
    ImageView imageView;
    Button callPhoneButton;
    Cat cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);

        catName = (TextView) findViewById(R.id.nameCat);
        catBread = (TextView) findViewById(R.id.breedCat);
        catDescription = (TextView) findViewById(R.id.description);
        imageView = (ImageView) findViewById(R.id.imageView);
        callPhoneButton = (Button) findViewById(R.id.callPhoneButton);

        intent = getIntent();
        titleId = intent.getStringExtra("titleId");

        Cat.loadCatData();

        Cursor cursor = App.dbHelper
                .getReadableDatabase()
                .rawQuery("SELECT * FROM mytable WHERE catname LIKE ? ", new String[]{titleId});

        Log.i("catID", String.valueOf(titleId));
        Log.i("getColumnCount()", String.valueOf(cursor.getColumnCount()));

        if (cursor.moveToFirst()) {
            cat = Cat.fromCursor(cursor);
            catName.setText("Name:  "+cat.catName);
            catBread.setText("Breed:  "+cat.catBreed);
            catDescription.setText("Description:  "+cat.description);


            Picasso.with(this)
                    .load(cat.photoLink)
                    .placeholder(R.drawable.common_full_open_on_phone)
                    .error(R.drawable.common_full_open_on_phone)
                    .into(imageView);

            callPhoneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + cat.telephoneNumber));
                    startActivity(intent);
                }
            });
        }
        cursor.close();



    }
}
