package it.szyszka.skillmarket.modules.offers.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.api.APIConfig;
import it.szyszka.skillmarket.modules.offers.tasks.GetAllAdverts;
import it.szyszka.skillmarket.modules.user.model.Credentials;
import it.szyszka.skillmarket.modules.user.model.User;

/**
 * Created by rafal on 08.12.17.
 */

public class AdvertisementFragment extends Fragment {

    public static final String ARGS_USER = "user";

    private User signedInUser;

    private FloatingActionButton createNewAdvert;
    private RecyclerView recyclerView;

    public static AdvertisementFragment newInstance(User signedInUser) {

        AdvertisementFragment fragment = new AdvertisementFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_USER, signedInUser);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleArgumentsIfExists();
    }

    private void handleArgumentsIfExists() {
        Bundle bundle = getArguments();
        if(bundle != null) {
            signedInUser = bundle.getParcelable(ARGS_USER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offers_main_fragment, container, false);

        createNewAdvert = view.findViewById(R.id.offer_create_new);
        recyclerView = view.findViewById(R.id.offer_list);

        loadAdverts();
        initActionListeners();

        return view;
    }

    private void loadAdverts() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        downloadAdverts();
    }

    private void initActionListeners() {
        createNewAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragments, CreateOfferFragment.newInstance(signedInUser))
                        .commit();
            }
        });
    }

    private void downloadAdverts() {
        GetAllAdverts getAllAdverts = new GetAllAdverts(recyclerView);
        getAllAdverts.execute(
                APIConfig.getInstance().createAdvertApiClient()
                    .getAllAdverts(Credentials.getInstance().getBasicAuth())
        );
    }
}
