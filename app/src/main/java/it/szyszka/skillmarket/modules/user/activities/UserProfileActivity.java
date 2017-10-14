package it.szyszka.skillmarket.modules.user.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
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

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.user.fragments.TileMenuFragment;
import it.szyszka.skillmarket.modules.user.fragments.configuration.DefaultTileConfig;
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
        FragmentManager manager = getSupportFragmentManager();
        DefaultTileConfig defaultConfig = new DefaultTileConfig(
                getApplicationContext(),
                R.id.fragments,
                manager
        );

        TileMenuFragment tileMenu = TileMenuFragment.newInstance(defaultConfig);
        manager.beginTransaction()
                .replace(R.id.fragments, tileMenu)
                .commit();
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