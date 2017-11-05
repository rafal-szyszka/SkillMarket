package it.szyszka.skillmarket.modules.user.tasks;

import android.content.Context;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.IOException;
import java.util.ArrayList;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.user.fragments.FriendsFragment;
import it.szyszka.skillmarket.modules.user.model.User;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by rafal on 26.10.17.
 */

public class GetFriendsTask extends MyAsyncTask<ArrayList<User>, Void, ArrayList<User>> {

    public static final String TAG = GetFriendsTask.class.getSimpleName();

    private FriendsFragment friendsFragment;

    public GetFriendsTask(FriendsFragment friendsFragment) {
        this.friendsFragment = friendsFragment;
    }

    @Override
    protected void handleOnFailure(String errorMessage) {

    }

    @Override
    protected void onNoServerResponse() {
        alertDialog.setTitle(onNoResponseAlertTitle);
        alertDialog.setContent(onNoResponseAlertContent);
        alertDialog.show();
    }

    @Override
    protected void onSuccess(Response<ArrayList<User>> response) {

    }

    @Override
    protected Response<ArrayList<User>> doInBackground(Call<ArrayList<User>>[] calls) {
        try {
            return calls[0].execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
