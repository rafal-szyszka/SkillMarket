package it.szyszka.skillmarket.common.view;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by rafal on 14.10.17.
 */

public class ShortcutTile implements Parcelable {

    @Getter @Setter private Integer iconId;
    @Getter @Setter private String label;

    public static final Creator<ShortcutTile> CREATOR = new Creator<ShortcutTile>() {
        @Override
        public ShortcutTile createFromParcel(Parcel parcel) {
            return new ShortcutTile(parcel);
        }

        @Override
        public ShortcutTile[] newArray(int i) {
            return new ShortcutTile[i];
        }
    };

    public ShortcutTile(Parcel parcel) {
        iconId = parcel.readInt();
        label = parcel.readString();
    }

    public ShortcutTile() {
    }

    public ShortcutTile(Integer iconId, String label) {
        this.iconId = iconId;
        this.label = label;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(iconId);
        parcel.writeString(label);
    }
}
