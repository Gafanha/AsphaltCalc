package com.lusostudios.asphaltcalc.input_screen;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import ConCalc.Activity.R;

public class InputImages {

    String TAG = "InputHints";

    public static Drawable getDrawable(Context context, String type) {

        Drawable drawable;

        if ("Slab - Rectangle".equals(type)) {
            drawable = ContextCompat.getDrawable(context, R.drawable.slab_square_svg);
        } else if ("Slab - Circle".equals(type)) {
            drawable = ContextCompat.getDrawable(context, R.drawable.slab_round_svg);
        } else if ("Slab - Half Circle".equals(type)) {
            drawable = ContextCompat.getDrawable(context, R.drawable.slab_round_svg);
        } else if ("Slab - Area".equals(type)) {
            drawable = ContextCompat.getDrawable(context, R.drawable.slab_square_svg);
        } else if ("Curb".equals(type)) {
            drawable = ContextCompat.getDrawable(context, R.drawable.curb_svg);
        } else if ("Curb and Gutter".equals(type)) {
            drawable = ContextCompat.getDrawable(context, R.drawable.curb_gutter_svg);
        } else if ("Wall".equals(type)) {
            drawable = ContextCompat.getDrawable(context, R.drawable.wall_svg);
        } else if ("Footing".equals(type)) {
            drawable = ContextCompat.getDrawable(context, R.drawable.footing_svg);
        } else if ("Column - Round".equals(type)) {
            drawable = ContextCompat.getDrawable(context, R.drawable.column_round_svg);
        }else if ("Column - Square".equals(type)) {
            drawable = ContextCompat.getDrawable(context, R.drawable.column_square_svg);
        }else {
            drawable = null;
        }
        return drawable;
    }
}
