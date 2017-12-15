package it.szyszka.skillmarket.modules.user.fragments.configuration;

import android.content.Context;
import android.os.Parcel;
import android.support.v4.app.FragmentManager;
import android.view.View;

import it.szyszka.skillmarket.modules.application.fragments.AppSettingsFragment_;
import it.szyszka.skillmarket.modules.mails.fragments.MailsFragment;
import it.szyszka.skillmarket.modules.offers.fragments.AdvertisementFragment;
import it.szyszka.skillmarket.modules.user.activities.SignInActivity_;
import it.szyszka.skillmarket.modules.user.fragments.PeopleFragment_;
import it.szyszka.skillmarket.modules.user.fragments.UserAccountFragment;
import it.szyszka.skillmarket.modules.user.listeners.DefaultParcelableClickListener;
import it.szyszka.skillmarket.modules.user.listeners.ParcelableClickListener;
import it.szyszka.skillmarket.modules.user.model.Credentials;
import it.szyszka.skillmarket.modules.user.model.User;

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

    public ParcelableClickListener instantiateClickListener(Integer tileNumber, User displayedUser) {
        switch (tileNumber) {
            case ACCOUNT: {
                return new DefaultParcelableClickListener(
                        UserAccountFragment.newInstance(displayedUser),
                        fragmentManager,
                        containerId
                );
            }
            case MAILS: {
                return new DefaultParcelableClickListener(
                        MailsFragment.newInstance(displayedUser),
                        fragmentManager,
                        containerId
                );
            }
            case PEOPLE: {
                return new DefaultParcelableClickListener(
                        new PeopleFragment_(),
                        fragmentManager,
                        containerId
                );
            }
            case ANNOUNCEMENTS: {
                return new DefaultParcelableClickListener(
                        AdvertisementFragment.newInstance(displayedUser),
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
