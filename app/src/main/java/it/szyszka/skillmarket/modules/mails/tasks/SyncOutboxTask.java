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
 * Created by rafal on 08.12.17.
 */

public class SyncOutboxTask extends MyAsyncTask<Mailbox, Void, Mailbox> {

    public static final String TAG = SyncOutboxTask.class.getSimpleName();

    private RecyclerView recyclerView;

    public SyncOutboxTask(RecyclerView recyclerView) {
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
        Log.i(TAG, "Syncing Outbox");
        List<Mail> mails = new ArrayList<>();
        if(response.body().getSend() != null) {
            mails.addAll(response.body().getSend());
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
    protected void onPostExecute(Response<Mailbox> mailboxResponse) {
        super.onPostExecute(mailboxResponse);
        handleResponse(mailboxResponse);
    }
}
