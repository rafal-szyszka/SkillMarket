package it.szyszka.skillmarket.modules.mails.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.mails.model.Mail;

/**
 * Created by rafal on 07.12.17.
 */

public class MailViewAdapter extends RecyclerView.Adapter<MailViewAdapter.MailHolder> {

    private List<Mail> mails;

    public MailViewAdapter(List<Mail> mails) {
        this.mails = mails;
    }

    @Override
    public MailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mails_mail_row_view, parent, false);

        return new MailHolder(view);
    }

    @Override
    public void onBindViewHolder(MailHolder holder, int position) {
        Mail mail = mails.get(position);
        holder.mailOwner.setText(mail.getFullName());
        holder.title.setText(mail.getMessage().getTitle());
        holder.content.setText(mail.getMessage().getContent());
        changeBackgroundColorForOddRecords(holder, position);
    }

    private void changeBackgroundColorForOddRecords(MailHolder holder, int position) {
        if(position % 2 == 1) {
            holder.background.setBackgroundResource(R.color.colorLightGray);
        }
    }

    @Override
    public int getItemCount() {
        return mails.size();
    }

    public class MailHolder extends RecyclerView.ViewHolder {

        TextView mailOwner, title, content;
        LinearLayout background;

        public MailHolder(View itemView) {
            super(itemView);
            mailOwner = itemView.findViewById(R.id.mails_owner_name);
            title = itemView.findViewById(R.id.mails_title);
            content = itemView.findViewById(R.id.mails_content);
            background = itemView.findViewById(R.id.mails_row_background);
        }
    }

}
