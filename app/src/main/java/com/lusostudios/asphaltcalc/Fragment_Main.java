package com.lusostudios.asphaltcalc;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.lusostudios.asphaltcalc.adapters.ItemViewAdapter;
import com.lusostudios.asphaltcalc.calculations.ConcreteBags;
import com.lusostudios.asphaltcalc.preferences_and_other.PreferenceDecimalFormat;
import com.lusostudios.asphaltcalc.room_database.LineItem;
import com.lusostudios.asphaltcalc.room_database.LineItemViewModel;

import ConCalc.Activity.Fragment_MainDirections;

public class Fragment_Main extends Fragment {

    private String TAG = "Fragment_Main";
    private View rootView;
    private TextView textRunningTotal, tvUnits;
    private DecimalFormat currentDecimalFormat;
    private String units, waste;
    private LineItemViewModel lineItemViewModel;
    private SharedPreferences sharedPreferences;
    private View viewSummary;

    public Fragment_Main() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.main_fragment2, container, false);
        textRunningTotal = rootView.findViewById(R.id.textTotalVolume);
        tvUnits = rootView.findViewById(R.id.tvUnits);

        // Adds view
        AdView mAdView = rootView.findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Summary view that shows number of bags needed for the shown volume.
        viewSummary = rootView.findViewById(R.id.include_additional_info);
        viewSummary.setVisibility(View.GONE);
        // Summary individual views
        final TextView textViewBagsOne = viewSummary.findViewById(R.id.tvBagsOne);
        final TextView textViewBagsTwo = viewSummary.findViewById(R.id.tvBagsTwo);

        // Get SharedPreferences instance. To be used later to get Units and Waste preferences.
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext()/* Activity context */);
        // Get decimal format from preferences using Helper class written by me.
        PreferenceDecimalFormat preferenceDecimalFormat = new PreferenceDecimalFormat();
        currentDecimalFormat = preferenceDecimalFormat.getCurrent(getContext());

        // Recycler view.
        RecyclerView rvLineItems = rootView.findViewById(R.id.recycler_view);
        rvLineItems.setLayoutManager(new LinearLayoutManager(getContext()));
        rvLineItems.setHasFixedSize(true);
        final ItemViewAdapter adapter = new ItemViewAdapter();
        rvLineItems.setAdapter(adapter);
        lineItemViewModel = ViewModelProviders.of(this).get(LineItemViewModel.class);
        lineItemViewModel.getAllLive().observe(getViewLifecycleOwner(), new Observer<List<LineItem>>() {
            @Override
            public void onChanged(@Nullable List<LineItem> items) {
                double totalCubicYards = 0;
                adapter.setItemList(getActivity(), items);
                for (int i = 0; i < items.size(); i++) {
                    totalCubicYards = totalCubicYards + items.get(i).getVolume();
                }
                textRunningTotal.setText(currentDecimalFormat.format(totalCubicYards));
                ConcreteBags concreteBags =new ConcreteBags();
                ArrayList bagsList =  concreteBags.BagQuantity((totalCubicYards));
                textViewBagsOne.setText(currentDecimalFormat.format(bagsList.get(0)));
                textViewBagsTwo.setText(currentDecimalFormat.format(bagsList.get(1)));
            }
        });

        // Handle swipes.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                lineItemViewModel.delete(adapter.getItemAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "Item Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(rvLineItems);

        // Handle individual item clicks
        adapter.setOnItemClickListener(new ItemViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(LineItem lineItem) {
                // Navigate and pass the type to the input screen fragment/
                Fragment_MainDirections.ActionFragmentMainToInputScreenFragment action =
                        Fragment_MainDirections.actionFragmentMainToInputScreenFragment(0, "");
                action.setId(Integer.toString(lineItem.getID()));
                action.setType(0);
                action.setTitle("");
                Navigation.findNavController(getActivity(), R.id.fragment_container).navigate(action);
            }
        });

        // Handle total volume text view click.
        textRunningTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewSummary.getVisibility() == View.VISIBLE){
                    viewSummary.setVisibility(View.GONE);
                } else {
                    viewSummary.findViewById(R.id.tvBagsOne);
                    viewSummary.findViewById(R.id.tvBagsTwo);
                    viewSummary.setVisibility(View.VISIBLE);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        hideKeyboard(getActivity());
        UpdateScreen();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //Log.d(TAG, "onSaveInstanceState: totalVolume - " + textRunningTotal.getText().toString());
        if (!textRunningTotal.getText().toString().isEmpty()){
            outState.putDouble("totalVolume", Double.parseDouble(textRunningTotal.getText().toString()));
        } else {
            outState.putDouble("totalVolume", 0.0);
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            textRunningTotal.setText(currentDecimalFormat.format(savedInstanceState.getDouble("totalVolume", 0)));
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void UpdateScreen() {
        units = sharedPreferences.getString("units_system", "US");
        waste = sharedPreferences.getString("waste", "5");

        if ("US".equals(units)) {
            tvUnits.setText("Cubic Yards");
        } else if ("Metric".equals(units)) {
            tvUnits.setText("Cubic Meters");
        } else {
            Toast.makeText(getActivity(), "No units set", Toast.LENGTH_SHORT).show();
        }
    }
}
