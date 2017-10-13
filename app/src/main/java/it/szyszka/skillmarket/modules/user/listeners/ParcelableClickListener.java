package it.szyszka.skillmarket.modules.user.listeners;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by rafal on 12.10.17.
 */

public abstract class ParcelableClickListener implements View.OnClickListener, Parcelable {

    @Getter @Setter private Fragment fragment;
    @Setter private FragmentManager manager;
    @Setter private int containerId;

    public ParcelableClickListener() {}

    protected ParcelableClickListener(Parcel parcel) {
        containerId = parcel.readInt();
    }

    @Override
    public int describeContents() { return 0;}

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(containerId);
    }

    @Override
    public void onClick(View view) {
        manager.beginTransaction()
                .replace(containerId, fragment)
                .commit();
    }
}