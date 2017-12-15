package it.szyszka.skillmarket.modules.offers.tasks;

import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.io.IOException;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.offers.fragments.AdvertisementFragment;
import it.szyszka.skillmarket.modules.offers.model.Announcement;
import it.szyszka.skillmarket.modules.user.tasks.MyAsyncTask;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by rafal on 08.12.17.
 */

public class PlaceAdvertTask extends MyAsyncTask<Announcement, Void, Announcement> {

    private static final String TAG = PlaceAdvertTask.class.getSimpleName();

    private FragmentManager fragmentManager;
    private AdvertisementFragment fragment;

    public PlaceAdvertTask(FragmentManager fragmentManager, AdvertisementFragment fragment) {
        this.fragmentManager = fragmentManager;
        this.fragment = fragment;
    }

    @Override
    protected void handleOnFailure(String errorMessage) {
        Log.e(TAG, errorMessage);
    }

    @Override
    protected void onNoServerResponse() {
        Log.e(TAG, "No internet connection.");
    }

    @Override
    protected void onSuccess(Response<Announcement> response) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragments, fragment)
                .commit();
    }

    @Override
    protected Response<Announcement> doInBackground(Call<Announcement>[] calls) {
        try {
            return calls[0].execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Response<Announcement> announcementResponse) {
        super.onPostExecute(announcementResponse);
        handleResponse(announcementResponse);
    }
}
