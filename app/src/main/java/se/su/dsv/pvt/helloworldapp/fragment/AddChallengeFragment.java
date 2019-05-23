package se.su.dsv.pvt.helloworldapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.activity.MainActivity;
import se.su.dsv.pvt.helloworldapp.model.Challenge;

public class AddChallengeFragment extends Fragment implements View.OnClickListener {

    private View vy = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_challenge, container, false);
        String[] values =
                {"Välj plats", "Akalla Gårds utegym", "Björkhagens utegym", "Bredängs utegym", "Brotorp utegym", "Eriksdal utegym", "Fagersjöskogens utegym", "Farsta utegym", "Farstanäsets utegym", "Farstastrandsbadets utegym", "Flatenbadet utegym", "Fruängens utegym", "Grimsta utegym", "Grimstafältet - Grimsta mostionsspår", "Gärdet utegym", "Hammarby Sjöstads utegym", "Hellasgårdens utegym", "Hjorthagens utegym", "Hornsbergs strands utegym", "Hökarängsbadets utegym", "Kaknäs utegym", "Kanaanbadets utegym", "Kronobergsparkens utegym", "Kungsholms strandstigs utegym", "Kärrtorp utegym", "Lappkärrsbergets utegym, Docentbacken", "Liljeholmens utegym", "Lillsjöns utegym", "Mellanbergsparkens utegym", "Mälarhöjdsbadets utegym", "Nytorpsgärdets utegym", "Nälsta utegym", "Oppundaparkens utegym", "Pålsundsparkens utegym", "Rålambshovsparkens utegym", "Sannadalsparkens utegym", "Skarpnäcksfältets utegym", "Skärholmens utegym", "Smedsuddsbadets utegym", "Solviks utegym", "Spånga utegym", "Spånga IP utegym", "Stora Mossens utegym", "Stora Sköndals utegym", "Stråkets utegym", "Sätra IP utegym", "Sätradals utegym", "Sätrastrandsbadet utegym", "Tanto strandbads utegym", "Uteträffen i Tessinparken, utegym för seniorer", "Vanadislundens utegym", "Vasaparkens utegym", "Vintervikens utegym", "Vårgårdens utegym", "Ågesta utegym", "Årstaskogens utegym", "Årstavikens utegym", "Östberga utegym"
                };
        vy = view;
        Spinner spinner = view.findViewById(R.id.locationPicker);
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<>(Objects.requireNonNull(this.getActivity()), android.R.layout.simple_spinner_item, values);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(LTRadapter);
        Button createButton = (Button) view.findViewById(R.id.createButton);
        Button cancelButton = (Button) view.findViewById(R.id.cancelButton);
        createButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        return view;
    }

    /**
     * This is what creates the challenge, AND, adds it to MainActivity for checking in with backend.
     * @param v standard thing, not used, can be ignored.
     * @author Niklas Edström, mostly.
     */
    @Override
    public void onClick(View v) {
        MainActivity mainActivity = (MainActivity) getActivity();
        switch (v.getId()) {
            case R.id.createButton:
                if (emptyField()){
                    Toast.makeText(mainActivity, "tomt fält", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    EditText challenge = vy.findViewById(R.id.challengeText);
                    String cString = challenge.getText().toString();
                    EditText description = vy.findViewById(R.id.descriptionText);
                    String dString = description.getText().toString();
                    Challenge c = new Challenge(cString, dString, 0, 0, 0, parseDate());
                    mainActivity.setActive();
                    mainActivity.addActiveChallenge(c);
                    Toast.makeText(mainActivity, c.toString(), Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.cancelButton:
                MyCustomDialog mcd = new MyCustomDialog();
                mcd.show(getFragmentManager(), "MyCustomDialog");
                Toast.makeText(mainActivity, "avbryt", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    /**
     * check för att inget av textfälten är tomma när man försöker skapa ett objekt
     * @return true om ett obligatoriskt fält inte är ifyllt.
     */
    private boolean emptyField() {
        EditText challenge = vy.findViewById(R.id.challengeText);
        EditText description = vy.findViewById(R.id.descriptionText);
        TextView y = vy.findViewById(R.id.year);
        TextView m = vy.findViewById(R.id.month);
        TextView d = vy.findViewById(R.id.day);
        TextView h = vy.findViewById(R.id.hour);
        TextView min = vy.findViewById(R.id.minute);
        if (challenge.getText().equals("")) {
            return true;
        } else if (description.getText().equals("")) {
            return true;
        } else if (y.getText().equals("")) {
            return true;
        } else if (m.getText().equals("")) {
            return true;
        } else if (d.getText().equals("")){
            return true;
        } else if (h.getText().equals("")){
            return true;
        } else if (min.getText().equals("")){
            return true;
        }
        return false;
    }

    /**
     * gör om alla textfält till ett Datum-objekt för att skapa ett Challenge-objekt
     * @return datum (java.util.Date) som väljs i Date- och Time-pickern av användaren
     */
    private Date parseDate(){
        TextView y = vy.findViewById(R.id.year);
        TextView m = vy.findViewById(R.id.month);
        TextView d = vy.findViewById(R.id.day);
        TextView h = vy.findViewById(R.id.hour);
        TextView min = vy.findViewById(R.id.minute);
        String stringY = y.getText().toString();
        String stringM = m.getText().toString();
        String stringD = d.getText().toString();
        String stringH = h.getText().toString();
        String stringMin = min.getText().toString();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(stringY));
        cal.set(Calendar.MONTH, Integer.parseInt(stringM));
        cal.set(Calendar.DATE, Integer.parseInt(stringD));
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(stringH));
        cal.set(Calendar.MINUTE, Integer.parseInt(stringMin));
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

}
