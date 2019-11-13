package com.lusostudios.asphaltcalc.calculations;

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
                volume = calculation.Circle(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), isNegative);
                break;
            case "Half Circle":
                volume = (calculation.Circle(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), isNegative)) / 2;
                break;
            case "Area":
                volume = calculation.Area(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), isNegative);
                break;
            case "Triangle":
                volume = calculation.Triangle(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), valuesArray.get(3), isNegative);
                break;
            case "Fillet":
                volume = calculation.Fillet(units, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), isNegative);
                break;
        }
        return volume;
    }
}
