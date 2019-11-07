package com.lusostudios.asphaltcalc.calculations;

public class ConcreteVolume {

    public double SlabRectangle(String units, double length, double width, double thickness, double waste, boolean isNegative) {
        double volume = 0;
        if ("US".equals(units)) {
            volume = (((length * width) * (thickness / 12.0d)) / 27.0d) * ((waste / 100.0d) + 1.0d);
        } else if ("Metric".equals(units)) {
            volume = ((length * width) * (thickness / 100.0d)) * ((waste / 100.0d) + 1.0d);
        }
        if (isNegative) {
            volume = volume * -1;
        }
        return volume;
    }

    public double SlabCircle(String units, double radius, double thickness, double waste, boolean isNegative) {
        double volume = 0;

        if ("US".equals(units)) {
            volume = (((3.141592653589793d * Math.pow(radius, 2.0d)) * (thickness / 12.0d)) / 27.0d) * ((waste / 100.0d) + 1.0d);
        } else if ("Metric".equals(units)) {
            volume = ((3.141592653589793d * Math.pow(radius, 2.0d)) * (thickness / 100.0d)) * ((waste / 100.0d) + 1.0d);
        }
        if (isNegative) {
            volume = volume * -1;
        }
        return volume;
    }

    public double SlabArea(String units, double area, double thickness, double waste, boolean isNegative) {
        double volume = 0;
        if ("US".equals(units)) {
            volume = (((thickness / 12.0d) * area) / 27.0d) * ((waste / 100.0d) + 1.0d);
        } else if ("Metric".equals(units)) {
            volume = ((thickness / 100.0d) * area) * ((waste / 100.0d) + 1.0d);
        }
        if (isNegative) {
            volume = volume * -1;
        }
        return volume;
    }

    public double Curb(String units, double height, double thickness, double length, double waste, boolean isNegative) {
        double volume = 0;
        if ("US".equals(units)) {
            volume = ((((height / 12.0d) * (thickness / 12.0d)) * length) / 27.0d) * ((waste / 100.0d) + 1.0d);
        } else if ("Metric".equals(units)) {
            volume = (((height / 100.0d) * (thickness / 100.0d)) * length) * ((waste / 100.0d) + 1.0d);
        }
        if (isNegative) {
            volume = volume * -1;
        }
        return volume;
    }

    public double Curb_and_Gutter(String units, double height, double thickness, double gutterHeight, double gutterWidth, double length, double waste, boolean isNegative) {
        double volume = 0;
        if ("US".equals(units)) {
            volume = (((((height / 12.0d) * (thickness / 12.0d)) + ((gutterHeight / 12.0d) * (gutterWidth / 12.0d))) * length) / 27.0d) * ((waste / 100.0d) + 1.0d);
        } else if ("Metric".equals(units)) {
            volume = ((((height / 100.0d) * (thickness / 100.0d)) + ((gutterHeight / 100.0d) * (gutterWidth / 100.0d))) * length) * ((waste / 100.0d) + 1.0d);
        }
        if (isNegative) {
            volume = volume * -1;
        }
        return volume;
    }

    public double Wall(String units, double height, double thickness, double length, double waste, boolean isNegative) {
        double volume = 0;
        if ("US".equals(units)) {
            volume = (((length * height) * (thickness / 12.0d)) / 27.0d) * ((waste / 100.0d) + 1.0d);
        } else if ("Metric".equals(units)) {
            volume = ((length * height) * (thickness / 100.0d)) * ((waste / 100.0d) + 1.0d);
        }
        if (isNegative) {
            volume = volume * -1;
        }
        return volume;
    }

    public double Footing(String units, double height, double width, double length, double waste, boolean isNegative) {
        double volume = 0;
        if ("US".equals(units)) {
            volume = ((((height / 12.0d) * (width / 12.0d)) * length) / 27.0d) * ((waste / 100.0d) + 1.0d);
        } else if ("Metric".equals(units)) {
            volume = (((height / 12.0d) * (width / 12.0d)) * length) * ((waste / 100.0d) + 1.0d);
        }
        if (isNegative) {
            volume = volume * -1;
        }
        return volume;
    }

    public double Column_Round(String units, double radius, double height, double waste, boolean isNegative) {
        double volume = 0;
        if ("US".equals(units)) {
            volume = (((3.141592653589793d * Math.pow(radius / 12.0d, 2.0d)) * height) / 27.0d) * ((waste / 100.0d) + 1.0d);
        } else if ("Metric".equals(units)) {
            volume = ((3.141592653589793d * Math.pow(radius / 100.0d, 2.0d)) * height) * ((waste / 100.0d) + 1.0d);
        }
        if (isNegative) {
            volume = volume * -1;
        }
        return volume;
    }

    public double Column_Square(String units, double length, double width, double height, double waste, boolean isNegative) {
        double volume = 0;
        if ("US".equals(units)) {
            volume = ((((length / 12.0d) * (width / 12.0d)) * height) / 27.0d) * ((waste / 100.0d) + 1.0d);
        } else if ("Metric".equals(units)) {
            volume = (((length / 100.0d) * (width / 100.0d)) * height) * ((waste / 100.0d) + 1.0d);
        }
        if (isNegative) {
            volume = volume * -1;
        }
        return volume;
    }
}
