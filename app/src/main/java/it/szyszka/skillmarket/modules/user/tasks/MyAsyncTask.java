package it.szyszka.skillmarket.modules.user.tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by rafal on 05.10.17.
 */

public abstract class MyAsyncTask<Params, Progress, Result> extends AsyncTask<Call<Params>, Progress, Response<Result>> {

    private static final String TAG = MyAsyncTask.class.getSimpleName();

    protected void onServerResponse(Response<Params> response) {
        if(response.isSuccessful()) {
            onSuccess(response);
        } else {
            onFailure(response);
        }
    }

    protected void onFailure(Response<Params> response) {
        String errorMessage = null;
        try {
            errorMessage = response.errorBody().string();
        } catch (IOException e) {
            Log.e(TAG, "No error message found.");
        }
        if(errorMessage != null) {
            handleOnFailure(errorMessage);
        }
    }

    protected void handleResponse(Response<Params> response) {
        if(response != null) {
            onServerResponse(response);
        } else {
            Log.e(TAG, "No internet connection");
            onNoServerResponse();
        }
    }

    protected abstract void handleOnFailure(String errorMessage);

    protected abstract void onNoServerResponse();

    protected abstract void onSuccess(Response<Params> response);

}
