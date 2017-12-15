package it.szyszka.skillmarket.modules.user.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.api.APIConfig;
import it.szyszka.skillmarket.modules.user.adapters.FriendsAdapter;
import it.szyszka.skillmarket.modules.user.model.Credentials;
import it.szyszka.skillmarket.modules.user.model.User;
import it.szyszka.skillmarket.modules.user.tasks.GetFriendsTask;
import it.szyszka.skillmarket.modules.user.views.BasicInfo;
import it.szyszka.skillmarket.modules.user.views.Rating;

/**
 * Created by rafal on 12.10.17.
 */
@EFragment(R.layout.user_account_fragment)
public class UserAccountFragment extends Fragment {

    public static final String TAG = UserAccountFragment.class.getSimpleName();
    public static final String DISPLAYED_USER = "displayed_user";

    private User displayedUser;
    private List<User> userFriends;

    public static UserAccountFragment newInstance(User user) {
        UserAccountFragment fragment = new UserAccountFragment();
        Bundle extras = new Bundle();
        extras.putParcelable(DISPLAYED_USER, user);

        fragment.setArguments(extras);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(argumentsExists()) {
            handleArguments();
        }
    }

    private void handleArguments() {
        displayedUser = getArguments()
                            .getParcelable(DISPLAYED_USER);
        Log.i(TAG, "Loaded User " + displayedUser.getFullName());
    }

    private boolean argumentsExists() {
        return getArguments() != null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View parent = inflater.inflate(R.layout.user_account_fragment, container, false);

        ViewHolder holder = new ViewHolder(parent.findViewById(R.id.user_account_view));
        NestedScrollView scrollView = parent.findViewById(R.id.user_account_nested_scroll);
        scrollView.setFillViewport(true);
        holder.parentFab = parent.findViewById(R.id.user_account_back);
        holder.childFab.setVisibility(View.INVISIBLE);

        fillUserData(holder);

        setupRecyclerView(holder.recycler);

        return parent;
    }

    public void downloadAndFillFriends(RecyclerView recyclerView) {
        Log.i(TAG, "onActivityCreated");
        GetFriendsTask getFriendsTask = new GetFriendsTask(recyclerView);
        getFriendsTask.execute(
                APIConfig.getInstance().createUserApiClient()
                        .getFriends(
                                Credentials.getInstance().getBasicAuth(),
                                displayedUser.getEmail()
                        )
        );
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        Log.i(TAG, "setupRecyclerView");
        userFriends = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        downloadAndFillFriends(recyclerView);
    }

    private void fillUserData(ViewHolder holder) {
        holder.userName.setText(displayedUser.getFullName());
        holder.setEmailView(displayedUser.getEmail());
        holder.setPhoneView(displayedUser.getPhoneNumber());
        holder.setLocationView(displayedUser.getCity());
    }

    private class ViewHolder {
        FloatingActionButton childFab;
        FloatingActionButton parentFab;
        CircleImageView image;
        TextView userName;
        Rating rating;
        BasicInfo email;
        BasicInfo phone;
        BasicInfo location;
        RecyclerView recycler;
        TabLayout tabs;

        ViewHolder(View parent) {
            childFab = parent.findViewById(R.id.user_account_invite);
            image = parent.findViewById(R.id.user_account_image);
            userName = parent.findViewById(R.id.user_account_name);
            rating = new Rating(parent.findViewById(R.id.user_account_rating));
            email = new BasicInfo(parent.findViewById(R.id.user_account_email));
            phone = new BasicInfo(parent.findViewById(R.id.user_account_phone));
            location = new BasicInfo(parent.findViewById(R.id.user_account_location));
            recycler = parent.findViewById(R.id.user_account_recycler_view);
            tabs = parent.findViewById(R.id.user_account_tabs);
        }

        void setEmailView(String email) {
            this.email.setText(email);
        }

        void setPhoneView(String phoneView) {
            phone.setIcon(R.drawable.ic_phone);
            phone.setText(phoneView);
        }

        void setLocationView(String city) {
            location.setIcon(R.drawable.ic_location);
            location.setText(city);
        }
    }

}