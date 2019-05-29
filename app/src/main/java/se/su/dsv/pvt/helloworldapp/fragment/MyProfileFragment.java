package se.su.dsv.pvt.helloworldapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.activity.MainActivity;
import se.su.dsv.pvt.helloworldapp.model.Challenge;
import se.su.dsv.pvt.helloworldapp.model.Participation;

/**
 * This is a mess. TODO: add a dynamic (or somewhat static) list of active challenges.
 * @author Niklas Edström
 */
public class MyProfileFragment extends Fragment {

    ArrayList<Challenge> challenges;
    ListView lv;
    private static CustomAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);

        lv = view.findViewById(R.id.listofActiveChallenges);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        MainActivity mainActivity = (MainActivity) getActivity();
        int finishedChallenges = 0;
        try {
            for (Participation part : mainActivity.getParticipationList()) {
                if (part.isCompleted()) {
                    finishedChallenges++;
                }
            }
        }
        catch (NullPointerException e) {
            finishedChallenges = 0;
        }

        TextView tV = (TextView) getView().findViewById(R.id.finishedChallenges);
        tV.setText(finishedChallenges + " avklarade!");
    }

    public void showUsersChallenges() {
//        MainActivity mainActivity = (MainActivity) getActivity();
//        mainActivity.getUserChallengesCall(1); // lägg till nuvarande userID här

        if (challenges != null) {
            adapter = new CustomAdapter(challenges, getActivity().getApplicationContext());

            lv.setAdapter(adapter);
        } else {
            System.out.println("challenges är null");
        }
    }

    public void setChallenges(ArrayList<Challenge> challenges) {
        this.challenges = challenges;
    }
}
