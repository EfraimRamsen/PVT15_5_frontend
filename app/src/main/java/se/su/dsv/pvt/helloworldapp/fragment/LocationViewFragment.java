package se.su.dsv.pvt.helloworldapp.fragment;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.io.IOException;
import java.util.*;

import retrofit2.Call;
import retrofit2.Response;
import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.activity.MainActivity;
import se.su.dsv.pvt.helloworldapp.model.*;

public class LocationViewFragment extends Fragment {
    private static int userID;

    protected AlertDialog alertDialog;
    private int clickedStar;
    protected ImageView latestStar;
    ArrayList<Challenge> challenges;
    ListView lv;
    private static CustomAdapter adapter;
    private double avgRating;
    private MainActivity mainActivity;
    private Place clickedPlace;

    Challenge c;
    List<Participation> participationList;

    // motsvarar stjärnorna i popup-fönstret
    ImageView ib1;
    ImageView ib2;
    ImageView ib3;
    ImageView ib4;
    ImageView ib5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        clickedPlace = mainActivity.getOpenThisPlaceFragment();
        participationList = mainActivity.getParticipationList();
        avgRating = clickedPlace.getAverageRating();

        userID = MainActivity.getUserID();

        View view = inflater.inflate(R.layout.fragment_location_view, container, false);

        lv = view.findViewById(R.id.list);
        challenges = clickedPlace.getChallengeList();

        Date d = Calendar.getInstance().getTime();

        TextView missingChallengesTxt = view.findViewById(R.id.missingChallengesTxt);

        if (!challenges.isEmpty()) {
            missingChallengesTxt.setVisibility(View.GONE);

            adapter  = new CustomAdapter (challenges, getActivity().getApplicationContext());

            lv.setAdapter(adapter);

            lv.setClickable(true);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ChallengeDialog cd = new ChallengeDialog();
                    //RADEN UNDER HÄMTAR DET OBJEKT SOM ANVÄNDAREN KLICKAT PÅ I LISTAN
                    c = adapter.getItem(position);
                    //RADEN UNDER ANROPAR EN METOD I CHALLENGEDIALOG OCH SKICKAR MED OBJEKTET SOM ANVÄNDAREN VALT
                    //METODEN FINNS PÅ RAD 103 I CHALLENGEDIALOG
                    cd.updateView(c, (OutdoorGym) clickedPlace, getSpecificParticipation()); // ful-casting
                    cd.show(getFragmentManager(), "ChallengeDialog");
                    Log.d(MainActivity.class.getSimpleName(), "hej");
                }
            });
        } else {
            missingChallengesTxt.setVisibility(View.VISIBLE);
        }

        TextView title = (TextView) mainActivity.findViewById(R.id.main_title_text);
        title.setText(clickedPlace.getName());
        TextView textRating = (TextView) view.findViewById(R.id.location_view_gymrating);
        textRating.setText(String.valueOf(clickedPlace.getAverageRating()));
        if (clickedPlace instanceof OutdoorGym) {
            TextView description = (TextView) view.findViewById(R.id.gymview_description);
            if (((OutdoorGym) clickedPlace).getDescription().equals("null")) {
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

    private Participation getSpecificParticipation() {
        for (Participation participation : participationList) {
            if (participation.getChallengeID() == c.getChallengeID()) {
                return participation;
            }
        }
        return null;
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

        ib1 = (ImageView) alertDialog.findViewById(R.id.star1);
        ib2 = (ImageView) alertDialog.findViewById(R.id.star2);
        ib3 = (ImageView) alertDialog.findViewById(R.id.star3);
        ib4 = (ImageView) alertDialog.findViewById(R.id.star4);
        ib5 = (ImageView) alertDialog.findViewById(R.id.star5);
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
            new RateGymCall().execute();
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
                ib5.clearColorFilter();
                ib4.clearColorFilter();
                ib3.clearColorFilter();
                ib2.clearColorFilter();
                ib1.clearColorFilter();
            }

            String textID = v.getResources().getResourceName(v.getId());
            ImageView iV = (ImageView) v.findViewById(v.getId());

            clickedStar = Integer.parseInt(String.valueOf(textID.charAt(textID.length() - 1)));

            if (iV.equals(ib5)) {
                ib4.setColorFilter(Color.parseColor("#f4c842"));
                ib3.setColorFilter(Color.parseColor("#f4c842"));
                ib2.setColorFilter(Color.parseColor("#f4c842"));
                ib1.setColorFilter(Color.parseColor("#f4c842"));
            } else if (iV.equals(ib4)) {
                ib3.setColorFilter(Color.parseColor("#f4c842"));
                ib2.setColorFilter(Color.parseColor("#f4c842"));
                ib1.setColorFilter(Color.parseColor("#f4c842"));
            } else if (iV.equals(ib3)) {
                ib2.setColorFilter(Color.parseColor("#f4c842"));
                ib1.setColorFilter(Color.parseColor("#f4c842"));
            } else if (iV.equals(ib2)) {
                ib1.setColorFilter(Color.parseColor("#f4c842"));
            }

            iV.setColorFilter(Color.parseColor("#f4c842"));
            latestStar = iV;
        }
    }

    private class RateGymCall extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
//                Call<String> call = params[0];
//                Response<String> response = call.execute();
                return mainActivity.rateGymCall(clickedPlace.getId(), userID, clickedStar);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                if (result.equals("Success")) {
                    alertDialog.cancel();
                    Toast.makeText(mainActivity, "Din rankning av gymmet har skickats", Toast.LENGTH_SHORT).show();
                } else if (result.equals("user has already rated this gym")) {
                    alertDialog.cancel();
                    Toast.makeText(mainActivity, "Du har redan rankat gymmet tidigare!", Toast.LENGTH_SHORT).show();
                }
            } else {
                System.out.println("RateGym response contained null");
            }
        }
    }
}
