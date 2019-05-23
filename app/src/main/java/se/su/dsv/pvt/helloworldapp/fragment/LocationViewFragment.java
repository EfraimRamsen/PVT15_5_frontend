package se.su.dsv.pvt.helloworldapp.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.activity.MainActivity;
import se.su.dsv.pvt.helloworldapp.model.Place;

public class LocationViewFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity mainActivity = (MainActivity) getActivity();
        Place clickedPlace = mainActivity.getOpenThisPlaceFragment();
        /**
         * this is for trying out/debugging the popup dialog!
         * TODO: make something nicer of it.

        AlertDialog.Builder adb = new AlertDialog.Builder(getContext());
        adb.setView(R.layout.rank_gym_dialog);
        adb.create();
        adb.show();
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.rank_gym_dialog);
        // builder.setItems();
        AlertDialog alertDialog = builder.create();

        //System.err.println(clickedPlace.getName()); // tested if we got a Place.
        return inflater.inflate(R.layout.fragment_location_view, container, false);
    }

    class cancelButtonListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            ;

        }
    }
}
