package com.lusostudios.asphaltcalc.input_screen;

import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class Input_Screen_Setup {

    String TAG = "Input_Screen_Setup";

    public void generalScreenSetUp(List<TextInputLayout> inputLayoutArray, String[] hintsArray) {

        // Set hints for EditTextLayouts
        for (int i = 0; i < hintsArray.length; i++) {
            inputLayoutArray.get(i).setHint(hintsArray[i]);
        }

        // Hide Extra Edit Texts Layouts
        for (int i = hintsArray.length; i < inputLayoutArray.size(); i++) {
           inputLayoutArray.get(i).setVisibility(View.GONE);
        }
    }
}
