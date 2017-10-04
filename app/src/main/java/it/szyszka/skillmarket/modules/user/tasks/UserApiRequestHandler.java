package it.szyszka.skillmarket.modules.user.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.api.ApiResponse;
import it.szyszka.skillmarket.modules.user.activities.SignInActivity;
import it.szyszka.skillmarket.modules.user.activities.SignInActivity_;
import it.szyszka.skillmarket.modules.user.activities.SignUpActivity;
import it.szyszka.skillmarket.modules.user.activities.SignUpActivity_;
import it.szyszka.skillmarket.modules.user.api.UserResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by rafal on 04.10.17.
 */

public class UserApiRequestHandler extends AsyncTask<Call<UserResponse>, Void, Response<UserResponse>> implements ApiResponse<UserResponse> {

    private static final String TAG = UserApiRequestHandler.class.getSimpleName();
    private Context context;
    private SweetAlertDialog alertDialog;

    public UserApiRequestHandler(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        alertDialog.setTitleText(context.getString(R.string.info_message_wait));
        alertDialog.setContentText(context.getString(R.string.info_message_signing_up));
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    @Override
    protected Response<UserResponse> doInBackground(Call<UserResponse>... calls) {
        try {
            return calls[0].execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Response<UserResponse> response) {
        super.onPostExecute(response);
        alertDialog.cancel();

        if(response.isSuccessful()) {
            onSuccess(response);
        } else {
            onFailure(response);
        }
    }

    @Override
    public void onSuccess(Response<UserResponse> response) {
        Intent intent;
        Log.i(TAG, "Successfully handled request: " + response.body());
        intent = new Intent(context, SignInActivity_.class);
        intent.putExtra(SignInActivity.MESSAGE_KEY, context.getString(R.string.success_message_signed_up));
        intent.putExtra(SignInActivity.REGISTRATION_STATUS_KEY, true);
        context.startActivity(intent);
    }

    @Override
    public void onFailure(Response<UserResponse> response) {
        String errorMessage = null;
        try {
            errorMessage = response.errorBody().string();
        } catch (IOException e) {
            Log.e(TAG, "No error message found.");
        }
        if(errorMessage != null) {
            handleErrors(errorMessage);
        }
    }

    @Override
    public void handleErrors(String errorMessage) {
        Intent intent;

        if (errorMessage.equals(UserErrorMessages.EMAIL_TAKEN.getMessage())) {

            intent = new Intent(context, SignUpActivity_.class);
            intent.putExtra(SignUpActivity.EMAIL_TAKEN_KEY, true);
            context.startActivity(intent);

        } else if(errorMessage.equals(UserErrorMessages.NICKNAME_TAKEN.getMessage())) {

            intent = new Intent(context, SignUpActivity_.class);
            intent.putExtra(SignUpActivity.NICKNAME_TAKEN_KEY, true);
            context.startActivity(intent);

        } else {

            intent = new Intent(context, SignInActivity_.class);
            intent.putExtra(SignInActivity.MESSAGE_KEY, context.getString(R.string.error_message_uknown));
            intent.putExtra(SignInActivity.REGISTRATION_STATUS_KEY, false);
            context.startActivity(intent);

        }
    }

    private enum UserErrorMessages {

        EMAIL_TAKEN("\"EMAIL_TAKEN\""),
        NICKNAME_TAKEN("\"NICKNAME_TAKEN\"");

        private String message;

        UserErrorMessages(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
