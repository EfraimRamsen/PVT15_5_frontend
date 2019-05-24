package se.su.dsv.pvt.helloworldapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import se.su.dsv.pvt.helloworldapp.R;


/**
 * Preferably, don't touch this! It's ugly.
 * TODO: fix soon, when we've got rating added to gyms.
 * @author Niklas Edström
 *
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

    @Override
    public View getInfoContents(Marker m) {

        LayoutInflater layoutInflater = LayoutInflater.from(c);
        customView = layoutInflater.inflate(R.layout.custom_info_contents, null);
        Place place = (Place) m.getTag();

        ((ImageView) customView.findViewById(R.id.gym_icon)).setImageResource(R.drawable.ic_gym_popup);
        ((TextView) customView.findViewById(R.id.gym_name)).setText(place.getName());

        ((ImageView) customView.findViewById(R.id.gym_icon)).setImageResource(R.drawable.ic_gym_popup);
        setRatingStars(2.6); //HÅRDKODAT!!!
        ((TextView) customView.findViewById(R.id.gym_ratings_text)).setText(String.valueOf(4)); // TODO: HÅRDKODAT!!!

        ((TextView) customView.findViewById(R.id.gym_challenges)).setText(place.getChallengeList().size() + " utmaningar"); //Oerhört fult, but works.

        return customView;
    }

    private void setRatingStars(double rating) {
        ImageView[] ratingStars = new ImageView[5];
        ratingStars[0] = (ImageView) customView.findViewById(R.id.gym_star_1);
        ratingStars[1] = (ImageView) customView.findViewById(R.id.gym_star_2);
        ratingStars[2] = (ImageView) customView.findViewById(R.id.gym_star_3);
        ratingStars[3] = (ImageView) customView.findViewById(R.id.gym_star_4);
        ratingStars[4] = (ImageView) customView.findViewById(R.id.gym_star_5);
        RatingStars.setRatingStars(rating, customView, ratingStars);
    }
}
