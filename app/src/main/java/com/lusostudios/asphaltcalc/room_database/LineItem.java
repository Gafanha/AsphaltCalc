package com.lusostudios.asphaltcalc.room_database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "line_item_table")
public class LineItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int _id;
    @ColumnInfo(name = "title") // Title also used to determine type.
    public String _title;
    @ColumnInfo(name = "description1")
    public String _description1;
    @ColumnInfo(name = "description2")
    public String _description2;
    @ColumnInfo(name = "description3")
    public String _description3;
    @ColumnInfo(name = "description4")
    public String _description4;
    @ColumnInfo(name = "description5")
    public String _description5;
    @ColumnInfo(name = "waste")
    public double _waste;
    @ColumnInfo(name = "volume")
    public double _volume;
    @ColumnInfo(name = "units")
    public String _units;
    @ColumnInfo(name = "negative")
    public boolean negative;



    // Empty constructor
    @Ignore
    public LineItem() {
    }

    // constructor
    public LineItem(String title, String description1,
                    String description2, String description3, String description4,
                    String description5, double waste, double volume, String units, boolean negative) {

        this._title = title;
        this._description1 = description1;
        this._description2 = description2;
        this._description3 = description3;
        this._description4 = description4;
        this._description5 = description5;
        this._waste = waste;
        this._volume = volume;
        this._units = units;
        this.negative = negative;
    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int id) {
        this._id = id;
    }

    // getting title
    public String getTitle() {
        return this._title;
    }

    // setting title
    public void setTitle(String title) {
        this._title = title;
    }

    // getting description1
    public String getDescription1() {
        return this._description1;
    }

    // setting description1
    public void setDescription1(String description1) {
        this._description1 = description1;
    }

    // getting description2
    public String getDescription2() {
        return this._description2;
    }

    // setting description2
    public void setDescription2(String description2) {
        this._description2 = description2;
    }

    // getting description3
    public String getDescription3() {
        return this._description3;
    }

    // setting description3
    public void setDescription3(String description3) {
        this._description3 = description3;
    }

    // getting description4
    public String getDescription4() {
        return this._description4;
    }

    // setting description4
    public void setDescription4(String description4) {
        this._description4 = description4;
    }

    // getting description5
    public String getDescription5() {
        return this._description5;
    }

    // setting description5
    public void setDescription5(String description5) {
        this._description5 = description5;
    }

    public double get_waste() {
        return _waste;
    }

    public void set_waste(double _waste) {
        this._waste = _waste;
    }

    // getting tons
    public double getVolume() {
        return this._volume;
    }

    // setting tons
    public void setVolume(double volume) {
        this._volume = volume;
    }

    public String get_units() {
        return _units;
    }

    public void set_units(String _units) {
        this._units = _units;
    }

    public boolean isNegative() {
        return negative;
    }

    public void setNegative(boolean negative) {
        this.negative = negative;
    }
}
