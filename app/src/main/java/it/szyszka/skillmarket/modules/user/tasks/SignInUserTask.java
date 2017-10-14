package it.szyszka.skillmarket.modules.user.tasks;

import android.content.Intent;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.IOException;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.user.activities.SignInActivity;
import it.szyszka.skillmarket.modules.user.activities.UserProfileActivity;
import it.szyszka.skillmarket.modules.user.activities.UserProfileActivity_;
import it.szyszka.skillmarket.modules.user.model.User;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by rafal on 05.10.17.
 */

public class SignInUserTask extends MyAsyncTask<User, Void, User> {

    private static final String TAG = SignInUserTask.class.getSimpleName();
    private MaterialDialog alertDialog;
    private SignInActivity context;

    public SignInUserTask(SignInActivity context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog = new MaterialDialog.Builder(context)
                .title(context.getString(R.string.info_message_wait))
                .content(context.getString(R.string.info_message_signing_in))
                .progress(true, 0)
                .show();
    }

    @Override
    protected Response<User> doInBackground(Call<User>... calls) {
        try {
            return calls[0].execute();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Response<User> userResponse) {
        super.onPostExecute(userResponse);
        alertDialog.cancel();
        handleResponse(userResponse);
    }


    @Override
    public void onNoServerResponse() {
        alertDialog = new MaterialDialog.Builder(context)
                .title(context.getString(R.string.error_message_failure))
                .content(context.getString(R.string.error_message_no_internet))
                .positiveText("OK")
                .show();
    }

    @Override
    protected void onSuccess(Response<User> response) {
        Log.i(TAG, response.message());
        Intent intent = new Intent(context, UserProfileActivity_.class);
        intent.putExtra(UserProfileActivity.SIGNED_IN_USER, response.body());
        context.startActivity(intent);
    }

    @Override
    public void handleOnFailure(String errorMessage) {
        Log.e(TAG, errorMessage);
        alertDialog = new MaterialDialog.Builder(context)
                .title(context.getString(R.string.error_message_failure))
                .content(context.getString(R.string.error_message_invalid_credentials))
                .positiveText("OK")
                .show();
    }
}
