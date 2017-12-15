package it.szyszka.skillmarket.modules.mails.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.api.APIConfig;
import it.szyszka.skillmarket.modules.mails.model.Message;
import it.szyszka.skillmarket.modules.mails.model.PreparedMessage;
import it.szyszka.skillmarket.modules.mails.tasks.GetAllRecipientsTask;
import it.szyszka.skillmarket.modules.mails.tasks.SendMessageTask;
import it.szyszka.skillmarket.modules.user.model.Credentials;
import it.szyszka.skillmarket.modules.user.model.User;

/**
 * Created by rafal on 08.12.17.
 */

public class CreateMailFragment extends Fragment {

    private static final String TAG = CreateMailFragment.class.getSimpleName();

    private Spinner recipients;
    private TextInputLayout title;
    private TextInputLayout content;
    private FloatingActionButton send;

    private User signedInUser;

    public static CreateMailFragment newInstance(User signedInUser) {

        CreateMailFragment mailFragment = new CreateMailFragment();

        Bundle arguments = new Bundle();
        arguments.putParcelable(MailsFragment.ARGS_USER, signedInUser);

        mailFragment.setArguments(arguments);

        return mailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        handleArgumentsIfExists(arguments);
    }

    private void handleArgumentsIfExists(Bundle arguments) {
        if(arguments != null){
            signedInUser = arguments.getParcelable(MailsFragment.ARGS_USER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mails_create_mail_fragment, container, false);

        recipients = view.findViewById(R.id.mails_create_recipient);
        title = view.findViewById(R.id.mails_create_title);
        content = view.findViewById(R.id.mails_create_content);
        send = view.findViewById(R.id.mails_create_send);
        
        initRecipients();
        initActionListeners();

        return view;
    }

    private void initRecipients() {
        GetAllRecipientsTask getAllRecipientsTask = new GetAllRecipientsTask(recipients, getContext());
        getAllRecipientsTask.execute(
                APIConfig.getInstance().createUserApiClient()
                    .getAll(Credentials.getInstance().getBasicAuth())
        );
    }

    private void initActionListeners() {
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User selected = (User) recipients.getSelectedItem();
                Message message = new Message();
                message.setTitle(title.getEditText().getText().toString());
                message.setContent(content.getEditText().getText().toString());

                PreparedMessage preparedMessage = new PreparedMessage();
                preparedMessage.setSenderID(signedInUser.getId());
                preparedMessage.setRecipientID(selected.getId());
                preparedMessage.setMessage(message);

                SendMessageTask sendMessageTask = new SendMessageTask(getContext(), getFragmentManager(), signedInUser);
                sendMessageTask.execute(
                        APIConfig.getInstance().createMailApiClient()
                            .send(
                                    Credentials.getInstance().getBasicAuth(),
                                    preparedMessage
                            )
                );
            }
        });
    }
}
