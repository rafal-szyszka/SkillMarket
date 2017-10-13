package it.szyszka.skillmarket.modules.user.fragments.management;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import it.szyszka.skillmarket.modules.user.fragments.TileMenuFragment;
import it.szyszka.skillmarket.modules.user.listeners.ListenersManager;
import lombok.Setter;

/**
 * Created by rafal on 12.10.17.
 */

public class TileManager {

    private TileWorker worker;
    @Setter private FragmentManager fragmentManager;
    @Setter private int containerId;

    public TileManager(TileWorker worker) {
        this.worker = worker;
    }

    public void initTileMenu() {
        initWorker();
        Log.i("CREATING:", "after initWorker");
        TileMenuFragment tileMenu = initResources();
        Log.i("CREATING:", "after initResources");
        showTileMenu(tileMenu);
    }

    private void initWorker() {
        worker.setListenersManager(new ListenersManager(fragmentManager, containerId));
    }

    private TileMenuFragment initResources() {
        Log.i("CREATING:", "in initResources");
        return TileMenuFragment.newInstance(
                worker.prepareIcons(),
                worker.prepareLabels(),
                worker.prepareListeners()
        );
    }

    private void showTileMenu(Fragment tileMenu) {
        fragmentManager.beginTransaction()
                .replace(containerId, tileMenu)
                .commit();
    }

}
