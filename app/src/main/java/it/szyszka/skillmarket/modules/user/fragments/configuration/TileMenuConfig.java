package it.szyszka.skillmarket.modules.user.fragments.configuration;

import android.os.Parcelable;

import java.util.ArrayList;

import it.szyszka.skillmarket.modules.user.listeners.ParcelableClickListener;

public interface TileMenuConfig extends Parcelable {

    ArrayList<Integer> getTileIds();
    ArrayList<Integer> getIconIds();
    ArrayList<String> getLabelIds();
    ArrayList<ParcelableClickListener> getListeners();

}
