package se.su.dsv.pvt.helloworldapp.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import se.su.dsv.pvt.helloworldapp.R;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        TextView h = getActivity().findViewById(R.id.hour);
        TextView min = getActivity().findViewById(R.id.minute);

        if (view.getCurrentHour() < 10) {
            h.setText("0" + view.getCurrentHour() + "");
        } else {
            h.setText(view.getCurrentHour() + "");
        }

        if (view.getCurrentMinute() < 10) {
            min.setText("0" + view.getCurrentMinute() + "");
        } else {
            min.setText(view.getCurrentMinute() + "");
        }
    }
}