package it.szyszka.skillmarket.modules.user.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.user.model.User;

/**
 * Created by rafal on 26.10.17.
 */

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder>{

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View friendItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_friend_list_item_view, parent, false);

        return new ViewHolder(friendItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User friend = friends.get(position);
        holder.fullName.setText(friend.getFullName());
        holder.email.setText(friend.getEmail());
        holder.userIcon.setImageResource(R.drawable.ic_person);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fullName, email;
        CircleImageView userIcon;
        ImageView sendMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.user_record_full_name);
            email = itemView.findViewById(R.id.user_record_email);
            userIcon = itemView.findViewById(R.id.user_record_icon);
            sendMessage = itemView.findViewById(R.id.user_record_send_message);
        }
    }

    private List<User> friends;

    public FriendsAdapter(List<User> friends) {
        this.friends = friends;
    }
}