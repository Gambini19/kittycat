package ru.artemdivin.kittycat;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Администратор on 27.02.2017.
 */

public class DBHelper extends SQLiteOpenHelper {


    private static final int Version = 1;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DBHelper(Context context) {
        super(context, "myDB", null, Version);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("myDB", "Created");
        db.execSQL("create table mytable ("
                + "id integer primary key autoincrement,"
                + "catname text,"
                + "catBread text,"
                + "lng real,"
                + "ltd real,"
                + "description text,"
                + "photoURL text,"
                + "telephoneNumber text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

 }
