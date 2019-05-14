package se.su.dsv.pvt.helloworldapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import se.su.dsv.pvt.helloworldapp.R;

/**
 * TODO:
 * - Fix the "visa mer"-button, add an android:onClick to it: what is it supposed to do?
 */

public class CustomMapMarker implements GoogleMap.InfoWindowAdapter {

    private View customView;
    private Context c; // Used to access the layoutinflater (can probably be done in a nicer way,
                        // but android be android...


    public CustomMapMarker(Context c) {
        this.c = c;
    }

    // This needs to return null for the program to use getInfoContents()
    @Override
    public View getInfoWindow(Marker m) {
        return null;
    }

    /**
     * här skriver man koden/skapar viewen som ska visas i kartan. ATM är allt hårdkodat.
     * @param m is the marker which contains the marker-info.
     * @return a View which has the custom needed.
     */
    @Override
    public View getInfoContents(Marker m) {
        // standardkod som kan återanvändas i framtiden:
        /**
         * få tag på information från markern här någonstans: String name, double betyg/stjärnor.
         */
        LayoutInflater layoutInflater = LayoutInflater.from(c);
        customView = layoutInflater.inflate(R.layout.custom_info_contents, null);
        String name = m.getTitle();
        double rating = 4.5;

        ((ImageView) customView.findViewById(R.id.gym_icon)).setImageResource(R.drawable.ic_gym_popup);
        ((TextView) customView.findViewById(R.id.gym_name)).setText(m.getTitle());
        setRatingStars(rating);

        ((TextView) customView.findViewById(R.id.gym_ratings_text)).setText(String.valueOf(rating));
        ((TextView) customView.findViewById(R.id.gym_challenges)).setText(new String("12 inmaningar"));
        Button button = (Button)customView.findViewById(R.id.gym_button_link);
        button.setTag(new String("bajs"));
        return customView;
    }

    private void setRatingStars(double rating) {

        ImageView[] ratingStars = new ImageView[5];
        ratingStars[0] = (ImageView) customView.findViewById(R.id.gym_star_1);
        ratingStars[1] = (ImageView) customView.findViewById(R.id.gym_star_2);
        ratingStars[2] = (ImageView) customView.findViewById(R.id.gym_star_3);
        ratingStars[3] = (ImageView) customView.findViewById(R.id.gym_star_4);
        ratingStars[4] = (ImageView) customView.findViewById(R.id.gym_star_5);

        ((ImageView) customView.findViewById(R.id.gym_star_1)).setImageResource(R.drawable.ic_star_empty);
        ((ImageView) customView.findViewById(R.id.gym_star_2)).setImageResource(R.drawable.ic_star_empty);
        ((ImageView) customView.findViewById(R.id.gym_star_3)).setImageResource(R.drawable.ic_star_empty);
        ((ImageView) customView.findViewById(R.id.gym_star_4)).setImageResource(R.drawable.ic_star_empty);
        ((ImageView) customView.findViewById(R.id.gym_star_5)).setImageResource(R.drawable.ic_star_empty);
        ratingStars[0] = (ImageView) customView.findViewById(R.id.gym_star_1);
        ratingStars[1] = (ImageView) customView.findViewById(R.id.gym_star_2);
        ratingStars[2] = (ImageView) customView.findViewById(R.id.gym_star_3);
        ratingStars[3] = (ImageView) customView.findViewById(R.id.gym_star_4);
        ratingStars[4] = (ImageView) customView.findViewById(R.id.gym_star_5);

        if (rating >= 0.25) {
            for (int i = 0; i <= 4; i++) {
                ImageView iT = ratingStars[i];
                System.out.println(i);
                System.out.println(rating);
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
}
