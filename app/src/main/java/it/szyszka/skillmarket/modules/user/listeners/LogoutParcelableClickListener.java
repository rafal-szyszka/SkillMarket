package it.szyszka.skillmarket.modules.user.listeners;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import it.szyszka.skillmarket.modules.user.activities.SignInActivity_;
import it.szyszka.skillmarket.modules.user.model.Credentials;
import lombok.NoArgsConstructor;

/**
 * Created by rafal on 13.10.17.
 */
@NoArgsConstructor
public class LogoutParcelableClickListener extends ParcelableClickListener {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<LogoutParcelableClickListener>() {
        @Override
        public LogoutParcelableClickListener createFromParcel(Parcel parcel) {
            return new LogoutParcelableClickListener(parcel);
        }

        @Override
        public LogoutParcelableClickListener[] newArray(int i) {
            return new LogoutParcelableClickListener[i];
        }
    };

    public LogoutParcelableClickListener(Parcel parcel) {
        super(parcel);
    }

    @Override
    public void onClick(View view) {
        Credentials.instantiate("","");
        SignInActivity_.intent(getFragment().getContext()).start();
    }
}
