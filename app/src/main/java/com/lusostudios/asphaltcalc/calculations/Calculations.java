package com.lusostudios.asphaltcalc.calculations;

import android.app.Activity;

public class Calculations {

    public double cubicFeet, cubicMeters, tons;

    // TODO still have to check metric math.

    public double Area(String units, double area, double thickness, double density) {

        if ("US".equals(units)) {
            cubicFeet = area * (thickness / 12);
            tons = (cubicFeet * density) / 2000;
        } else if ("Metric".equals(units)) {
            cubicMeters = area * (thickness / 100);
            tons = cubicMeters * (density / 1000);
        }
        return tons;
    }

    public double Circle(String units, double radius,
                         double thickness, double density) {

        if ("US".equals(units)) {
            cubicFeet = Math.PI * Math.pow(radius, 2) * (thickness / 12);
            tons = (cubicFeet * density) / 2000;
        } else if ("Metric".equals(units)) {
            cubicMeters = (Math.PI * Math.pow(radius, 2) * (thickness / 100));
            tons = cubicMeters * (density / 1000);
        }

        return tons;
    }

    public double Fillet(String units, double length,
                         double thickness, double density) {
        // length - feet/meters. width - feet/meters. thickness -
        // inches/centimeters

        if ("US".equals(units)) {
            cubicFeet = (.215 * length) * (thickness / 12);
            tons = (cubicFeet * density) / 2000;
        } else if ("Metric".equals(units)) {
            cubicMeters = (.215 * length) * (thickness / 100);
            tons = cubicMeters * (density / 1000);
        }
        return tons;
    }

    public double Rectangle(String units, double length, double width, double thickness, double density, boolean isNegative) {

        if ("US".equals(units)) {
            cubicFeet = length * width * (thickness / 12);
            tons = (cubicFeet * density) / 2000;
        } else if ("Metric".equals(units)) {
            cubicMeters = length * width * (thickness / 100);
            tons = cubicMeters * (density / 1000);
        }

        if (isNegative) {
            tons = tons * -1;
        }

        return tons;
    }

    public double Triangle(String units, double height,
                           double width, double thickness, double density) {

        if ("US".equals(units)) {
            cubicFeet = ((height * width) / 2) * (thickness / 12);
            tons = (cubicFeet * density) / 2000;
        } else if ("Metric".equals(units)) {
            cubicMeters = ((height * width) / 2) * (thickness / 100);
            tons = cubicMeters * (density / 1000);
        }
        return tons;
    }
}
