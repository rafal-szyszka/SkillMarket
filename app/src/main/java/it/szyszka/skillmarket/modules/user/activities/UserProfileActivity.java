package it.szyszka.skillmarket.modules.user.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.user.listeners.UserNavigationMenuListener;

/**
 * Created by rafal on 06.10.17.
 */
@EActivity(R.layout.user_profile_activity)
public class UserProfileActivity extends AppCompatActivity {

    private ActionBarDrawerToggle drawerToggle;

    @ViewById(R.id.user_toolbar)
    Toolbar toolbar;

    @ViewById(R.id.user_drawer_layout)
    DrawerLayout drawerLayout;

    @ViewById(R.id.user_nav_view)
    NavigationView navigationView;

    @AfterViews
    void initView(){
        configureNavigationDrawer();
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
        if(drawerToggle != null) drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(drawerToggle != null) drawerToggle.syncState();
    }
}
