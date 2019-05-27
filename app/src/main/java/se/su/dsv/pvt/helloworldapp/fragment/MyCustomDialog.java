package se.su.dsv.pvt.helloworldapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.activity.MainActivity;

public class MyCustomDialog extends DialogFragment {

    private static final String TAG = "MyCustomDialog";

    private EditText mInput;
    private TextView mActionOk, mActionCancel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        MainActivity mainActivity = (MainActivity) getActivity();
       // AddChallengeFragment acf = new AddChallengeFragment();
        View view = inflater.inflate(R.layout.dialog_cancel, container, false);
        Button yes = view.findViewById(R.id.yes);
        Button no = view.findViewById(R.id.no);

        no.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(mainActivity, "no", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });

        yes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(mainActivity, "yes", Toast.LENGTH_SHORT).show();
                mainActivity.clearAddChallenge();
                getDialog().dismiss();
            }
        });

        return view;
    }
}
