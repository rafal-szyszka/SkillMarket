package it.szyszka.skillmarket.modules.user.fragments;

import android.os.Bundle;
import android.service.quicksettings.Tile;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.user.fragments.configuration.TileMenuConfig;
import it.szyszka.skillmarket.modules.user.listeners.ParcelableClickListener;
import it.szyszka.skillmarket.modules.user.views.TileView;

/**
 * Created by rafal on 11.10.17.
 */
public class TileMenuFragment extends Fragment {

    public static final String CONFIG_EXTRAS = "config";
    private static final String TAG = TileMenuFragment.class.getSimpleName();

    private TileMenuConfig config;

    public static TileMenuFragment newInstance(TileMenuConfig config) {
        TileMenuFragment menuFragment = new TileMenuFragment();

        Bundle extras = new Bundle();
        extras.putParcelable(CONFIG_EXTRAS, config);

        menuFragment.setArguments(extras);
        return menuFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View menu = inflater.inflate(R.layout.user_actions_fragment, container, false);

        ViewHandle handle = new ViewHandle(
                getViewsByIds(menu)
        );

        initTiles(handle);
        return menu;
    }

    @NonNull
    private ArrayList<View> getViewsByIds(View menu) {
        ArrayList<View> tiles = new ArrayList<>();
        for (Integer tileId : config.getTileIds()) {
            tiles.add(menu.findViewById(tileId));
        }
        return tiles;
    }

    private void initTiles(ViewHandle handle) {
        handle.setIconsLabelsAndListeners();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if(arguments != null) handleArguments(arguments);
    }

    private void handleArguments(Bundle arguments) {
        Log.i(TAG, "Handling arguments");
        config = arguments.getParcelable(CONFIG_EXTRAS);
    }

    private class ViewHandle {
        ArrayList<TileView> tiles;

        ViewHandle(ArrayList<View> tileViews) {
            tiles = new ArrayList<>();
            for (View tileView : tileViews ) {
                tiles.add(new TileView(tileView));
            }
        }

        void setIconsLabelsAndListeners() {
            Iterator<Integer> iconIterator = config.getIconIds().iterator();
            Iterator<String> labelIterator = config.getLabelIds().iterator();
            Iterator<ParcelableClickListener> listenerIterator = config.getListeners().iterator();
            for(TileView tile : tiles) {
                tile.replaceIconImageWith(
                        iconIterator.hasNext() ? iconIterator.next() : R.drawable.ic_default_category
                );

                tile.replaceLabelTextWith(
                        labelIterator.hasNext() ? labelIterator.next() : ""
                );

                tile.setOnClickListener(
                        listenerIterator.hasNext() ? listenerIterator.next() : null
                );
            }
        }
    }
}