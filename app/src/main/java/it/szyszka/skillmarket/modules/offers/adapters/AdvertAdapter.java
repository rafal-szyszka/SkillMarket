package it.szyszka.skillmarket.modules.offers.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.offers.model.Announcement;

/**
 * Created by rafal on 08.12.17.
 */

public class AdvertAdapter extends RecyclerView.Adapter<AdvertAdapter.AdvertHolder> {

    private List<Announcement> announcements;

    @Override
    public AdvertHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offers_offer_row_view, parent, false);

        return new AdvertHolder(view);
    }

    @Override
    public void onBindViewHolder(AdvertHolder holder, int position) {
        Announcement announcement = announcements.get(position);
        holder.ownerName.setText(announcement.getAdvertiser().getFullName());
        holder.ownerEmail.setText(announcement.getAdvertiser().getEmail());

        holder.title.setText(announcement.getAdvertisement().getTitle());
        holder.category.setText(announcement.getAdvertisement().getCategory());
        holder.description.setText(announcement.getAdvertisement().getShortDescription());
        holder.payment.setText(announcement.getAdvertisement().getPayment());
    }

    @Override
    public int getItemCount() {
        return announcements.size();
    }

    public class AdvertHolder extends RecyclerView.ViewHolder {

        TextView ownerName, ownerEmail;
        ImageView contact, rating, observe;
        TextView title, category, description, payment;

        public AdvertHolder(View itemView) {
            super(itemView);
            ownerName = itemView.findViewById(R.id.offer_item_owner);
            ownerEmail = itemView.findViewById(R.id.offer_item_owner_email);
            contact = itemView.findViewById(R.id.offer_item_contact);
            rating = itemView.findViewById(R.id.offer_item_rating);
            observe = itemView.findViewById(R.id.offer_item_observe);
            title = itemView.findViewById(R.id.offer_item_title);
            category = itemView.findViewById(R.id.offer_item_category);
            description = itemView.findViewById(R.id.offer_item_short_description);
            payment = itemView.findViewById(R.id.offer_item_payment);
        }
    }

    public AdvertAdapter(List<Announcement> announcements) {
        this.announcements = announcements;
    }
}
