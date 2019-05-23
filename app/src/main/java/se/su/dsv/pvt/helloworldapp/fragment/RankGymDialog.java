package se.su.dsv.pvt.helloworldapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.activity.MainActivity;

public class RankGymDialog extends Activity {

    View view = null;
    ViewGroup viewGroup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("den creatar ivf");

        LayoutInflater inflater = getLayoutInflater();
        Context c = inflater.getContext();
        LayoutInflater.Factory lif = inflater.getFactory();
        view = lif.onCreateView("rankgymdialogxml", c, findViewById(R.id.rankgymdialogxml));
        Button cancelButton = view.findViewById(R.id.rankgym_button_cancel);
        cancelButton.setOnClickListener(new cancelButtonListener());
        setContentView(view);


    }

    public View getView() {
        return this.view;
    }

    class cancelButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            System.out.println("it must be bunnies");
        }
    }
}
