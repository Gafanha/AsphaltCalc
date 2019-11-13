package com.lusostudios.asphaltcalc.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lusostudios.asphaltcalc.R;
import com.lusostudios.asphaltcalc.input_screen.InputImages;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ItemViewHolder> {

    Activity activity;
    private OnItemClickListener listener;
    private String[] menuItemList;

    public MenuItemAdapter(Activity activity) {
        this.activity = activity;
        menuItemList = activity.getResources().getStringArray(R.array.bottom_sheet_menu);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.menu_item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int position) {

        // Get the data model based on position
        String menuItem = menuItemList[position];
        // Set text for individual views.
        viewHolder.ivIcon.setImageDrawable(InputImages.getDrawable(activity, menuItemList[position]));
        viewHolder.txTittle.setText(menuItemList[position]);
    }

    @Override
    public int getItemCount() {
        return menuItemList.length;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private ImageView ivIcon;
        private TextView txTittle;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            txTittle = itemView.findViewById(R.id.tvTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(menuItemList[position]);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String string);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
