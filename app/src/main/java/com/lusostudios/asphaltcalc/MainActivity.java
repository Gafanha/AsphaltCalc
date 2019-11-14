package com.lusostudios.asphaltcalc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.lusostudios.asphaltcalc.preferences_and_other.Action_Items;
import com.lusostudios.asphaltcalc.preferences_and_other.SettingsActivity;
import com.lusostudios.asphaltcalc.room_database.LineItemViewModel;
import com.lusostudios.asphaltcalc.utils.Pixel_Utilities;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String TAG = "MainActivity";
    BottomAppBar bottomAppBar;
    FloatingActionButton fab;
    View fragmentContainer;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Initialize Mobile Ads SDK
        // https://developers.google.com/admob/android/quick-start#initialize_mobile_ads_sdk
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        fragmentContainer = findViewById(R.id.fragment_container);

        bottomAppBar = findViewById(R.id.bottom_bar);
        setSupportActionBar(bottomAppBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        // Get navigation controller.
        final NavController navController = Navigation.findNavController(this, R.id.fragment_container);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(Fragment_MainDirections.actionFragmentMainToBottomSheetMenu());
            }
        });

        // Hide bottom bar and fab when main fragment is not displayed.
        // Also close bottom sheet.
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.fragment_Main) {
                    bottomAppBar.setVisibility(View.VISIBLE);
                    fab.setVisibility(View.VISIBLE);

                    CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) fragmentContainer.getLayoutParams();

                    // Converts 56 dp into its equivalent px
                    float bottomMargin = new Pixel_Utilities().convertDpToPixel(50, getApplicationContext());
                    Log.d(TAG, "onDestinationChanged: pixels - " + bottomMargin);

                    params.setMargins(0,0,0,(int)bottomMargin);
                    fragmentContainer.setLayoutParams(params);
                } else {
                    bottomAppBar.setVisibility(View.GONE);
                    fab.setVisibility(View.GONE);
                    CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) fragmentContainer.getLayoutParams();
                    params.setMargins(0,0,0,0);
                    fragmentContainer.setLayoutParams(params);

                    // Dismiss bottom sheet.
                    Fragment prev = getSupportFragmentManager().findFragmentByTag("BottomSheetMenu");
                    if (prev != null) {
                        //Log.d(TAG, "onDestinationChanged: " + prev.getTag());
                        DialogFragment df = (DialogFragment) prev;
                        df.dismiss();
                    }
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Navigate and pass the type to the input screen fragment/
        Fragment_MainDirections.ActionFragmentMainToInputScreenFragment action =
                Fragment_MainDirections.actionFragmentMainToInputScreenFragment(item.getItemId(), item.getTitle().toString());
        action.setType(item.getItemId());
        action.setTitle(item.getTitle().toString());
        Navigation.findNavController(this, R.id.fragment_container).navigate(action);
        // Close the bottom sheet.
        //sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        LineItemViewModel lineItemViewModel = new LineItemViewModel(getApplication());

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.clear:
                // TODO create code to save new Line Item.
                lineItemViewModel.deleteAll();
                Toast.makeText(this, "All Items Deleted", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.share:
                Action_Items.Share(context, lineItemViewModel.getAllItemList(), "US");
                return true;
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.concalc:
                Action_Items.GetApps(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
