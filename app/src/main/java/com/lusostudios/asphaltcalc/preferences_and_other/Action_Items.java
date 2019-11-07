package com.lusostudios.asphaltcalc.preferences_and_other;

import java.text.DecimalFormat;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.lusostudios.asphaltcalc.room_database.LineItem;

public class Action_Items {

    static String TAG = "Action_Items";

    // ************ Code for ActionItems ******************
    // Clears options_menu screen
    public static void ClearList(Activity activity, Context context, List<LineItem> itemList) {

        // Clear ListArray and clear list view
        itemList.clear();

        // Clear Total
        //MainActivity.runningTons = 0;
        Toast.makeText(context, "Cleared", Toast.LENGTH_SHORT).show();
    }

    // Shares data with other applications
    public static void Share(Context context, List<LineItem> itemList, String units) {

        double totalVolume = 0;

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);

        // Build text to share
        DecimalFormat formatVolume;

        // Set decimal formats based on units.
        if ("US".equals(units)) {
            formatVolume = new DecimalFormat("#,###.# cubic yards");
        } else {
            formatVolume = new DecimalFormat("#,###.# cubic meters");
        }

        String messageItems = "";
        String description5;
        String description4;
        // Build string to share.
        for (int i = 0; i < itemList.size(); i++) {
            String title = itemList.get(i).getTitle();
            // TODO fix this part
            String volume = formatVolume.format(itemList.get(i).getVolume()) + "\n\r";
            String description1 = itemList.get(i).getDescription1()
                    + "\n\r";
            String description2 = itemList.get(i).getDescription2()
                    + "\n\r";
            String description3 = itemList.get(i).getDescription3()
                    + "\n\r";
            if (itemList.get(i).getDescription4() != null) {
                description4 = itemList.get(i).getDescription4() + "\n\r";
            } else {
                description4 = "";
            }
            if (itemList.get(i).getDescription5() != null) {
                description5 = itemList.get(i).getDescription5() + "\n\r";
            } else {
                description5 = "";
            }


            messageItems = messageItems + "\n\r" + title + ": "
                    + volume + description1 + description2
                    + description3 + description4 + description5;

            //totalVolume = totalVolume + Double.parseDouble(volume);
            Log.d(TAG, "Share: " + itemList);
        }

        // Add data to the intent, the receiving app will decide what to do with
        // it.
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Concrete Calculations");
        sendIntent.putExtra(Intent.EXTRA_TEXT, messageItems
                + "\n\r" + "Total Volume - "
                + totalVolume
                + "\n\r" + "\n\r" + "\n\r"
                + "ConCalc - Concrete Calculator for Android" + "\n\r");

        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        context.startActivity(shareIntent);

    }

    // Get more apps by luso studios
    public static void GetApps(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setData(Uri.parse(
                "https://play.google.com/store/apps/developer?id=Luso+Studios"));
        //intent.setPackage("com.android.vending");
        context.startActivity(intent);
    }
}
