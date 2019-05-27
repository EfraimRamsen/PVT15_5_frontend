package se.su.dsv.pvt.helloworldapp.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.model.Challenge;


public class CustomAdapter extends ArrayAdapter<Challenge> {

    private ArrayList<Challenge> challenges;
    Context mContext;

    private static class ViewHolder {
        TextView name;
        TextView description;
        TextView date;
        TextView gym;
    }

    public CustomAdapter(ArrayList<Challenge> data, Context context) {
        super(context, R.layout.list_item, data);
        this.challenges = data;
        this.mContext=context;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Challenge challenge = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.name = convertView.findViewById(R.id.challenge_name);
            viewHolder.description = convertView.findViewById(R.id.description);
            viewHolder.date = convertView.findViewById(R.id.date);
            viewHolder.gym = convertView.findViewById(R.id.gym);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        viewHolder.name.setText(challenge.getName());
        viewHolder.description.setText(challenge.getDescription());

        // Return the completed view to render on screen
        return convertView;
    }
}
