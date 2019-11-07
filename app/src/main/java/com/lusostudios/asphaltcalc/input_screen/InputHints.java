package com.lusostudios.asphaltcalc.input_screen;

import android.content.Context;
import android.content.res.Resources;

import com.lusostudios.asphaltcalc.R;

public class InputHints {

    static String TAG = "InputHints";

    public static String[] getHints(Context context, String type, String units) {

        String[] hints = null;
        Resources res = context.getResources();

        if ("US".equals(units)){
            if ("Slab - Rectangle".equals(type)) {
                hints = res.getStringArray(R.array.Slab_Rectangle_Hints);
            } else if ("Slab - Circle".equals(type)) {
                hints = res.getStringArray(R.array.Slab_Circle_Hints);
            } else if ("Slab - Half Circle".equals(type)) {
                hints = res.getStringArray(R.array.Slab_Circle_Hints);
            } else if ("Slab - Area".equals(type)) {
                hints = res.getStringArray(R.array.Slab_Area_Hints);
            } else if ("Curb".equals(type)) {
                hints = res.getStringArray(R.array.Curb_Hints);
            } else if ("Curb and Gutter".equals(type)) {
                hints = res.getStringArray(R.array.Curb_Gutter_Hints);
            } else if ("Wall".equals(type)) {
                hints = res.getStringArray(R.array.Wall_Hints);
            } else if ("Footing".equals(type)) {
                hints = res.getStringArray(R.array.Footing_Hints);
            } else if ("Column - Round".equals(type)) {
                hints = res.getStringArray(R.array.Column_Round_Hints);
            } else if ("Column - Square".equals(type)) {
                hints = res.getStringArray(R.array.Column_Square_Hints);
            }
        } else {
            if ("Slab - Rectangle".equals(type)) {
                hints = res.getStringArray(R.array.Slab_Rectangle_Hints_metric);
            } else if ("Slab - Circle".equals(type)) {
                hints = res.getStringArray(R.array.Slab_Circle_Hints_metric);
            } else if ("Slab - Half Circle".equals(type)) {
                hints = res.getStringArray(R.array.Slab_Circle_Hints_metric);
            } else if ("Slab - Area".equals(type)) {
                hints = res.getStringArray(R.array.Slab_Area_Hints_metric);
            } else if ("Curb".equals(type)) {
                hints = res.getStringArray(R.array.Curb_Hints_metric);
            } else if ("Curb and Gutter".equals(type)) {
                hints = res.getStringArray(R.array.Curb_Gutter_Hints_metric);
            } else if ("Wall".equals(type)) {
                hints = res.getStringArray(R.array.Wall_Hints_metric);
            } else if ("Footing".equals(type)) {
                hints = res.getStringArray(R.array.Footing_Hints_metric);
            } else if ("Column - Round".equals(type)) {
                hints = res.getStringArray(R.array.Column_Round_Hints_metric);
            } else if ("Column - Square".equals(type)) {
                hints = res.getStringArray(R.array.Column_Square_Hints_metric);
            }
        }
        return hints;
    }
}
