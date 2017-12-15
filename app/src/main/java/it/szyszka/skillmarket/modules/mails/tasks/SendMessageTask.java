package it.szyszka.skillmarket.modules.mails.tasks;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.mails.fragments.MailsFragment;
import it.szyszka.skillmarket.modules.mails.model.ProofOfPosting;
import it.szyszka.skillmarket.modules.user.model.User;
import it.szyszka.skillmarket.modules.user.tasks.MyAsyncTask;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by rafal on 08.12.17.
 */

public class SendMessageTask extends MyAsyncTask<ProofOfPosting, Void, ProofOfPosting> {

    private static final String TAG = SendMessageTask.class.getSimpleName();

    private Context context;
    private FragmentManager fragmentManager;
    private User signedInUser;

    public SendMessageTask(Context context, FragmentManager fragmentManager, User signedInUser) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.signedInUser = signedInUser;
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
    protected void onSuccess(Response<ProofOfPosting> response) {
        if(response != null && response.body() != null) {
            Toast.makeText(context, "Message send!", Toast.LENGTH_SHORT).show();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragments, MailsFragment.newInstance(signedInUser))
                    .commit();
        }
    }

    @Override
    protected Response<ProofOfPosting> doInBackground(Call<ProofOfPosting>[] calls) {
        try {
            return calls[0].execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Response<ProofOfPosting> proofOfPostingResponse) {
        super.onPostExecute(proofOfPostingResponse);
        handleResponse(proofOfPostingResponse);
    }
}
