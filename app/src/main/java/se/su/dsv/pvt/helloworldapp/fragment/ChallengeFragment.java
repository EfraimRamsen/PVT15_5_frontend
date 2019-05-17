package se.su.dsv.pvt.helloworldapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.su.dsv.pvt.helloworldapp.R;

public class ChallengeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_profile, container, false);

        return inflater.inflate(R.layout.fragment_profile, container, false);
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
