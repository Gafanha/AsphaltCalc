package com.lusostudios.asphaltcalc.preferences_and_other;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import java.text.DecimalFormat;

public class PreferenceDecimalFormat {

    String TAG = "PreferenceDecimalFormat";
    DecimalFormat df1 = new DecimalFormat("0.0");
    DecimalFormat df2 = new DecimalFormat("#,###,##0.00");
    DecimalFormat df3 = new DecimalFormat("#,###,##0.000");
    DecimalFormat df4 = new DecimalFormat("#,###,##0.000");

    public DecimalFormat getCurrent(Context activityContext) {
        DecimalFormat currentDF;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activityContext/* Activity context */);

        if ("2".equals(sharedPreferences.getString("decimalPlaces", "1"))) {
            currentDF = df2;
        } else if ("3".equals(sharedPreferences.getString("decimalPlaces", "1"))) {
            currentDF = df3;
        } else if ("4".equals(sharedPreferences.getString("decimalPlaces", "1"))) {
            currentDF = df4;
        } else {
            currentDF = df1;
        }
        Log.d(TAG, "getCurrent: Decimal Places " + sharedPreferences.getString("decimalPlaces", "1"));
        Log.d(TAG, "getCurrent: Decimal Format - " + currentDF.format(100.0000));
        return currentDF;
    }
}
