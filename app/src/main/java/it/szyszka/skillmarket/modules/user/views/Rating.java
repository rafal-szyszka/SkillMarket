package it.szyszka.skillmarket.modules.user.views;

import android.view.View;
import android.widget.ImageView;

import it.szyszka.skillmarket.R;

/**
 * Created by rafal on 05.11.17.
 */

public class Rating {

    private static final int FULL_STAR = R.drawable.ic_filled_star;
    private static final int HALF_STAR = R.drawable.ic_half_star;
    private static final int EMPTY_STAR = R.drawable.ic_no_star;

    private ImageView[] stars;

    public Rating(View parent) {
        initView(parent);
    }

    private void initView(View parent) {
        stars = new ImageView[5];
        stars[0] = parent.findViewById(R.id.rating_first_star);
        stars[1] = parent.findViewById(R.id.rating_second_star);
        stars[2] = parent.findViewById(R.id.rating_third_star);
        stars[3] = parent.findViewById(R.id.rating_fourth_star);
        stars[4] = parent.findViewById(R.id.rating_fifth_star);

        for(ImageView star : stars) {
            star.setImageResource(EMPTY_STAR);
        }
    }

    public void setRating(Integer fullStars, Boolean setHalfStar) {
        for (int i = 0; i < fullStars; i++) {
            stars[i].setImageResource(FULL_STAR);
        }
        if(setHalfStar && fullStars < 5) {
            stars[fullStars].setImageResource(HALF_STAR);
        }
    }

}
