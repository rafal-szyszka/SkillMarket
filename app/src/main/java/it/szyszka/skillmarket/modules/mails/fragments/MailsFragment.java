package it.szyszka.skillmarket.modules.mails.fragments;

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
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.api.APIConfig;
import it.szyszka.skillmarket.modules.mails.adapters.MailViewAdapter;
import it.szyszka.skillmarket.modules.mails.model.Mail;
import it.szyszka.skillmarket.modules.mails.tasks.SyncInboxTask;
import it.szyszka.skillmarket.modules.mails.tasks.SyncOutboxTask;
import it.szyszka.skillmarket.modules.user.model.Credentials;
import it.szyszka.skillmarket.modules.user.model.User;

/**
 * Created by rafal on 07.12.17.
 */
public class MailsFragment extends Fragment {

    public static final String ARGS_USER = "user";

    private User signedInUser;

    private List<Mail> mails;
    private MailViewAdapter mailViewAdapter;

    private RecyclerView recyclerView;
    private Button syncInbox, syncOutbox;
    private FloatingActionButton createMail;

    public static MailsFragment newInstance(User signedInUser) {
        MailsFragment mailsFragment = new MailsFragment();

        Bundle arguments = new Bundle();
        arguments.putParcelable(ARGS_USER, signedInUser);

        mailsFragment.setArguments(arguments);

        return mailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        handleArgumentsIfExists(arguments);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mails_main_fragment, container, false);
        recyclerView = view.findViewById(R.id.mails_list);

        syncInbox = view.findViewById(R.id.mails_sync_inbox);
        syncOutbox = view.findViewById(R.id.mails_sync_outbox);
        createMail = view.findViewById(R.id.mails_create_mail);
        loadInbox();

        initClickListeners();

        return view;
    }

    private void initClickListeners() {
        syncInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                syncInbox.setBackgroundResource(R.color.colorAccent);
                syncOutbox.setBackgroundResource(R.color.colorPrimaryDark);
                downloadInbox();
            }
        });

        syncOutbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                syncInbox.setBackgroundResource(R.color.colorPrimaryDark);
                syncOutbox.setBackgroundResource(R.color.colorAccent);
                downloadOutbox();
            }
        });

        createMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragments, CreateMailFragment.newInstance(signedInUser))
                        .commit();
            }
        });
    }

    private void loadInbox() {
        syncInbox.setBackgroundResource(R.color.colorAccent);
        syncOutbox.setBackgroundResource(R.color.colorPrimary);
        mails = new ArrayList<>();
        mailViewAdapter = new MailViewAdapter(mails);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mailViewAdapter);

        downloadInbox();
    }

    private void downloadInbox() {
        SyncInboxTask syncInboxTask = new SyncInboxTask(recyclerView);
        syncInboxTask.execute(
                APIConfig.getInstance()
                    .createMailApiClient()
                        .syncInbox(
                                Credentials.getInstance().getBasicAuth(),
                                signedInUser.getId()
                        )
        );
    }

    private void downloadOutbox() {
        SyncOutboxTask syncOutboxTask = new SyncOutboxTask(recyclerView);
        syncOutboxTask.setAlertDialog(new MaterialDialog.Builder(getContext()).build());
        syncOutboxTask.execute(
                APIConfig.getInstance()
                    .createMailApiClient()
                        .syncOutbox(
                                Credentials.getInstance().getBasicAuth(),
                                signedInUser.getId()
                        )
        );
    }

    private void handleArgumentsIfExists(Bundle arguments) {
        if(arguments != null) {
            signedInUser = arguments.getParcelable(ARGS_USER);
        }
    }
}
