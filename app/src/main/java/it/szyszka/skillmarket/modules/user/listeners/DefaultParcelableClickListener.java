package it.szyszka.skillmarket.modules.user.listeners;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import lombok.NoArgsConstructor;

/**
 * Created by rafal on 13.10.17.
 */
@NoArgsConstructor
public class DefaultParcelableClickListener extends ParcelableClickListener {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<DefaultParcelableClickListener>() {
        @Override
        public DefaultParcelableClickListener createFromParcel(Parcel parcel) {
            return new DefaultParcelableClickListener(parcel);
        }

        @Override
        public DefaultParcelableClickListener[] newArray(int i) {
            return new DefaultParcelableClickListener[i];
        }
    };

    public DefaultParcelableClickListener(Fragment fragment, FragmentManager manager, Integer containerId) {
        setFragment(fragment);
        setManager(manager);
        setContainerId(containerId);
    }

    private DefaultParcelableClickListener(Parcel parcel) {super(parcel);}

}