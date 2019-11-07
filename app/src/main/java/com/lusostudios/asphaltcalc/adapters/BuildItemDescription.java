package com.lusostudios.asphaltcalc.adapters;


import com.lusostudios.asphaltcalc.room_database.LineItem;

public class BuildItemDescription {


    public String getDescription(LineItem lineItem){

        String description = lineItem.getDescription1() + " x " + lineItem.getDescription2();

        if (lineItem._description3 != null && !lineItem._description3.isEmpty()) {
            description = description + " x " + lineItem._description3;
        }

        if (lineItem._description4 != null && !lineItem._description4.isEmpty()) {
            description = description + " x " + lineItem._description4;
        }
        if (lineItem._description5 != null && !lineItem._description5.isEmpty()) {
            description = description + " x " + lineItem._description5;
        }
        return description;
    }
}
