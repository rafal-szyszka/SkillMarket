package it.szyszka.skillmarket.modules.user.fragments.configuration;

import android.content.Context;
import android.os.Parcel;
import android.support.v4.app.FragmentManager;

import it.szyszka.skillmarket.modules.user.model.User;

public class DefaultTileConfigBuilder {
    private Context context;
    private Integer containerId;
    private FragmentManager fragmentManager;
    private User signedInUser;
    private Parcel parcel;

    public DefaultTileConfigBuilder setContext(Context context) {
        this.context = context;
        return this;
    }

    public DefaultTileConfigBuilder setContainerId(Integer containerId) {
        this.containerId = containerId;
        return this;
    }

    public DefaultTileConfigBuilder setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        return this;
    }

    public DefaultTileConfigBuilder setSignedInUser(User signedInUser) {
        this.signedInUser = signedInUser;
        return this;
    }

    public DefaultTileConfigBuilder setParcel(Parcel parcel) {
        this.parcel = parcel;
        return this;
    }

    public DefaultTileConfig build() {
        return new DefaultTileConfig(context, containerId, fragmentManager, signedInUser);
    }
}