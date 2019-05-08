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
 * TODO:
 * - Decide how we will show the ratings via stars, can be done in two ways (i think):
 * either we have 10 or so icon-files, and choose the one with the correct number of stars for
 * each object; or we have five separate ImageViews, and fill them up separately.
 * - Fix the "visa mer"-button, add an android:onClick to it: what is it supposed to do?
 */

public class CustomMapMarker implements GoogleMap.InfoWindowAdapter {

    private View customView;
    private Context c; // Used to access the layoutinflater (can probably be done in a nicer way,
                        // but android be android...

    public CustomMapMarker(Context c) {
        this.c = c;
    }

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
        LayoutInflater layoutInflater = LayoutInflater.from(c);
        customView = layoutInflater.inflate(R.layout.custom_info_contents, null);
        ((ImageView) customView.findViewById(R.id.gym_icon)).setImageResource(R.drawable.ic_gym_popup);
        ((TextView) customView.findViewById(R.id.gym_name)).setText(m.getTitle());

        //kod som ska ersättas/tas bort/bli slutgiltig:
        ((ImageView) customView.findViewById(R.id.gym_ratings)).setImageResource(R.drawable.ic_back_button);
        ((TextView) customView.findViewById(R.id.gym_ratings_text)).setText("4,2");

        return customView;
    }

}
