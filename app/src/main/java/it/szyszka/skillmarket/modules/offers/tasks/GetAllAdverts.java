package it.szyszka.skillmarket.modules.offers.tasks;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import it.szyszka.skillmarket.modules.offers.adapters.AdvertAdapter;
import it.szyszka.skillmarket.modules.offers.model.Announcement;
import it.szyszka.skillmarket.modules.user.tasks.MyAsyncTask;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by rafal on 08.12.17.
 */

public class GetAllAdverts extends MyAsyncTask<List<Announcement>, Void, List<Announcement>> {

    private static final String TAG = GetAllAdverts.class.getSimpleName();

    private RecyclerView recyclerView;

    public GetAllAdverts(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    protected void handleOnFailure(String errorMessage) {
        Log.i(TAG, errorMessage);
    }

    @Override
    protected void onNoServerResponse() {
        Log.i(TAG, "No internet connection");
    }

    @Override
    protected void onSuccess(Response<List<Announcement>> response) {
        if(response != null && response.body() != null) {
            for (Announcement a :
                    response.body()) {
                Log.i(TAG, a.getAdvertiser().getFullName());
            }
            recyclerView.setAdapter(new AdvertAdapter(response.body()));
        }
    }

    @Override
    protected Response<List<Announcement>> doInBackground(Call<List<Announcement>>[] calls) {
        try {
            return calls[0].execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Response<List<Announcement>> listResponse) {
        super.onPostExecute(listResponse);
        handleResponse(listResponse);
    }
}
