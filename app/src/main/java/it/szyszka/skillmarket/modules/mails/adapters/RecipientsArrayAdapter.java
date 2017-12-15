package it.szyszka.skillmarket.modules.mails.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.user.model.User;

/**
 * Created by rafal on 08.12.17.
 */

public class RecipientsArrayAdapter extends ArrayAdapter<User> {

    public RecipientsArrayAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
    }

    public View getCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.utils_user_spinner_view, parent, false);

        TextView id = view.findViewById(R.id.spinner_user_id);
        TextView name = view.findViewById(R.id.spinner_user_full_name);

        id.setText(getItem(position).getId().toString());
        name.setText(getItem(position).getFullName());

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
}
