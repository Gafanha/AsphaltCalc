package com.lusostudios.asphaltcalc.room_database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = LineItem.class, version = 5, exportSchema = false)
public abstract class LineItemDatabase extends RoomDatabase {

    private static LineItemDatabase instance;
    public abstract LineItemDAO lineItemDAO();

    public static synchronized LineItemDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LineItemDatabase.class, "line_item_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
