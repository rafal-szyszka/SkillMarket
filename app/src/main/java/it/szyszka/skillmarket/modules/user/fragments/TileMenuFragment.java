package it.szyszka.skillmarket.modules.user.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.user.listeners.ParcelableClickListener;
import it.szyszka.skillmarket.modules.user.views.TileView;

/**
 * Created by rafal on 11.10.17.
 */
public class TileMenuFragment extends Fragment {

    public static final String ICONS_EXTRAS = "icons";
    public static final String LABELS_EXTRAS = "labels";
    public static final String LISTENERS_EXTRAS = "listeners";

    private ArrayList<Integer> icons;
    private ArrayList<String> labels;
    private ArrayList<ParcelableClickListener> listeners;

    public static TileMenuFragment newInstance(ArrayList<Integer> icons, ArrayList<String> labels, ArrayList<ParcelableClickListener> listeners) {
        TileMenuFragment menuFragment = new TileMenuFragment();

        Bundle extras = new Bundle();
        extras.putIntegerArrayList(ICONS_EXTRAS, icons);
        extras.putStringArrayList(LABELS_EXTRAS, labels);
        extras.putParcelableArrayList(LISTENERS_EXTRAS, listeners);

        menuFragment.setArguments(extras);
        return menuFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View menu = inflater.inflate(R.layout.user_actions_fragment, container, false);

        ViewHandle handle = new ViewHandle(
                menu.findViewById(R.id.upper_left_tile),
                menu.findViewById(R.id.upper_right_tile),
                menu.findViewById(R.id.center_left_tile),
                menu.findViewById(R.id.center_right_tile),
                menu.findViewById(R.id.bottom_left_tile),
                menu.findViewById(R.id.bottom_right_tile)
        );

        initTiles(handle);
        return menu;
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
        icons = arguments.getIntegerArrayList(ICONS_EXTRAS);
        labels = arguments.getStringArrayList(LABELS_EXTRAS);
        listeners = arguments.getParcelableArrayList(LISTENERS_EXTRAS);
    }

    private class ViewHandle {
        TileView upperLeft;
        TileView upperRight;
        TileView centerLeft;
        TileView centerRight;
        TileView bottomLeft;
        TileView bottomRight;

        public ViewHandle(View upperLeft, View upperRight, View centerLeft,
                          View centerRight, View bottomLeft, View bottomRight) {
            this.upperLeft = new TileView(upperLeft);
            this.upperRight = new TileView(upperRight);
            this.centerLeft = new TileView(centerLeft);
            this.centerRight = new TileView(centerRight);
            this.bottomLeft = new TileView(bottomLeft);
            this.bottomRight = new TileView(bottomRight);
        }

        List<TileView> toList() {
            return Arrays.asList(upperLeft, upperRight, centerLeft, centerRight, bottomLeft, bottomRight);
        }

        void setIconsLabelsAndListeners() {
            Iterator<Integer> iconIterator = icons.iterator();
            Iterator<String> labelIterator = labels.iterator();
            Iterator<ParcelableClickListener> listenerIterator = listeners.iterator();
            for(TileView tile : toList()) {
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