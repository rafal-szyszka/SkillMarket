package it.szyszka.skillmarket.modules.mails.tasks;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.szyszka.skillmarket.modules.mails.adapters.MailViewAdapter;
import it.szyszka.skillmarket.modules.mails.model.Mail;
import it.szyszka.skillmarket.modules.mails.model.Mailbox;
import it.szyszka.skillmarket.modules.user.tasks.MyAsyncTask;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by rafal on 07.12.17.
 */

public class SyncInboxTask extends MyAsyncTask<Mailbox, Void, Mailbox> {
    private static final String TAG = SyncInboxTask.class.getSimpleName();

    private RecyclerView recyclerView;

    public SyncInboxTask(RecyclerView recyclerView) {
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
    protected void onSuccess(Response<Mailbox> response) {
        List<Mail> mails = new ArrayList<>();
        if(response.body().getReceived() != null) {
            mails.addAll(response.body().getReceived());
            for (Mail mail : mails) {
                Log.i(TAG, mail.getMessage().getTitle());
            }
            recyclerView.setAdapter(new MailViewAdapter(mails));
        }
    }

    @Override
    protected Response<Mailbox> doInBackground(Call<Mailbox>[] calls) {
        try {
            return calls[0].execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Response<Mailbox> listResponse) {
        super.onPostExecute(listResponse);
        handleResponse(listResponse);
    }
}
