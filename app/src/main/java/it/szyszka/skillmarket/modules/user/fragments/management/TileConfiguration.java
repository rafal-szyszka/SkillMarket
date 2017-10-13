package it.szyszka.skillmarket.modules.user.fragments.management;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

import lombok.Getter;

/**
 * Created by rafal on 13.10.17.
 */
public class TileConfiguration {

    @Getter private ArrayList<Integer> icons;
    @Getter private ArrayList<String> labels;
    @Getter private ArrayList<Fragment> fragments;

    public TileConfiguration(ArrayList<Integer> icons, ArrayList<String> labels, ArrayList<Fragment> fragments) {
        this.icons = icons;
        this.labels = labels;
        this.fragments = fragments;
    }
}
