package it.szyszka.skillmarket.modules.mails.api;

import java.util.List;

import it.szyszka.skillmarket.modules.mails.model.Mailbox;
import it.szyszka.skillmarket.modules.mails.model.PreparedMessage;
import it.szyszka.skillmarket.modules.mails.model.ProofOfPosting;
import it.szyszka.skillmarket.modules.mails.model.ReceivedMail;
import it.szyszka.skillmarket.modules.mails.tasks.SyncOutboxTask;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by rafal on 07.12.17.
 */

public interface MailService {

    String AUTH = "Authorization";

    @GET("messages/sync/inbox")
    Call<Mailbox> syncInbox(@Header(AUTH) String auth, @Query("userId") Long id);

    @GET("messages/sync/outbox")
    Call<Mailbox> syncOutbox(@Header(AUTH) String auth, @Query("userId") Long id);

    @POST("messages/action/send")
    Call<ProofOfPosting> send(@Header(AUTH) String auth, @Body PreparedMessage message);
}
