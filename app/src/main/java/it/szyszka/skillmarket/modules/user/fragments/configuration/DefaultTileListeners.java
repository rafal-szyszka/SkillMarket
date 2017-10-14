package it.szyszka.skillmarket.modules.user.fragments.configuration;

import android.content.Context;
import android.os.Parcel;
import android.support.v4.app.FragmentManager;
import android.view.View;

import it.szyszka.skillmarket.modules.announcements.fragments.OffersTile_;
import it.szyszka.skillmarket.modules.application.fragments.AppSettingsFragment_;
import it.szyszka.skillmarket.modules.user.activities.SignInActivity_;
import it.szyszka.skillmarket.modules.user.fragments.MailsTile_;
import it.szyszka.skillmarket.modules.user.fragments.PeopleTile_;
import it.szyszka.skillmarket.modules.user.fragments.UserAccountTile_;
import it.szyszka.skillmarket.modules.user.listeners.DefaultParcelableClickListener;
import it.szyszka.skillmarket.modules.user.listeners.ParcelableClickListener;
import it.szyszka.skillmarket.modules.user.model.Credentials;

/**
 * Created by rafal on 14.10.17.
 */

public class DefaultTileListeners {

    static final int ACCOUNT = 0;
    static final int MAILS = 1;
    static final int PEOPLE = 2;
    static final int ANNOUNCEMENTS = 3;
    static final int SETTINGS = 4;
    static final int LOGOUT = 5;

    private final Integer containerId;
    private final FragmentManager fragmentManager;
    private final Context context;

    public DefaultTileListeners(Integer containerId, FragmentManager fragmentManager, Context context) {
        this.containerId = containerId;
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    public ParcelableClickListener instantiateClickListener(Integer tileNumber) {
        switch (tileNumber) {
            case ACCOUNT: {
                return new DefaultParcelableClickListener(
                        new UserAccountTile_(),
                        fragmentManager,
                        containerId
                );
            }
            case MAILS: {
                return new DefaultParcelableClickListener(
                        new MailsTile_(),
                        fragmentManager,
                        containerId
                );
            }
            case PEOPLE: {
                return new DefaultParcelableClickListener(
                        new PeopleTile_(),
                        fragmentManager,
                        containerId
                );
            }
            case ANNOUNCEMENTS: {
                return new DefaultParcelableClickListener(
                        new OffersTile_(),
                        fragmentManager,
                        containerId
                );
            }
            case SETTINGS: {
                return new DefaultParcelableClickListener(
                        new AppSettingsFragment_(),
                        fragmentManager,
                        containerId
                );
            }
            case LOGOUT: {
                return new ParcelableClickListener() {

                    @Override
                    public void writeToParcel(Parcel parcel, int i) {
                        parcel.writeInt(0);
                    }

                    @Override
                    public void onClick(View view) {
                        Credentials.clear();
                        SignInActivity_.intent(context).start();
                    }
                };
            }
            default: {
                return null;
            }
        }
    }

}
