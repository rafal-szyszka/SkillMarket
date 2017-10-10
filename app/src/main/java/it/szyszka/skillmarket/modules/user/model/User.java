package it.szyszka.skillmarket.modules.user.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by rafal on 30.09.17.
 */
@NoArgsConstructor
public class User implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public User createFromParcel(Parcel parcel) {
            return new User(parcel);
        }

        @Override
        public User[] newArray(int i) {
            return new User[i];
        }
    };
    @Getter @Setter private String nickname;
    @Getter @Setter private String email;
    @Getter @Setter private String fullName;
    @Getter @Setter private String password;

    @Getter @Setter private String city;
    @Getter @Setter private String mailingAddress;
    @Getter @Setter private String phoneNumber;
    @Getter @Setter private String about;
    @Getter @Setter private String preferredContact;

    @Getter @Setter private Set<User> friends;
    @Getter @Setter private Set<User> trusted;

    public User(Parcel parcel) {
        nickname = parcel.readString();
        email = parcel.readString();
        fullName = parcel.readString();
        password = parcel.readString();
        city = parcel.readString();
        mailingAddress = parcel.readString();
        phoneNumber = parcel.readString();
        about = parcel.readString();
        preferredContact = parcel.readString();
        friends = new HashSet<>();
        trusted = new HashSet<>();
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nickname);
        parcel.writeString(email);
        parcel.writeString(fullName);
        parcel.writeString(password);
        parcel.writeString(city);
        parcel.writeString(mailingAddress);
        parcel.writeString(phoneNumber);
        parcel.writeString(about);
        parcel.writeString(preferredContact);
    }
}
