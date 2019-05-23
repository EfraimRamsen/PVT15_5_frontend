package se.su.dsv.pvt.helloworldapp.model;

import android.view.View;
import android.widget.ImageView;

import se.su.dsv.pvt.helloworldapp.R;

/**
 * Static methods, used for the places where you need to set stars to a correct amount.
 * @author Niklas Edström
 */
public class RatingStars {
    /**
     * Use this method if you have five stars that needs to be filled with a correct amount of stars.
     * Require that the stars are ImageView, though.
     * @param rating the rating to be shown.
     * @param view the view which has the stars (e.g. R.layout.xyz).
     * @param ratingStars an array which holds the five star-objects, IN ORDER!
     */
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

    /**
     * Sets one OnClickListener to several ImageViews at once.
     * @param imageViews list of ImageViews, in order!
     * @param listener listener to be added to them, declared elsewhere. 
     */
    public static void setListeners(ImageView[] imageViews, ImageView.OnClickListener listener) {
        for (ImageView iV : imageViews) {
            iV.setOnClickListener(listener);
        }
    }
}
