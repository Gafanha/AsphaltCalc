package com.lusostudios.asphaltcalc.room_database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LineItemViewModel extends AndroidViewModel {

    private String TAG = "LineItemViewModel";
    private ListItemRepository repository;
    private LiveData<List<LineItem>> allLineItemsLive;
    private List<LineItem> allItemList;

    public LineItemViewModel(@NonNull Application application) {
        super(application);
        repository = new ListItemRepository(application);
        allLineItemsLive = repository.getAllLive();
        allItemList = repository.getItemsList();
    }

    // TODO getItem has to be implemented.\
    public LiveData<List<LineItem>> getItemByID(int id){
        repository.getItemByID(id);
        return repository.getItemByID(id);
    }

    public void insert(LineItem lineItem) {
        repository.insert(lineItem);
    }

    public void update(LineItem lineItem) {
        repository.update(lineItem);
    }

    public void delete(LineItem lineItem) {
        repository.delete(lineItem);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<LineItem>> getAllLive() {
        return allLineItemsLive;
    }

    public List<LineItem> getAllItemList (){
        return allItemList;
    }
}
