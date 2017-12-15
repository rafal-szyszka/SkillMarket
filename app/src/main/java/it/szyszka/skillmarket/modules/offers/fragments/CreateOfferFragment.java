package it.szyszka.skillmarket.modules.offers.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.api.APIConfig;
import it.szyszka.skillmarket.modules.offers.model.Advertisement;
import it.szyszka.skillmarket.modules.offers.model.PreparedAdvertisement;
import it.szyszka.skillmarket.modules.offers.tasks.PlaceAdvertTask;
import it.szyszka.skillmarket.modules.user.model.Credentials;
import it.szyszka.skillmarket.modules.user.model.User;

/**
 * Created by rafal on 08.12.17.
 */

public class CreateOfferFragment extends Fragment {

    public static final String ARGS_USER = "user";
    private User signedInUser;

    private ArrayList<String> categories;

    private TextInputLayout title, shortDescription, longDescription, payment;
    private Spinner category;
    private FloatingActionButton save;

    public static CreateOfferFragment newInstance(User signedInUser) {
        CreateOfferFragment fragment = new CreateOfferFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_USER, signedInUser);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleArgumentsIfExists();
        prepareCategories();
    }

    private void prepareCategories() {
        categories = new ArrayList<>();
        categories.addAll(Arrays.asList(getResources().getStringArray(R.array.offer_categories)));
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
        View view = inflater.inflate(R.layout.offers_create_advert_fragment, container, false);

        title = view.findViewById(R.id.offer_new_advert_title);
        shortDescription = view.findViewById(R.id.offer_new_advert_short_description);
        longDescription = view.findViewById(R.id.offer_new_advert_long_description);
        category = view.findViewById(R.id.offer_new_advert_category);
        payment = view.findViewById(R.id.offer_new_advert_payment);
        save = view.findViewById(R.id.offer_new_advert_save);

        initSpinner();
        initActionListeners();

        return view;
    }

    private void initActionListeners() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreparedAdvertisement preparedAdvertisement = createPreparedAdvertisement();
                placeAdvertisement(preparedAdvertisement);
            }
        });
    }

    private void placeAdvertisement(PreparedAdvertisement preparedAdvertisement) {
        PlaceAdvertTask placeAdvertTask = new PlaceAdvertTask(
                getFragmentManager(), AdvertisementFragment.newInstance(signedInUser));
        placeAdvertTask.execute(
                APIConfig.getInstance()
                        .createAdvertApiClient()
                        .placeAdvertisement(
                                Credentials.getInstance().getBasicAuth(),
                                preparedAdvertisement
                        )
        );
    }

    @NonNull
    private PreparedAdvertisement createPreparedAdvertisement() {
        PreparedAdvertisement preparedAdvertisement = new PreparedAdvertisement();
        preparedAdvertisement.setAdvertisement(
                new Advertisement.Builder()
                        .setTitle(title.getEditText().getText().toString())
                        .setCategory(category.getSelectedItem().toString())
                        .setShortDescription(shortDescription.getEditText().getText().toString())
                        .setDetailDescription(longDescription.getEditText().getText().toString())
                        .setPayment(payment.getEditText().getText().toString())
                        .build()
        );
        preparedAdvertisement.setAdvertiserId(signedInUser.getId());
        preparedAdvertisement.setCharacter(Advertisement.Character.PAYMASTER);
        return preparedAdvertisement;
    }

    private void initSpinner() {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        getContext(), android.R.layout.simple_spinner_item, categories
                );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        category.setAdapter(adapter);
    }
}
