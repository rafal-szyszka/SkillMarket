package it.szyszka.skillmarket.modules.user.tasks;

import android.util.Log;

import java.io.IOException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.user.activities.SignInActivity;
import it.szyszka.skillmarket.modules.user.activities.UserProfileActivity_;
import it.szyszka.skillmarket.modules.user.model.User;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by rafal on 05.10.17.
 */

public class SignInUserTask extends MyAsyncTask<User, Void, User> {

    private static final String TAG = SignInUserTask.class.getSimpleName();
    private SweetAlertDialog alertDialog;
    private SignInActivity context;

    public SignInUserTask(SignInActivity context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        alertDialog.setTitleText(context.getString(R.string.info_message_wait));
        alertDialog.setContentText(context.getString(R.string.info_message_signing_in));
        alertDialog.setCancelable(true);
        alertDialog.show();
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
        alertDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        alertDialog.setTitleText(context.getString(R.string.failure_message));
        alertDialog.setContentText(context.getString(R.string.error_message_no_internet));
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    @Override
    protected void onSuccess(Response<User> response) {
        Log.i(TAG, response.message());
        UserProfileActivity_.intent(context).start();
    }

    @Override
    public void handleOnFailure(String errorMessage) {
        Log.e(TAG, errorMessage);
        Log.e(TAG, "Oh no");
    }
}
