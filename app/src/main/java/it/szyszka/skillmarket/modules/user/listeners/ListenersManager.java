package it.szyszka.skillmarket.modules.user.listeners;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.ArrayList;

import it.szyszka.skillmarket.modules.user.fragments.management.TileConfiguration;

/**
 * Created by rafal on 12.10.17.
 */
public class ListenersManager {

    private FragmentManager manager;
    private int containerId;

    public ListenersManager(FragmentManager manager, int containerId) {
        this.manager = manager;
        this.containerId = containerId;
    }

    public ArrayList<ParcelableClickListener> prepareListeners(TileConfiguration configuration) {
        Log.i("CREATING: ", "In prepare listeners");
        ArrayList<ParcelableClickListener> listeners = new ArrayList<>();
        ArrayList<Fragment> fragments = configuration.getFragments();
        ParcelableClickListener listener;
        for(int i = 0; i < fragments.size() - 1; i++) {
            listener = new DefaultParcelableClickListener();
            listener.setContainerId(containerId);
            listener.setFragment(fragments.get(i));
            listener.setManager(manager);
            listeners.add(listener);
            Log.i("CREATING: ", String.valueOf(i));
        }

        listener = new LogoutParcelableClickListener();
        listener.setFragment(fragments.get(fragments.size() - 1));
        listeners.add(listener);
        return listeners;
    }

    private void populateListeners(ArrayList<ParcelableClickListener> listeners, ArrayList<Fragment> fragments) {


    }
}
