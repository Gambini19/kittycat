package ru.artemdivin.kittycat;

import android.app.Application;

/**
 * Created by Администратор on 09.02.2017.
 */

public class App extends Application {

    public static DBHelper dbHelper ;

    @Override
    public void onCreate() {
        super.onCreate();

        dbHelper = new DBHelper(this);

    }
}
