package se.su.dsv.pvt.helloworldapp.fragment;

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

import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.activity.MainActivity;
import se.su.dsv.pvt.helloworldapp.model.Place;

public class LocationViewFragment extends Fragment {

    protected AlertDialog alertDialog;
    private int clickedStar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity mainActivity = (MainActivity) getActivity();
        Place clickedPlace = mainActivity.getOpenThisPlaceFragment();
        //System.err.println(clickedPlace.getName()); // tested if we got a Place.
        /**
         * this is for trying out/debugging the popup dialog!
         * TODO: make something nicer of it.
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.rank_gym_dialog);
        alertDialog = builder.create();
        alertDialog.show();
        /**
         * Creates listeners for the buttons and stars
         * @author Niklas Edström
         */
        Button cancelButton = (Button) alertDialog.findViewById(R.id.rankgym_button_cancel);
        cancelButton.setOnClickListener(new cancelButtonListener());
        Button confirmButton = (Button) alertDialog.findViewById(R.id.rankgym_button_ok);
        confirmButton.setOnClickListener(new confirmButtonListener());

        starButtonListener starbl = new starButtonListener();

        ImageView ib1 = (ImageView) alertDialog.findViewById(R.id.rankgym_imageButton1);
        ImageView ib2 = (ImageView) alertDialog.findViewById(R.id.rankgym_imageButton2);
        ImageView ib3 = (ImageView) alertDialog.findViewById(R.id.rankgym_imageButton3);
        ImageView ib4 = (ImageView) alertDialog.findViewById(R.id.rankgym_imageButton4);
        ImageView ib5 = (ImageView) alertDialog.findViewById(R.id.rankgym_imageButton5);

        ib1.setOnClickListener(starbl);
        ib2.setOnClickListener(starbl);
        ib3.setOnClickListener(starbl);
        ib4.setOnClickListener(starbl);
        ib5.setOnClickListener(starbl);


        return inflater.inflate(R.layout.fragment_location_view, container, false);
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
             * a) Läs av clickedStar,
             * b) Addera användarens betyg (clickedStar) till databasen.
             */
            ;
        }
    }
    public class starButtonListener implements ImageView.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            System.out.println(id);
            ImageView iV = v.findViewById(id);
            iV.setImageResource(R.drawable.ic_back_button);
        }
    }
}
