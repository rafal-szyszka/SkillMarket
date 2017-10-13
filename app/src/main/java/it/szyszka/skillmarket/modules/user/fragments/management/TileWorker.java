package it.szyszka.skillmarket.modules.user.fragments.management;

import android.util.Log;

import java.util.ArrayList;

import it.szyszka.skillmarket.modules.user.listeners.ListenersManager;
import it.szyszka.skillmarket.modules.user.listeners.ParcelableClickListener;
import lombok.Setter;

/**
 * Created by rafal on 13.10.17.
 */
public class TileWorker {

    @Setter private ListenersManager listenersManager;
    private TileConfiguration configuration;

    public TileWorker(TileConfiguration configuration) {
        this.configuration = configuration;
    }

    public ArrayList<Integer> prepareIcons() {
        Log.i("CREATING:", "in icons");
        return configuration.getIcons();
    }

    public ArrayList<String> prepareLabels() {
        Log.i("CREATING:", "in labels");
        return configuration.getLabels();
    }

    public ArrayList<ParcelableClickListener> prepareListeners() {
        Log.i("CREATING:", "in listeners");
        return listenersManager.prepareListeners(configuration);
    }
}
