package com.lusostudios.asphaltcalc.input_screen;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class EditTextValidation {

    public boolean isValidated(EditText editText) {
        // Check if empty or equals a period.
        if (!editText.getText().toString().isEmpty() && !".".equals(editText.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }
}
