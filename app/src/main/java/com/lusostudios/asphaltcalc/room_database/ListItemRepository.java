package com.lusostudios.asphaltcalc.room_database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListItemRepository {

    private LineItemDAO lineItemDAO;
    private LiveData<List<LineItem>> allLineItemsLive;
    private List<LineItem> allLineItemList;

    public ListItemRepository(Application application) {
        LineItemDatabase lineItemDatabase = LineItemDatabase.getInstance(application);
        lineItemDAO = lineItemDatabase.lineItemDAO();
        allLineItemsLive = lineItemDAO.getAllLive();
    }

    public void insert(LineItem lineItem) {
        new InsertAsyncTask(lineItemDAO).execute(lineItem);
    }

    public void update(LineItem lineItem) {
        new UpdateAsyncTask(lineItemDAO).execute(lineItem);
    }

    public void delete(LineItem lineItem) {
        new DeleteAsyncTask(lineItemDAO).execute(lineItem);
    }

    public void deleteAll() {
        new DeleteAllAsyncTask(lineItemDAO).execute();
    }

    public LiveData<List<LineItem>> getItemByID(int id) {
        return lineItemDAO.getItemByID(id);
    }

    public LiveData<List<LineItem>> getAllLive() {
        return allLineItemsLive;
    }

    public List<LineItem> getItemsList() {
        try {
            allLineItemList =  new GetAllLineItemListAsyncTask(lineItemDAO).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allLineItemList;
    }

    private static class InsertAsyncTask extends AsyncTask<LineItem, Void, Void> {
        private LineItemDAO lineItemDAO;

        private InsertAsyncTask(LineItemDAO lineItemDAO) {
            this.lineItemDAO = lineItemDAO;
        }

        @Override
        protected Void doInBackground(LineItem... lineItems) {
            lineItemDAO.insert(lineItems[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<LineItem, Void, Void> {
        private LineItemDAO lineItemDAO;

        private UpdateAsyncTask(LineItemDAO lineItemDAO) {
            this.lineItemDAO = lineItemDAO;
        }

        @Override
        protected Void doInBackground(LineItem... lineItems) {
            lineItemDAO.update(lineItems[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<LineItem, Void, Void> {
        private LineItemDAO lineItemDAO;

        private DeleteAsyncTask(LineItemDAO lineItemDAO) {
            this.lineItemDAO = lineItemDAO;
        }

        @Override
        protected Void doInBackground(LineItem... lineItems) {
            lineItemDAO.delete(lineItems[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private LineItemDAO lineItemDAO;

        private DeleteAllAsyncTask(LineItemDAO lineItemDAO) {
            this.lineItemDAO = lineItemDAO;
        }


        @Override
        protected Void doInBackground(Void... Voids) {
            lineItemDAO.deleteAll();
            return null;
        }
    }

    private static class GetAllLineItemListAsyncTask extends AsyncTask<Void, Void, List<LineItem>> {
        private LineItemDAO lineItemDAO;

        private GetAllLineItemListAsyncTask(LineItemDAO lineItemDAO) {
            this.lineItemDAO = lineItemDAO;
        }

        @Override
        protected List<LineItem> doInBackground(Void... Voids) {
            return lineItemDAO.getAllItemList();
        }

        @Override
        protected void onPostExecute(List<LineItem> lineItems) {
            super.onPostExecute(lineItems);
        }
    }
}
