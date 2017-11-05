package it.szyszka.skillmarket.modules.user.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.user.adapters.FriendsAdapter;
import it.szyszka.skillmarket.modules.user.model.User;

/**
 * Created by rafal on 26.10.17.
 */

public class FriendsFragment extends Fragment {

    public static final String FRIENDS_LIST_EXTRA = "friends_list";

    private ArrayList<User> friends;
    private RecyclerView recyclerView;

    public static FriendsFragment newInstance(ArrayList<User> users) {
        FriendsFragment fragment = new FriendsFragment();

        Bundle extras = new Bundle();
        extras.putParcelableArrayList(FRIENDS_LIST_EXTRA, users);

        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if(arguments != null) {
            handleArguments(arguments);
        }
    }

    private void handleArguments(Bundle arguments) {
        friends = arguments.getParcelableArrayList(FRIENDS_LIST_EXTRA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View friendsView = inflater.inflate(R.layout.user_friends_fragment, container, false);

        FriendsAdapter friendsListAdapter = new FriendsAdapter(friends);
        recyclerView = friendsView.findViewById(R.id.user_friends);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(friendsListAdapter);

        return friendsView;
    }
}
