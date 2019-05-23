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
        TextView date = (TextView) getActivity().findViewById(R.id.date);
        String månad = null;
        switch (view.getMonth()){
            case 1:
                månad = "januari";
                break;
            case 2:
                månad = "februari";
                break;
            case 3:
                månad = "mars";
                break;
            case 4:
                månad = "april";
                break;
            case 5:
                månad = "maj";
                break;
            case 6:
                månad = "juni";
                break;
            case 7:
                månad = "juli";
                break;
            case 8:
                månad = "augusti";
                break;
            case 9:
                månad = "september";
                break;
            case 10:
                månad = "oktober";
                break;
            case 11:
                månad = "november";
                break;
            case 12:
                månad = "december";
                break;
        }
        date.setText(view.getYear() + " / " + view.getMonth() + " / " + view.getDayOfMonth());
    }
}
