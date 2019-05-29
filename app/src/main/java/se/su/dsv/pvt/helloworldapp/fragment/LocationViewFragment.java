package se.su.dsv.pvt.helloworldapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.activity.MainActivity;
import se.su.dsv.pvt.helloworldapp.model.Challenge;
import se.su.dsv.pvt.helloworldapp.model.OutdoorGym;
import se.su.dsv.pvt.helloworldapp.model.Place;
import se.su.dsv.pvt.helloworldapp.model.RatingStars;

public class LocationViewFragment extends Fragment {

    protected AlertDialog alertDialog;
    private int clickedStar;
    protected ImageView latestStar;
    ArrayList<Challenge> challenges;
    ListView lv;
    private static CustomAdapter adapter;
    private double avgRating;
    private MainActivity mainActivity;
    private Place clickedPlace;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        clickedPlace = mainActivity.getOpenThisPlaceFragment();
        avgRating = clickedPlace.getAverageRating();

        View view = inflater.inflate(R.layout.fragment_location_view, container, false);

        lv = view.findViewById(R.id.list);
        challenges = clickedPlace.getChallengeList();

        Date d = Calendar.getInstance().getTime();

        adapter  = new CustomAdapter (challenges, getActivity().getApplicationContext());

        lv.setAdapter(adapter);
        TextView title = (TextView) mainActivity.findViewById(R.id.main_title_text);
        title.setText(clickedPlace.getName());
        TextView textRating = (TextView) view.findViewById(R.id.location_view_gymrating);
        textRating.setText(String.valueOf(clickedPlace.getAverageRating()));
        if (clickedPlace instanceof OutdoorGym) {
            TextView description = (TextView) view.findViewById(R.id.gymview_description);
            if (((OutdoorGym) clickedPlace).getDescription() == null) {
                description.setText(R.string.no_descr_avail);
            }
            description.setText(((OutdoorGym) clickedPlace).getDescription());
        }
        else {
            TextView descr = (TextView) view.findViewById(R.id.gymview_description);
            descr.setText(getString(R.string.no_descr_avail));
        }

        Button rateGym = (Button) view.findViewById(R.id.rate_gym_popup_button);
        rateGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateGymPopupCreate(inflater);
            }
        });

         // I'm so sorry for this.
        ImageView iV1 = (ImageView) view.findViewById(R.id.gym_star_m1);
        ImageView iV2 = (ImageView) view.findViewById(R.id.gym_star_m2);
        ImageView iV3 = (ImageView) view.findViewById(R.id.gym_star_m3);
        ImageView iV4 = (ImageView) view.findViewById(R.id.gym_star_m4);
        ImageView iV5 = (ImageView) view.findViewById(R.id.gym_star_m5);
        ImageView[] imageViews = new ImageView[5];
        imageViews[0] = iV1;
        imageViews[1] = iV2;
        imageViews[2] = iV3;
        imageViews[3] = iV4;
        imageViews[4] = iV5;
        RatingStars.setRatingStars(clickedPlace.getAverageRating(), view, imageViews);

        return view;
    }
    /**
     * Creates and shows the popup for rating a gym.
     * @param inflater
     * @author Niklas Edström
     */
    private void rateGymPopupCreate(@NonNull LayoutInflater inflater) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.rank_gym_dialog);
        alertDialog = builder.create();
        alertDialog.show();
        Button cancelButton = (Button) alertDialog.findViewById(R.id.rankgym_button_cancel);
        cancelButton.setOnClickListener(new cancelButtonListener());
        Button confirmButton = (Button) alertDialog.findViewById(R.id.rankgym_button_ok);
        confirmButton.setOnClickListener(new confirmButtonListener());

        starButtonListener starbl = new starButtonListener();

        ImageView ib1 = (ImageView) alertDialog.findViewById(R.id.star1);
        ImageView ib2 = (ImageView) alertDialog.findViewById(R.id.star2);
        ImageView ib3 = (ImageView) alertDialog.findViewById(R.id.star3);
        ImageView ib4 = (ImageView) alertDialog.findViewById(R.id.star4);
        ImageView ib5 = (ImageView) alertDialog.findViewById(R.id.star5);
        ImageView[] imageViews = new ImageView[5];
        imageViews[0] = ib1;
        imageViews[1] = ib2;
        imageViews[2] = ib3;
        imageViews[3] = ib4;
        imageViews[4] = ib5;
        RatingStars.setRatingStars(avgRating, inflater.inflate(R.layout.rank_gym_dialog, null), imageViews);
        RatingStars.setListeners(imageViews, starbl);
    }

    /**
     * These listeners are used by the popup window.
     * @author Niklas Edström
     */
    class cancelButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            alertDialog.cancel();
        }
    }
    class confirmButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            /**
             * TODO:
             * Detta förutsätter att jag kan hantera ratings härifrån till databasen:
             * a) Läs av clickedStar,
             * b) Addera användarens betyg (clickedStar) till databasen.
             */
            mainActivity.rateGymCall(clickedPlace.getId(), 0, clickedStar); //TODO: HÅRDKODAT userID
            alertDialog.cancel();
        }
    }
    public class starButtonListener implements ImageView.OnClickListener {
        @Override
        /**
         * This is uglier than God knows what. It sets clickedStar to the, well, clicked star, using
         * the last character in the text id of all the stars.
         */
        public void onClick(View v) {
            if (latestStar != null) {
                latestStar.clearColorFilter();
            }
            String textID = v.getResources().getResourceName(v.getId());
            ImageView iV = (ImageView) v.findViewById(v.getId());
            clickedStar = Integer.parseInt(String.valueOf(textID.charAt(textID.length() - 1)));
            iV.setColorFilter(Color.RED);
            latestStar = iV;
        }
    }
}
