package it.szyszka.skillmarket.modules.mails.tasks;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.mails.adapters.RecipientsArrayAdapter;
import it.szyszka.skillmarket.modules.user.model.User;
import it.szyszka.skillmarket.modules.user.tasks.MyAsyncTask;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by rafal on 08.12.17.
 */

public class GetAllRecipientsTask extends MyAsyncTask<List<User>, Void, List<User>> {

    public static final String TAG = GetAllRecipientsTask.class.getSimpleName();

    private Spinner recipients;
    private Context context;

    public GetAllRecipientsTask(Spinner recipients, Context context) {
        this.recipients = recipients;
        this.context = context;
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
        if(response != null && response.body() != null) {
            RecipientsArrayAdapter recipientAdapter =
                    new RecipientsArrayAdapter(context, R.layout.utils_user_spinner_view, response.body());
            recipients.setAdapter(recipientAdapter);
        }
    }

    @Override
    protected Response<List<User>> doInBackground(Call<List<User>>[] calls) {
        try {
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
