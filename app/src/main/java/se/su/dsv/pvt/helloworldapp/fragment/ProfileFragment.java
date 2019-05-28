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

import se.su.dsv.pvt.helloworldapp.R;

public class ProfileFragment extends Fragment {

    public static final String GOOGLE_ACCOUNT = "google_account_test";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        String[] listItems = {"Hej1", "Hej2", "Hej3"};

        ListView listView = view.findViewById(R.id.listofActiveChallenges);

        ArrayAdapter<String> listViewAdapter  = new ArrayAdapter<> (
                getActivity(), android.R.layout.simple_list_item_1, listItems
        );

        listView.setAdapter(listViewAdapter);

        return view;
    }
}
