package it.szyszka.skillmarket.modules.user.listeners;

import android.os.Parcel;
import android.os.Parcelable;

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

    private DefaultParcelableClickListener(Parcel parcel) {super(parcel);}

}
