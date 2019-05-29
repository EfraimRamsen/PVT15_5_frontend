package se.su.dsv.pvt.helloworldapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.activity.MainActivity;

public class ChallengeDialog extends DialogFragment {

    private static final String TAG = "ChallengeDialog";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        MainActivity mainActivity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.dialog_challenge, container, false);
        TextView timeAndDate = view.findViewById(R.id.timeAndDate);
        TextView name = view.findViewById(R.id.name);
        TextView description = view.findViewById(R.id.description);
        TextView participants = view.findViewById(R.id.participants);
        Button join = view.findViewById(R.id.join);
        Button ok = view.findViewById(R.id.ok);

        timeAndDate.setText("Tid och datum: " );
        name.setText("Utmaning: ");
        description.setText("Beskrivning: ");
        participants.setText("Antal deltagare: ");


        join.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick (View v){
              CharSequence text = join.getText();
              if (text.equals("Gå med")){
                  //TODO: add functionality to join a challenge
                  join.setText("Gå ur");
              } else if (text.equals("Gå ur")){
                  //TODO: add functionality to leave a challenge
                  join.setText("Gå med");
              }
          }
        });

        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getDialog().dismiss();
            }
        });

        return view;
    }
}
