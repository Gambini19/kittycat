package ru.artemdivin.kittycat;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Администратор on 27.02.2017.
 */

public class Cat {

    int id;
    String catName;
    String catBreed;
    double coordinatesLat;
    double coordinatesLng;
    int    telephoneNumber;
    String photoLink;
    String description;

    public static ArrayList<Cat> catList;

    static SQLiteDatabase db;


    public Cat(int id, String catName, String catBreed, double coordinatesLat, double coordinatesLng, int telephoneNumber, String photoLink, String description) {
        this.id = id;
        this.catName = catName;
        this.catBreed = catBreed;
        this.coordinatesLat = coordinatesLat;
        this.coordinatesLng = coordinatesLng;
        this.telephoneNumber = telephoneNumber;
        this.photoLink = photoLink;
        this.description = description;
    }

    public Cat() {
    }

    public static void createCat()
    {
        catList = new ArrayList<>();
        catList.add(new Cat(1,"Murka", "Pers", 39.00, -77.00, 000000001, "photoLink", "small kitty1"));
        catList.add(new Cat(2,"Kitty", "scotish", 38.86, -77.153, 000000002, "photoLink", "small kitty2"));
        catList.add(new Cat(3,"Luis", "Russian", 38.83, -76.98, 000000003, "photoLink", "small kitty3"));
        catList.add(new Cat(4,"Tom", "Dvorovaya", 38.90, -76.88, 000000004, "photoLink", "small kitty4"));
        catList.add(new Cat(5,"Burt", "noname", 38.94, -77.043, 000000005, "photoLink", "small kitty5"));

       saveCatToDB(catList);

        Log.i("createCat", "Done");


    }

    private static void saveCatToDB(ArrayList<Cat> list) {

        list = new ArrayList<>();

        ContentValues cv = new ContentValues();
       db = App.dbHelper.getWritableDatabase();

        for (int i = 0; i < list.size() ; i++) {

            cv.put("id", list.get(i).id );
            cv.put("catname", list.get(i).catName );
            cv.put("catBread", list.get(i).catBreed);
            cv.put("lng", list.get(i).coordinatesLng);
            cv.put("ltd", list.get(i).coordinatesLat);
            cv.put("description", list.get(i).description);
            cv.put("photoURL", "testName");
            cv.put("telephoneNumber", list.get(i).telephoneNumber);

           db.insert("mytable", null, cv);


        }

        db.close();
    }

    public static Cat fromCursor(Cursor cursor)
    {
        Cat cat = new Cat(
                cursor.getInt(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("catName")),
                cursor.getString(cursor.getColumnIndex("catBread")),
                cursor.getDouble(cursor.getColumnIndex("lng")),
                cursor.getDouble(cursor.getColumnIndex("ltd")),
                cursor.getInt(cursor.getColumnIndex("telephoneNumber")),
                cursor.getString(cursor.getColumnIndex("photoURL")),
                cursor.getString(cursor.getColumnIndex("description"))
                        );

        return cat;
    }


}
