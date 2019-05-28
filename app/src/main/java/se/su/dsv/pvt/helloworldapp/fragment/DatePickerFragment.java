package se.su.dsv.pvt.helloworldapp.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import se.su.dsv.pvt.helloworldapp.R;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        TextView y = (TextView) getActivity().findViewById(R.id.year);
        TextView m = (TextView) getActivity().findViewById(R.id.month);
        TextView d = (TextView) getActivity().findViewById(R.id.day);
        int correctedMonth = view.getMonth() + 1; // Adderar 1 så att månad visas korrekt eftersom januari börjar på 0. /JD
        y.setText(view.getYear() + "");
        m.setText(correctedMonth + "");
        d.setText(view.getDayOfMonth() + "");
    }

}
