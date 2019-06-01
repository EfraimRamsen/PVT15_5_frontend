package se.su.dsv.pvt.helloworldapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.activity.MainActivity;
import se.su.dsv.pvt.helloworldapp.model.Challenge;
import se.su.dsv.pvt.helloworldapp.model.OutdoorGym;
import se.su.dsv.pvt.helloworldapp.model.Participation;

/**
 * This is a mess. TODO: add a dynamic (or somewhat static) list of active challenges.
 * @author Niklas Edström
 */
public class MyProfileFragment extends Fragment {

    ArrayList<Challenge> challenges;
    ListView lv;
    private static CustomAdapter adapter;
    Challenge c;

    ChallengeDialog cd = new ChallengeDialog();
    List<OutdoorGym> outdoorGyms;
    List<Participation> participationList;

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

            lv.setClickable(true);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //RADEN UNDER HÄMTAR DET OBJEKT SOM ANVÄNDAREN KLICKAT PÅ I LISTAN
                    c = adapter.getItem(position);
                    //RADEN UNDER ANROPAR EN METOD I CHALLENGEDIALOG OCH SKICKAR MED OBJEKTET SOM ANVÄNDAREN VALT
                    //METODEN FINNS PÅ RAD 103 I CHALLENGEDIALOG
                    cd.updateView(c, getSelectedOutdoorGym(), getSpecificParticipation());
                    //RADEN UNDER ÖPPNAR CHALLENGEDIALOG OCH VISAR FÖNSTRET FÖR ANVÄNDAREN
                    cd.show(getFragmentManager(), "ChallengeDialog");
                    Log.d(MainActivity.class.getSimpleName(), "hej");
                }
            });

        } else {
            System.out.println("challenges är null");
        }
    }

    public void setChallenges(ArrayList<Challenge> challenges) {
        this.challenges = challenges;
    }

    public void setOutdoorGyms(List<OutdoorGym> outdoorGyms) {
//        cd.setOutdoorGym(outdoorGyms);
        this.outdoorGyms = outdoorGyms;
    }

    public void setParticipationList(List<Participation> participationList) {
        this.participationList = participationList;
    }

    private OutdoorGym getSelectedOutdoorGym() {
        for (OutdoorGym outdoorGym : outdoorGyms) {
            if (outdoorGym.getId() == c.getWorkoutSpotID()) {
                return outdoorGym;
            }
        }
        return null;
    }

    private Participation getSpecificParticipation() {
        for (Participation participation : participationList) {
            if (participation.getChallengeID() == c.getChallengeID()) {
                return participation;
            }
        }
        return null;
    }
}
