package com.lusostudios.asphaltcalc.calculations;

import android.util.Log;

import java.util.List;

/**
 * Created by Sergio on 4/28/2017.
 */

public class Volume_Helper {

    public double calculate(String type, String units, boolean isNegative, List<Double> valuesArray) {

        String TAG = "Volume_Helper";
        double volume = 0;

        // Volume_Helper Volume and set descriptions array based on type.
        switch (type) {
            case "Slab - Rectangle":
                volume = new ConcreteVolume().SlabRectangle(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), valuesArray.get(3), isNegative);
                break;
            case "Slab - Circle":
                volume = new ConcreteVolume().SlabCircle(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), isNegative);
                break;
            case "Slab - Half Circle":
                volume = (new ConcreteVolume().SlabCircle(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), isNegative)) / 2;
                break;
            case "Slab - Area":
                for (int i = 0; i < valuesArray.size(); i++) {
                    Log.d(TAG, "onClick: value array - " + valuesArray.get(i));
                }
                volume = new ConcreteVolume().SlabArea(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), isNegative);
                break;
            case "Curb":
                volume = new ConcreteVolume().Curb(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), valuesArray.get(3), isNegative);
                break;
            case "Curb and Gutter":
                volume = new ConcreteVolume().Curb_and_Gutter(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), valuesArray.get(3),
                        valuesArray.get(4), valuesArray.get(5), isNegative);
                break;
            case "Wall":
                volume = new ConcreteVolume().Wall(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), valuesArray.get(3), isNegative);
                break;
            case "Footing":
                volume = new ConcreteVolume().Footing(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), valuesArray.get(3), isNegative);
                break;
            case "Column - Round":
                volume = new ConcreteVolume().Column_Round(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), isNegative);
                break;
            case "Column - Square":
                volume = new ConcreteVolume().Column_Square(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), valuesArray.get(3), isNegative);
                break;
        }
        return volume;
    }
}
