package se.su.dsv.pvt.helloworldapp.fragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.activity.MainActivity;
import se.su.dsv.pvt.helloworldapp.model.Challenge;


public class CustomAdapter extends ArrayAdapter<Challenge> {

    private ArrayList<Challenge> challenges;
    Context mContext;

    private static class ViewHolder {
        TextView name;
        TextView description;
    }

    public CustomAdapter(ArrayList<Challenge> data, Context context) {
        super(context, R.layout.list_item, data);
        this.challenges = data;
        this.mContext=context;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View vy, ViewGroup parent) {
        // Get the data item for this position
        Challenge challenge = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (vy == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            vy = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.name = vy.findViewById(R.id.challenge_name);
            viewHolder.description = vy.findViewById(R.id.description);

            result = vy;

            vy.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) vy.getTag();
            result = vy;
        }

        lastPosition = position;

        viewHolder.name.setText(challenge.getName());
        viewHolder.description.setText(challenge.getDescription());

        // Return the completed view to render on screen
        return vy;
    }

}
