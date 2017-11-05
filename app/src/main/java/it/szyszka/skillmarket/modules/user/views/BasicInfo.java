package it.szyszka.skillmarket.modules.user.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import it.szyszka.skillmarket.R;

/**
 * Created by rafal on 05.11.17.
 */

public class BasicInfo {

    private ImageView icon;
    private TextView text;

    public BasicInfo(View parent) {
        icon = parent.findViewById(R.id.user_info_img);
        text = parent.findViewById(R.id.user_info_text);
    }

    public void setIcon(Integer drawableResource) {
        icon.setImageResource(drawableResource);
    }

    public void setText(String text) {
        this.text.setText(text);
    }
}
