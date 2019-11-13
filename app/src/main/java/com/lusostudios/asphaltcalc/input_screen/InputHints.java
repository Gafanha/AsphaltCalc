package com.lusostudios.asphaltcalc.input_screen;

import android.content.Context;
import android.content.res.Resources;

import com.lusostudios.asphaltcalc.R;

public class InputHints {

    static String TAG = "InputHints";

    public static String[] getHints(Context context, String type, String units) {

        String[] hints = null;
        Resources res = context.getResources();

        if ("US".equals(units)) {
            if (res.getString(R.string.rectangle).equals(type)) {
                hints = res.getStringArray(R.array.Rectangle_Hints);

            } else if (res.getString(R.string.triangle).equals(type)) {
                hints = res.getStringArray(R.array.Triangle_Hints);

            } else if (res.getString(R.string.circle).equals(type)) {
                hints = res.getStringArray(R.array.Circle_Hints);

            } else if (res.getString(R.string.half_circle).equals(type)) {
                hints = res.getStringArray(R.array.Circle_Hints);

            } else if (res.getString(R.string.fillet).equals(type)) {
                hints = res.getStringArray(R.array.Fillet_Hints);

            } else if (res.getString(R.string.area).equals(type)) {
                hints = res.getStringArray(R.array.Area_Hints);

            }
        } else {
            if (res.getString(R.string.rectangle).equals(type)) {
                hints = res.getStringArray(R.array.Rectangle_Hints_metric);

            } else if (res.getString(R.string.triangle).equals(type)) {
                hints = res.getStringArray(R.array.Triangle_Hints_metric);

            } else if (res.getString(R.string.circle).equals(type)) {
                hints = res.getStringArray(R.array.Circle_Hints_metric);

            } else if (res.getString(R.string.half_circle).equals(type)) {
                hints = res.getStringArray(R.array.Circle_Hints_metric);

            } else if (res.getString(R.string.fillet).equals(type)) {
                hints = res.getStringArray(R.array.Fillet_Hints_metric);

            } else if (res.getString(R.string.area).equals(type)) {
                hints = res.getStringArray(R.array.Area_Hints_metric);

            }
        }
        return hints;
    }
}
