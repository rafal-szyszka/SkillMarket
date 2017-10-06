package it.szyszka.skillmarket.modules.user.listeners;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import it.szyszka.skillmarket.R;

/**
 * Created by rafal on 06.10.17.
 */

public class UserNavigationMenuListener implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        handleManageAccountSubMenu(item.getItemId());
        handleExploreSubMenu(item.getItemId());
        handleManagePeopleSubMenu(item.getItemId());

        handleOptions(item.getItemId());

        return false;
    }

    private void handleOptions(int itemId) {
        switch (itemId) {
            case R.id.nav_maintain_announcements: {
                // TODO: 06.10.17 show maintain announcements fragment
                break;
            }
            default: break;
        }
    }

    private void handleManageAccountSubMenu(int itemId) {
        switch (itemId) {
            case R.id.nav_change_password: {
                // TODO: 06.10.17 show change pass fragment
                break;
            }
            case R.id.nav_change_email: {
                // TODO: 06.10.17 show change email fragment
                break;
            }
            case R.id.nav_change_address: {
                // TODO: 06.10.17 show change address fragment
                break;
            }
            default: break;
        }
    }

    private void handleExploreSubMenu(int itemId) {
        switch (itemId) {
            case R.id.nav_explore_announcements: {
                // TODO: 06.10.17 show announcements fragment
                break;
            }
            case R.id.nav_explore_people: {
                // TODO: 06.10.17 show people fragment
                break;
            }
            default: break;
        }
    }

    private void handleManagePeopleSubMenu(int itemId) {
        switch (itemId) {
            case R.id.nav_maintain_friends: {
                // TODO: 06.10.17 show friends fragment
                break;
            }
            case R.id.nav_maintain_trusted: {
                // TODO: 06.10.17 show trusted fragment
                break;
            }
            default: break;
        }
    }

}
