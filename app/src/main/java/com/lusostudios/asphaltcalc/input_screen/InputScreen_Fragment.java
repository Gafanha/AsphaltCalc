package com.lusostudios.asphaltcalc.input_screen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.lusostudios.asphaltcalc.R;
import com.lusostudios.asphaltcalc.calculations.Volume_Helper;
import com.lusostudios.asphaltcalc.room_database.LineItem;
import com.lusostudios.asphaltcalc.room_database.LineItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class InputScreen_Fragment extends Fragment {

    String TAG = "InputScreen";
    LineItem lineItem;
    ImageView imageView;
    TextView textViewTitle;
    TextInputLayout inputLayout1, inputLayout2, inputLayout3, inputLayout4, inputLayout5, inputLayoutWaste;
    TextInputEditText editText1, editText2, editText3, editText4, editText5, editTextWaste;
    Switch negativeSwitch;
    Button buttonDone;
    String units;
    String id;
    String title;
    List<TextInputLayout> textInputLayoutArray = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.input_fragment, container, false);
        // References to various views in layout.
        imageView = rootView.findViewById(R.id.ivStructureType);
        textViewTitle = rootView.findViewById(R.id.textViewTitle);
        inputLayout1 = rootView.findViewById(R.id.textInputLayout1);
        inputLayout2 = rootView.findViewById(R.id.textInputLayout2);
        inputLayout3 = rootView.findViewById(R.id.textInputLayout3);
        inputLayout4 = rootView.findViewById(R.id.textInputLayout4);
        inputLayout5 = rootView.findViewById(R.id.textInputLayout5);
        inputLayoutWaste = rootView.findViewById(R.id.textInputLayoutWaste);
        editText1 = rootView.findViewById(R.id.editText1);
        //editText1.requestFocus(); // Get focus for the first EditText.
        editText2 = rootView.findViewById(R.id.editText2);
        editText3 = rootView.findViewById(R.id.editText3);
        editText4 = rootView.findViewById(R.id.editText4);
        editText5 = rootView.findViewById(R.id.editText5);
        editTextWaste = rootView.findViewById(R.id.editTextWaste);
        negativeSwitch = rootView.findViewById(R.id.negativeOrPositiveSwitch);
        buttonDone = rootView.findViewById(R.id.buttonDone);

        id = InputScreen_FragmentArgs.fromBundle(getArguments()).getId();
        textInputLayoutArray.add(inputLayout1);
        textInputLayoutArray.add(inputLayout2);
        textInputLayoutArray.add(inputLayout3);
        textInputLayoutArray.add(inputLayout4);
        textInputLayoutArray.add(inputLayout5);

        // Display AdMob add
        // Adds view
        AdView mAdView = rootView.findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Get units from preferences.
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext() /* Activity context */);
        units = sharedPreferences.getString("units_system", "US");


        // If editing item get from database and then display.
        if (id != null && !id.isEmpty() && !id.equals("null")) {
            LineItemViewModel lineItemViewModel;
            lineItemViewModel = ViewModelProviders.of(this).get(LineItemViewModel.class);
            lineItemViewModel.getItemByID(Integer.parseInt(id)).observe(getActivity(), new Observer<List<LineItem>>() {
                @Override
                public void onChanged(@Nullable List<LineItem> lineItems) {
                    lineItem = lineItems.get(0);
                    title = lineItems.get(0).getTitle();
                    setUpHints();
                    editText1.setText(lineItems.get(0).getDescription1());
                    editText2.setText(lineItems.get(0).getDescription2());
                    if (inputLayout3.VISIBLE == 0) {
                        editText3.setText(lineItems.get(0).getDescription3());
                    }

                    if (inputLayout4.VISIBLE == 0) {
                        editText4.setText(lineItems.get(0).getDescription4());
                    }

                    if (inputLayout5.VISIBLE == 0) {
                        editText5.setText(lineItems.get(0).getDescription5());
                    }
                    editTextWaste.setText(lineItems.get(0).get_waste() + "");

                    Log.d(TAG, "onChanged: isNegative - " + lineItems.get(0).isNegative());
                    negativeSwitch.setChecked(lineItems.get(0).isNegative());
                }
            });
        } else {
            // Or else create new line item.
            lineItem = new LineItem();
            title = InputScreen_FragmentArgs.fromBundle(getArguments()).getTitle();
            setUpHints();
        }

        // Handle done button click.
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Validate edit texts and get values.
                List<Double> valuesArray = new ArrayList<>();
                EditTextValidation editTextValidation = new EditTextValidation();
                if (editTextValidation.isValidated(editText1)) {
                    valuesArray.add(Double.parseDouble(editText1.getText().toString()));
                } else {
                    Toast.makeText(buttonDone.getContext(), "Please enter " + inputLayout1.getHint(), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (editTextValidation.isValidated(editText2)) {
                    valuesArray.add(Double.parseDouble(editText2.getText().toString()));
                } else {
                    Toast.makeText(buttonDone.getContext(), "validated Please enter " + inputLayout2.getHint(), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (inputLayout3.getVisibility() == View.VISIBLE) {
                    if (editTextValidation.isValidated(editText3)) {
                        valuesArray.add(Double.parseDouble(editText3.getText().toString()));
                    } else {
                        Toast.makeText(buttonDone.getContext(), "Three NOT validated Please enter " + inputLayout3.getHint(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if (inputLayout4.getVisibility() == View.VISIBLE) {
                    if (editTextValidation.isValidated(editText4)) {
                        valuesArray.add(Double.parseDouble(editText4.getText().toString()));
                    } else {
                        Toast.makeText(buttonDone.getContext(), "Four NOT validated Please enter " + inputLayout4.getHint(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if (inputLayout5.getVisibility() == View.VISIBLE) {
                    if (editTextValidation.isValidated(editText5)) {
                        valuesArray.add(Double.parseDouble(editText5.getText().toString()));
                    } else {
                        Toast.makeText(buttonDone.getContext(), "Please enter " + inputLayout5.getHint(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if (inputLayoutWaste.getVisibility() == View.VISIBLE) {
                    if (editTextValidation.isValidated(editTextWaste)) {
                        valuesArray.add(Double.parseDouble(editTextWaste.getText().toString()));
                    } else {
                        Toast.makeText(buttonDone.getContext(), "Please enter " + inputLayoutWaste.getHint(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                // Calculate volume.
                Volume_Helper calcHelper = new Volume_Helper();
                Double volume = calcHelper.calculate(title, units, negativeSwitch.isChecked(),valuesArray);

                // Set the various values for the line item.
                lineItem.setTitle(textViewTitle.getText().toString());
                lineItem.setDescription1(editText1.getText().toString());
                lineItem.setDescription2(editText2.getText().toString());
                lineItem.setDescription3(editText3.getText().toString());
                lineItem.setDescription4(editText4.getText().toString());
                lineItem.setDescription5(editText5.getText().toString());
                lineItem.set_waste(Double.parseDouble(editTextWaste.getText().toString()));
                lineItem.setVolume(volume);
                lineItem.set_units(units);
                // TODO change to read actual switch. Not set to true for testing purposes.
                lineItem.setNegative(negativeSwitch.isChecked());

                if (negativeSwitch.isChecked()){
                    Log.d(TAG, "onClick: negativeSwitch is Checked");
                } else {
                    Log.d(TAG, "onClick: negativeSwitch NOT Checked");
                }

                LineItemViewModel lineItemViewModel = new LineItemViewModel(getActivity().getApplication());

                // Either update existing line item or insert new.
                if (id != null && !id.isEmpty() && !id.equals("null")) {
                    lineItemViewModel.update(lineItem);
                } else {
                    lineItemViewModel.insert(lineItem);
                }

                // Navigate back to main fragment.
                Navigation.findNavController(view).navigate(R.id.action_inputScreen_Fragment_to_fragment_Main);
            }
        });
        return rootView;
    }

    void setUpHints() {
        textViewTitle.setText(title);
        imageView.setImageDrawable(InputImages.getDrawable(imageView.getContext(), title));
        String[] hints = InputHints.getHints(imageView.getContext(), title, units);
        // Set up input screen hints
        Input_Screen_Setup setUpInput = new Input_Screen_Setup();
        setUpInput.generalScreenSetUp(textInputLayoutArray, hints);
    }
}