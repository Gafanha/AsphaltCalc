package com.lusostudios.asphaltcalc.room_database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LineItemDAO {

    @Insert
    void insert(LineItem lineItem);

    @Update
    void update(LineItem lineItem);

    @Delete
    void delete(LineItem lineItem);

    @Query("DELETE FROM line_item_table")
    void deleteAll();

    @Query("SELECT * FROM line_item_table")
    LiveData<List<LineItem>> getAllLive();

    @Query("SELECT * FROM line_item_table")
    List<LineItem> getAllItemList();

    @Query("SELECT * FROM line_item_table WHERE id = :itemID")
    LiveData<List<LineItem>> getItemByID(int itemID);

}
