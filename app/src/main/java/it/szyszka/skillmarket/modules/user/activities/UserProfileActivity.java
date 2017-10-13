package it.szyszka.skillmarket.modules.user.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Arrays;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.announcements.fragments.OffersTile_;
import it.szyszka.skillmarket.modules.application.fragments.AppSettingsFragment_;
import it.szyszka.skillmarket.modules.user.fragments.LogoutTile;
import it.szyszka.skillmarket.modules.user.fragments.MailsTile_;
import it.szyszka.skillmarket.modules.user.fragments.PeopleTile_;
import it.szyszka.skillmarket.modules.user.fragments.UserAccountTile_;
import it.szyszka.skillmarket.modules.user.fragments.management.TileConfiguration;
import it.szyszka.skillmarket.modules.user.fragments.management.TileManager;
import it.szyszka.skillmarket.modules.user.fragments.management.TileWorker;
import it.szyszka.skillmarket.modules.user.listeners.ListenersManager;
import it.szyszka.skillmarket.modules.user.listeners.UserNavigationMenuListener;
import it.szyszka.skillmarket.modules.user.model.User;

import static it.szyszka.skillmarket.modules.user.activities.SignInActivity.MESSAGE_KEY;
import static it.szyszka.skillmarket.modules.user.activities.SignInActivity.STATUS_KEY;

/**
 * Created by rafal on 06.10.17.
 */
@EActivity(R.layout.user_profile_activity)
public class UserProfileActivity extends AppCompatActivity {

    public static final String SIGNED_IN_USER = "signed_in_user";
    private static final String TAG = UserProfileActivity.class.getSimpleName();

    private ActionBarDrawerToggle drawerToggle;
    private User signedUser;

    @ViewById(R.id.user_toolbar)
    Toolbar toolbar;

    @ViewById(R.id.user_drawer_layout)
    DrawerLayout drawerLayout;

    @ViewById(R.id.user_nav_view)
    NavigationView navigationView;

    @ViewById(R.id.user_profile_view)
    View profileView;

    @AfterViews
    void initView() {
        readExtras();
        configureToolbar();
        configureNavigationDrawer();
        configureNavDrawerHeader();
        initHelloFragment();
    }

    private void initHelloFragment() {
        TileConfiguration tileConfiguration = configureTileMenu();

        TileManager manager = new TileManager(
                new TileWorker(tileConfiguration)
        );
        manager.setFragmentManager(getSupportFragmentManager());
        manager.setContainerId(R.id.fragments);
        manager.initTileMenu();
    }

    private TileConfiguration configureTileMenu() {
        return new TileConfiguration(
                prepareTileMenuIcons(),
                prepareTileMenuLabels(),
                prepareFragments()
        );
    }

    private ArrayList<Fragment> prepareFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        populateFragments(fragments);
        return fragments;
    }

    private void populateFragments(ArrayList<Fragment> fragments) {
        fragments.add(new UserAccountTile_());
        fragments.add(new MailsTile_());
        fragments.add(new PeopleTile_());
        fragments.add(new OffersTile_());
        fragments.add(new AppSettingsFragment_());
        fragments.add(new LogoutTile(getApplicationContext()));
    }

    @NonNull
    private ArrayList<String> prepareTileMenuLabels() {
        return new ArrayList<>(Arrays.asList(
                getResources().getStringArray(R.array.action_tiles_labels_array))
        );
    }

    @NonNull
    private ArrayList<Integer> prepareTileMenuIcons() {
        return new ArrayList<>(Arrays.asList(
                R.mipmap.ic_user_account, R.mipmap.ic_mail, R.mipmap.ic_people,
                R.mipmap.ic_offer, R.mipmap.ic_settings_2, R.mipmap.ic_logout)
        );
    }

    private void configureNavDrawerHeader() {
        View headerView = navigationView.getHeaderView(0);
        TextView headerEmail = headerView.findViewById(R.id.header_email);
        TextView headerFullName = headerView.findViewById(R.id.header_full_name);

        headerEmail.setText(signedUser.getEmail());
        headerFullName.setText(signedUser.getFullName());
    }

    private void readExtras() {
        Bundle extras = getIntent().getExtras();
        signedUser = extras.getParcelable(SIGNED_IN_USER);
        checkExtras();
        Log.i(TAG, "Hello: " + signedUser.getFullName());
    }

    private void checkExtras() {
        if (signedUser == null) {
            Intent intent = new Intent(this, SignInActivity_.class);
            intent.putExtra(STATUS_KEY, false);
            intent.putExtra(MESSAGE_KEY, getString(R.string.error_message_unknown));
            startActivity(intent);
        }
    }

    private void configureToolbar() {
        toolbar.setTitle(signedUser.getFullName());
    }

    private void configureNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener(
                new UserNavigationMenuListener()
        );

        addDrawerToggle();
    }

    private void addDrawerToggle() {
        drawerToggle = getDrawerToggle();
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @NonNull
    private ActionBarDrawerToggle getDrawerToggle() {
        return new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (drawerToggle != null) drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (drawerToggle != null) drawerToggle.syncState();
    }
}