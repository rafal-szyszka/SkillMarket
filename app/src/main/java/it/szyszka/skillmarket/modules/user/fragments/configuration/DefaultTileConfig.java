package it.szyszka.skillmarket.modules.user.fragments.configuration;

import android.content.Context;
import android.os.Parcel;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.user.listeners.ParcelableClickListener;
import it.szyszka.skillmarket.modules.user.model.User;

/**
 * Created by rafal on 14.10.17.
 */

public class DefaultTileConfig implements TileMenuConfig {

    private static final String TAG = DefaultTileConfig.class.getSimpleName();
    private ArrayList<Integer> tileIds;
    private ArrayList<Integer> iconIds;
    private ArrayList<String> labelIds;
    private ArrayList<ParcelableClickListener> listeners;
    private User signedInUser;

    private DefaultTileListeners defaultTileListeners;

    public static final Creator<DefaultTileConfig> CREATOR = new Creator<DefaultTileConfig>() {
        @Override
        public DefaultTileConfig createFromParcel(Parcel parcel) {
            return new DefaultTileConfigBuilder().setParcel(parcel).build();
        }

        @Override
        public DefaultTileConfig[] newArray(int i) {
            return new DefaultTileConfig[i];
        }
    };

    public DefaultTileConfig(Context context, Integer containerId, FragmentManager fragmentManager, User signedInUser) {
        this.signedInUser = signedInUser;
        defaultTileListeners = new DefaultTileListeners(containerId, fragmentManager, context);
        tileIds = getDefaultTileIds();
        iconIds = getDefaultIconIds();
        labelIds = getDefaultLabelIds(context);
        listeners = getDefaultListeners(defaultTileListeners);
    }

    private ArrayList<ParcelableClickListener> getDefaultListeners(DefaultTileListeners defaultTileListeners) {
        Log.i(TAG, signedInUser.getFullName());
        return new ArrayList<>(Arrays.asList(
                defaultTileListeners.instantiateClickListener(DefaultTileListeners.ACCOUNT, signedInUser),
                defaultTileListeners.instantiateClickListener(DefaultTileListeners.MAILS, signedInUser),
                defaultTileListeners.instantiateClickListener(DefaultTileListeners.PEOPLE, null),
                defaultTileListeners.instantiateClickListener(DefaultTileListeners.ANNOUNCEMENTS, signedInUser),
                defaultTileListeners.instantiateClickListener(DefaultTileListeners.SETTINGS, null),
                defaultTileListeners.instantiateClickListener(DefaultTileListeners.LOGOUT, null)
        ));
    }

    private ArrayList<String> getDefaultLabelIds(Context context) {
        return new ArrayList<>(Arrays.asList(
                context.getResources().getStringArray(R.array.action_tiles_labels_array))
        );
    }

    private ArrayList<Integer> getDefaultIconIds() {
        return new ArrayList<>(Arrays.asList(
                R.mipmap.ic_user_account, R.mipmap.ic_mail,
                R.mipmap.ic_people, R.mipmap.ic_offer,
                R.mipmap.ic_settings_2, R.mipmap.ic_logout
        ));
    }

    private ArrayList<Integer> getDefaultTileIds() {
        return new ArrayList<>(Arrays.asList(
                R.id.tile_1, R.id.tile_2,
                R.id.tile_3, R.id.tile_4,
                R.id.tile_5, R.id.tile_6
        ));
    }

    public DefaultTileConfig(Parcel parcel) {
        tileIds = convertFromSimpleArray(parcel.createIntArray());
        iconIds = convertFromSimpleArray(parcel.createIntArray());
        labelIds = parcel.createStringArrayList();
        listeners = parcel.createTypedArrayList(ParcelableClickListener.CREATOR);
    }

    @Override
    public ArrayList<Integer> getTileIds() {
        return tileIds;
    }

    @Override
    public ArrayList<Integer> getIconIds() {
        return iconIds;
    }

    @Override
    public ArrayList<String> getLabelIds() {
        return labelIds;
    }

    @Override
    public ArrayList<ParcelableClickListener> getListeners() {
        return listeners;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(convertToSimpleArray(tileIds));
        parcel.writeIntArray(convertToSimpleArray(iconIds));
        parcel.writeStringList(labelIds);
        parcel.writeTypedList(listeners);
    }

    private int[] convertToSimpleArray(ArrayList<Integer> arrayList) {
        int[] simpleArray = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            simpleArray[i] = arrayList.get(i);
        }
        return simpleArray;
    }

    private ArrayList<Integer> convertFromSimpleArray(int[] intArray) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i : intArray) {
            arrayList.add(i);
        }
        return arrayList;
    }


}
