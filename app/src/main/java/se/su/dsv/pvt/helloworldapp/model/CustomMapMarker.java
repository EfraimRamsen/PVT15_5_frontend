package se.su.dsv.pvt.helloworldapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import se.su.dsv.pvt.helloworldapp.R;

public class CustomMapMarker implements GoogleMap.InfoWindowAdapter {

    private View window;
    private View contents;
    private Context c;

    public CustomMapMarker(Context c) {
        this.c = c;
    }

    @Override
    public View getInfoWindow(Marker m) {
        return null;
    }

    /**
     * här skriver man koden/skapar viewen som ska visas i kartan. ATM är allt hårdkodat.
     * @param m is unused?
     * @return a View which has the custom needed.
     */
    @Override
    public View getInfoContents(Marker m) {
        LayoutInflater layoutInflater = LayoutInflater.from(c);
        View customView = layoutInflater.inflate(R.layout.custom_info_contents, null);
        ((ImageView) customView.findViewById(R.id.gym_icon)).setImageResource(R.drawable.ic_gym_popup);
        MarkerInfo markerInfo = new MarkerInfo();
        ((TextView) customView.findViewById(R.id.gym_name)).setText(markerInfo.getName());
        ((ImageView) customView.findViewById(R.id.gym_ratings)).setImageResource(R.drawable.ic_back_button);
        ((TextView) customView.findViewById(R.id.gym_ratings_text)).setText("4,2");

        return customView;
    }

}

/**
 * Class only used to read and store a map-objects information. May be moved or deleted, probably
 * the latter.
 */
class MarkerInfo {
    private String name;
    private double rating;
    private int challenges;

    public MarkerInfo() {
        this.name = "hejsans utegym";
        this.rating = 3.5;
        this.challenges = 8;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public int getChallenges() {
        return challenges;
    }
    public void setChallenges(int challenges) {
        this.challenges = challenges;
    }

}
