package com.lusostudios.asphaltcalc.calculations;

import android.text.NoCopySpan;
import android.util.Log;

import java.util.List;

/**
 * Created by Sergio on 4/28/2017.
 */

public class Volume_Helper {

    public double calculate(String type, String units, boolean isNegative, List<Double> valuesArray) {

        String TAG = "Volume_Helper";
        double volume = 0;
        Calculations calculation = new Calculations();

        // Volume_Helper Volume and set descriptions array based on type.
        switch (type) {
            case "Rectangle":
                volume = calculation.Rectangle(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), valuesArray.get(3), isNegative);
                break;
            case "Circle":
                volume = new ConcreteVolume().SlabCircle(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), isNegative);
                break;
            case "Half Circle":
                volume = (new ConcreteVolume().SlabCircle(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), isNegative)) / 2;
                break;
            case "Area":
                for (int i = 0; i < valuesArray.size(); i++) {
                    Log.d(TAG, "onClick: value array - " + valuesArray.get(i));
                }
                volume = new ConcreteVolume().SlabArea(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), isNegative);
                break;
            case "Triangle":
                volume = new ConcreteVolume().Curb(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), valuesArray.get(3), isNegative);
                break;
            case "Fillet":
                volume = new ConcreteVolume().Curb_and_Gutter(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), valuesArray.get(3),
                        valuesArray.get(4), valuesArray.get(5), isNegative);
                break;
        }
        return volume;
    }
}
