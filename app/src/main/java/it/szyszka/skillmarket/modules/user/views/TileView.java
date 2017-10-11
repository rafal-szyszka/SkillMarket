package it.szyszka.skillmarket.modules.user.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import it.szyszka.skillmarket.R;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by rafal on 11.10.17.
 */
public class TileView {

    @Getter @Setter private ImageView icon;
    @Getter @Setter private TextView label;

    public TileView(View tile) {
        icon = tile.findViewById(R.id.tile_image);
        label = tile.findViewById(R.id.tile_text);
    }

    public void replaceIconImageWith(int imageResource) {
        icon.setImageResource(imageResource);
    }

    public void replaceLabelTextWith(String text) {
        label.setText(text);
    }

}
