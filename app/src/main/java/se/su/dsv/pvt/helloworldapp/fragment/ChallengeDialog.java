package se.su.dsv.pvt.helloworldapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.activity.MainActivity;

public class ChallengeDialog extends DialogFragment {

    private static final String TAG = "ChallengeDialog";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        MainActivity mainActivity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.dialog_challenge, container, false);
        Button ok = view.findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getDialog().dismiss();
            }
        });

        return view;
    }
}
