package it.szyszka.skillmarket.modules.user.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import lombok.Getter;

/**
 * Created by rafal on 13.10.17.
 */
// TODO: 13.10.17 fix this
public class LogoutTile extends Fragment {
    @Getter private Context context;

    public LogoutTile(Context context) {
        this.context = context;
    }
}
