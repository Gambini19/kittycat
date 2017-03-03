package ru.artemdivin.kittycat;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by Администратор on 27.02.2017.
 */

public class Cat {

    String catName;
    String catBreed;
    double coordinatesLat;
    double coordinatesLng;
    String telephoneNumber;
    String photoLink;
    String description;



    public Cat( String catName, String catBreed, double coordinatesLat, double coordinatesLng, String telephoneNumber, String photoLink, String description) {
        this.catName = catName;
        this.catBreed = catBreed;
        this.coordinatesLat = coordinatesLat;
        this.coordinatesLng = coordinatesLng;
        this.telephoneNumber = telephoneNumber;
        this.photoLink = photoLink;
        this.description = description;
    }

      public static void createCat()
    {
        ArrayList<Cat> catList = new ArrayList<>();
        catList.add(new Cat("Murka", "Pers", 39.00, -77.00, "89655542233", "developer.alexanderklimov.ru/images/cat_bottom.png", "small kitty1"));
        catList.add(new Cat("Kitty", "scotish", 38.86, -77.153, "000000000", "http://www.fonstola.ru/mini/201510/209342.jpg", "small kitty2"));
        catList.add(new Cat("Luis", "Russian", 38.83, -76.98, "000000000", "http://www.setwalls.ru/min/201409/72348.jpg", "small kitty3"));
        catList.add(new Cat("Tom", "Dvorovaya", 38.90, -76.88, "000000000", "http://rabstol.ru/ts/animals/1438169907.jpg", "small kitty4"));
        catList.add(new Cat("Burt", "noname", 38.94, -77.043, "000000000", "http://newsmir.info/img/p/6/59/586/585099.jpg", "small kitty5"));

        Log.i("CATLIST", catList.toString());
       saveCatToDB(catList);

        Log.i("createCat", "Done");


    }

    private static void saveCatToDB(ArrayList<Cat> list) {
        SQLiteDatabase db;
        ContentValues cv = new ContentValues();
       db = App.dbHelper.getWritableDatabase();

        for (int i = 0; i < list.size() ; i++) {

            cv.put("catname", list.get(i).catName );
            cv.put("catBread", list.get(i).catBreed);
            cv.put("lng", list.get(i).coordinatesLng);
            cv.put("ltd", list.get(i).coordinatesLat);
            cv.put("description", list.get(i).description);
            cv.put("photoURL", list.get(i).photoLink);
            cv.put("telephoneNumber", list.get(i).telephoneNumber);

            Log.i("cv.put", cv.toString());
           db.insert("mytable", null, cv);


        }

        db.close();
    }

    public static Cat fromCursor(Cursor cursor)
    {
        Cat cat = new Cat(
                cursor.getString(cursor.getColumnIndex("catname")),
                cursor.getString(cursor.getColumnIndex("catBread")),
                cursor.getDouble(cursor.getColumnIndex("lng")),
                cursor.getDouble(cursor.getColumnIndex("ltd")),
                cursor.getString(cursor.getColumnIndex("telephoneNumber")),
                cursor.getString(cursor.getColumnIndex("photoURL")),
                cursor.getString(cursor.getColumnIndex("description"))
                        );

        return cat;
    }

    public static Boolean isFirstLaunchApp(Context context){
        boolean  isFirstStartApp;
        SharedPreferences sPref =  context.getSharedPreferences("",MODE_PRIVATE);
        isFirstStartApp = sPref.getBoolean("isFirstStartApp", true);


        if (isFirstStartApp){
            createCat();
            SharedPreferences.Editor editor = sPref.edit();
            editor.putBoolean("isFirstStartApp", false);
            editor.apply();
            editor.commit();
        }
        Log.i("isFirstStartApp", String.valueOf(isFirstStartApp));
    return isFirstStartApp;
    }


}
