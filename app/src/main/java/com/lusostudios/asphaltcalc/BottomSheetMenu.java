package com.lusostudios.asphaltcalc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.lusostudios.asphaltcalc.adapters.MenuItemAdapter;

import ConCalc.Activity.Fragment_MainDirections;

public class BottomSheetMenu extends BottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_menu, container, false);

        //String TAG = "BottomSheetMenu";

        RecyclerView recyclerView = v.findViewById(R.id.recyclerViewMenu);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        //recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setHasFixedSize(true);
        MenuItemAdapter adapter = new MenuItemAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MenuItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String string) {
                Fragment_MainDirections.ActionFragmentMainToInputScreenFragment action =
                        Fragment_MainDirections.actionFragmentMainToInputScreenFragment(0, string);
                action.setId(null);
                action.setType(0);
                action.setTitle(string);
                Navigation.findNavController(getActivity(), R.id.fragment_container).navigate(action);
            }
        });
        return v;
    }
}
