package se.su.dsv.pvt.helloworldapp.model;

import android.view.View;
import android.widget.ImageView;

import se.su.dsv.pvt.helloworldapp.R;

/**
 * Static methods, used for the places where you need to set stars to a correct amount.
 * @author Niklas Edström
 */
public class RatingStars {
    public static void setRatingStars(double rating, View view, ImageView[] ratingStars) {
        for (ImageView iV : ratingStars) {
            ((ImageView) view.findViewById(iV.getId())).setImageResource(R.drawable.ic_star_empty);
        }
        showFromRating(rating, ratingStars);
    }

    public static void showFromRating(double rating, ImageView[] iV) {
        if (rating >= 0.25) {
            for (int i = 0; i <= 4; i++) {
                ImageView iT = iV[i];
                if (rating >= 0.25){
                    iT.setImageResource(R.drawable.ic_star_half);
                }
                if (rating >= 0.75) {
                    iT.setImageResource(R.drawable.ic_star_full);
                }

                rating = rating - 1.0;
            }
        }
    }

    public static void setListeners(ImageView[] imageViews, View.OnClickListener listener) {
        for (ImageView iV : imageViews) {
            iV.setOnClickListener(listener);
        }
    }
}
