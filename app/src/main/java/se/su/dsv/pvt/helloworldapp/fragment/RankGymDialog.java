package se.su.dsv.pvt.helloworldapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import se.su.dsv.pvt.helloworldapp.R;

public class RankGymDialog extends Activity {

    View view = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank_gym_dialog);
        LayoutInflater inflater = getLayoutInflater();
        Context c = inflater.getContext();
        view = inflater.inflate()

    }
}
