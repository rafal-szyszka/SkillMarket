package it.szyszka.skillmarket.modules.user.tasks;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import it.szyszka.skillmarket.modules.user.adapters.FriendsAdapter;
import it.szyszka.skillmarket.modules.user.model.User;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by rafal on 26.10.17.
 */

public class GetFriendsTask extends MyAsyncTask<List<User>, Void, List<User>> {

    public static final String TAG = GetFriendsTask.class.getSimpleName();

    private RecyclerView recyclerView;

    public GetFriendsTask(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    protected void handleOnFailure(String errorMessage) {
        Log.i(TAG, errorMessage);
    }

    @Override
    protected void onNoServerResponse() {
        alertDialog.setTitle(onNoResponseAlertTitle);
        alertDialog.setContent(onNoResponseAlertContent);
        alertDialog.show();
    }

    @Override
    protected void onSuccess(Response<List<User>> response) {
        Log.i(TAG, "onSuccess");
        if(response.body() != null) {
            recyclerView.setAdapter(new FriendsAdapter(response.body()));
        }
    }

    @Override
    protected Response<List<User>> doInBackground(Call<List<User>>[] calls) {
        try {
            Log.i(TAG, "doInBackground");
            return calls[0].execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Response<List<User>> listResponse) {
        super.onPostExecute(listResponse);
        handleResponse(listResponse);
    }
}
