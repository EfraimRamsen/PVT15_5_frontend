package se.su.dsv.pvt.helloworldapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.activity.MainActivity;
import se.su.dsv.pvt.helloworldapp.model.Challenge;

/**
 * This is a mess. TODO: add a dynamic (or somewhat static) list of active challenges.
 * @author Niklas Edström
 */
public class ChallengeFragment extends Fragment {

    ArrayList<Challenge> challenges;
    ListView lv;
    private static CustomAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        lv = view.findViewById(R.id.listofActiveChallenges);

        // Får ej denna metod att fungera
//        MainActivity mainActivity = (MainActivity) getActivity();
//        challenges = mainActivity.getUserChallenges(30); // lägg till nuvarande userID här
        challenges = new ArrayList<>();

        Date d = Calendar.getInstance().getTime();

        adapter  = new CustomAdapter (challenges, getActivity().getApplicationContext());

        lv.setAdapter(adapter);

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        /*
        MainActivity mainActivity = (MainActivity) getActivity();
        //this.getFragmentManager().findFragmentById(R.id.completedChallenges);
        TextView completedChallengesNumber = (TextView) getView().findViewById(R.id.completedChallenges);
        String s = String.valueOf(mainActivity.getCompletedChallangeNumber());
        completedChallengesNumber.setText(s);
        */
        //TODO: här någonstans måste vi lägga in aktiva challenges i den layouten som heter
        //showchallengesprofile
    }
}
