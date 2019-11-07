package com.lusostudios.asphaltcalc.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.lusostudios.asphaltcalc.R;
import com.lusostudios.asphaltcalc.input_screen.InputImages;
import com.lusostudios.asphaltcalc.room_database.LineItem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.ItemViewHolder> {

    Activity activity;
    private OnItemClickListener listener;
    private List<LineItem> itemList = new ArrayList<>();
    DecimalFormat format = new DecimalFormat("#,###.#");
    Context context;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.line_item2, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int position) {

        // Get the data model based on position
        LineItem lineItem = itemList.get(position);
        // Set text for individual views.
        viewHolder.ivIcon.setImageDrawable(InputImages.getDrawable(activity, lineItem.getTitle()));
        viewHolder.txTittle.setText(lineItem.getTitle());

        // Use helper class to build description.
        BuildItemDescription itemDescription = new BuildItemDescription();
        viewHolder.txDescription.setText(itemDescription.getDescription(lineItem));
        viewHolder.txWaste.setText("Waste: " + lineItem.get_waste());
        viewHolder.txVolume.setText(format.format(lineItem.getVolume()));
        // TODO Set color based on whether volume is negative or not.
        if (lineItem.getVolume() >= 0) {
            viewHolder.txVolume.setTextColor(ContextCompat.getColor(context, R.color.colorOnPrimary));
        } else {
            viewHolder.txVolume.setTextColor(ContextCompat.getColor(context, R.color.colorSecondary));
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(Activity activity, List<LineItem> lineItems) {
        this.itemList = lineItems;
        this.activity = activity;
        notifyDataSetChanged();
    }

    public LineItem getItemAt(int position) {
        return itemList.get(position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        // Each data item is just a string in this case.
        private ImageView ivIcon;
        private TextView txTittle;
        private TextView txDescription;
        private TextView txWaste;
        private TextView txVolume;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            txTittle = itemView.findViewById(R.id.tvTitle);
            txDescription = itemView.findViewById(R.id.tv60PoundBags);
            txWaste = itemView.findViewById(R.id.tvWaste);
            txVolume = itemView.findViewById(R.id.tvVolume);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(itemList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(LineItem lineItem);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
