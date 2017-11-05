package it.szyszka.skillmarket.modules.user.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.androidannotations.annotations.EFragment;

import de.hdodenhof.circleimageview.CircleImageView;
import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.user.model.User;
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
        View parent = inflater.inflate(R.layout.user_account_fragment, container, false);

        ViewHolder holder = new ViewHolder(parent.findViewById(R.id.user_account_view));
        NestedScrollView scrollView = parent.findViewById(R.id.user_account_nested_scroll);
        scrollView.setFillViewport(true);
        holder.parentFab = parent.findViewById(R.id.user_account_back);
        holder.childFab.setVisibility(View.INVISIBLE);

        fillUserData(holder);
        setupViewPager(holder.pager, holder.tabs);

        return parent;
    }

    private void setupViewPager(ViewPager pager, TabLayout tabs) {
        PagerAdapter adapter = new PagerAdapter(getFragmentManager());
        pager.setAdapter(adapter);

        tabs.setupWithViewPager(pager);
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
        ViewPager pager;
        TabLayout tabs;

        ViewHolder(View parent) {
            childFab = parent.findViewById(R.id.user_account_invite);
            image = parent.findViewById(R.id.user_account_image);
            userName = parent.findViewById(R.id.user_account_name);
            rating = new Rating(parent.findViewById(R.id.user_account_rating));
            email = new BasicInfo(parent.findViewById(R.id.user_account_email));
            phone = new BasicInfo(parent.findViewById(R.id.user_account_phone));
            location = new BasicInfo(parent.findViewById(R.id.user_account_location));
            pager = parent.findViewById(R.id.user_account_view_pager);
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

    private class PagerAdapter extends FragmentPagerAdapter {

        String[] pageTitles = getResources().getStringArray(R.array.page_titles);

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.i(TAG, "getItem" + position);
            switch(position) {
                case 0: {
                    Log.i(TAG, "About fragment");
                    return new AboutFragment();
                }
                case 1: {
                    Log.i(TAG, "Friends fragment");
                    return new FriendsFragment();
                }
                case 2: {
                    Log.i(TAG, "Offers fragment");
                    return new AboutFragment();
                }
                default:
                    throw new IllegalStateException("No fragment on index " + position);
            }
        }

        @Override
        public int getCount() {
            return pageTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.i(TAG, "getPageTitle");
            return pageTitles[position];
        }
    }

}