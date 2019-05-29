package se.su.dsv.pvt.helloworldapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.*;
import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.activity.MainActivity;
import se.su.dsv.pvt.helloworldapp.model.Challenge;

public class AddChallengeFragment extends Fragment implements View.OnClickListener {

    private View vy = null;

    private static int userID = 1; // tillfällig ID

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_challenge, container, false);
        String[] values = {"Välj plats", "Akalla Gårds utegym", "Björkhagens utegym", "Bredängs utegym", "Brotorp utegym", "Eriksdal utegym", "Fagersjöskogens utegym", "Farsta utegym", "Farstanäsets utegym", "Farstastrandsbadets utegym", "Flatenbadet utegym", "Fruängens utegym", "Grimsta utegym", "Grimstafältet - Grimsta mostionsspår", "Gärdet utegym", "Hammarby Sjöstads utegym", "Hellasgårdens utegym", "Hjorthagens utegym", "Hornsbergs strands utegym", "Hökarängsbadets utegym", "Kaknäs utegym", "Kanaanbadets utegym", "Kronobergsparkens utegym", "Kungsholms strandstigs utegym", "Kärrtorp utegym", "Lappkärrsbergets utegym, Docentbacken", "Liljeholmens utegym", "Lillsjöns utegym", "Mellanbergsparkens utegym", "Mälarhöjdsbadets utegym", "Nytorpsgärdets utegym", "Nälsta utegym", "Oppundaparkens utegym", "Pålsundsparkens utegym", "Rålambshovsparkens utegym", "Sannadalsparkens utegym", "Skarpnäcksfältets utegym", "Skärholmens utegym", "Smedsuddsbadets utegym", "Solviks utegym", "Spånga utegym", "Spånga IP utegym", "Stora Mossens utegym", "Stora Sköndals utegym", "Stråkets utegym", "Sätra IP utegym", "Sätradals utegym", "Sätrastrandsbadet utegym", "Tanto strandbads utegym", "Uteträffen i Tessinparken, utegym för seniorer", "Vanadislundens utegym", "Vasaparkens utegym", "Vintervikens utegym", "Vårgårdens utegym", "Ågesta utegym", "Årstaskogens utegym", "Årstavikens utegym", "Östberga utegym"
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
                    Challenge c = new Challenge(cString, dString, 0, 0, getID(), parseDate());
                    Toast.makeText(mainActivity, "Din utmaning är skapad", Toast.LENGTH_SHORT).show();
                    mainActivity.createChallengeCall(userID, c);
                    mainActivity.clearAddChallenge();
                    break;
                }
            case R.id.cancelButton:
                MyCustomDialog mcd = new MyCustomDialog();
                mcd.show(getFragmentManager(), "MyCustomDialog");
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
        try {
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

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd, HH:mm");
            String dateTime = stringY + "-" + stringM + "-" + stringD + ", " + stringH + ":" + stringMin;
            Date date = dateFormat.parse(dateTime);

            return date;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private int getID(){
        Spinner mySpinner = vy.findViewById(R.id.locationPicker);
        String text = mySpinner.getSelectedItem().toString();
        int id = 0;
        switch (text){
            case "Akalla Gårds utegym":
                id = 106;
                break;
            case "Björkhagens utegym":
                id = 69;
                break;
            case "Bredängs utegym":
                id = 58;
                break;
            case "Brotorp utegym":
                id = 82;
                break;
            case "Eriksdal utegym":
                id = 85;
                break;
            case "Fagersjöskogens utegym":
                id = 101;
                break;
            case "Farsta utegym":
                id = 93;
                break;
            case "Farstanäsets utegym":
                id = 112;
                break;
            case "Farstastrandsbadets utegym":
                id = 68;
                break;
            case "Flatenbadet utegym":
                id = 59;
                break;
            case "Fruängens utegym":
                id = 60;
                break;
            case "Grimsta utegym":
                id = 111;
                break;
            case "Grimstafältet - Grimsta mostionsspår":
                id = 72;
                break;
            case "Gärdet utegym":
                id = 65;
                break;
            case "Hammarby Sjöstads utegym":
                id = 99;
                break;
            case "Hellasgårdens utegym":
                id = 94;
                break;
            case "Hjorthagens utegym":
                id = 89;
                break;
            case "Hornsbergs strands utegym":
                id = 70;
                break;
            case "Hökarängsbadets utegym":
                id = 97;
                break;
            case "Kaknäs utegym":
                id = 103;
                break;
            case "Kanaanbadets utegym":
                id = 105;
                break;
            case "Kronobergsparkens utegym":
                id = 77;
                break;
            case "Kungsholms strandstigs utegym":
                id = 57;
                break;
            case "Kärrtorp utegym":
                id = 100;
                break;
            case "Lappkärrsbergets utegym, Docentbacken":
                id = 62;
                break;
            case "Liljeholmens utegym":
                id = 81;
                break;
            case "Lillsjöns utegym":
                id = 84;
                break;
            case "Mellanbergsparkens utegym":
                id = 88;
                break;
            case "Mälarhöjdsbadets utegym":
                id = 104;
                break;
            case "Nytorpsgärdets utegym":
                id = 66;
                break;
            case "Nälsta utegym":
                id = 107;
                break;
            case "Oppundaparkens utegym":
                id = 71;
                break;
            case "Pålsundsparkens utegym":
                id = 109;
                break;
            case "Rålambshovsparkens utegym":
                id = 90;
                break;
            case "Sannadalsparkens utegym":
                id = 61;
                break;
            case "Skarpnäcksfältets utegym":
                id = 75;
                break;
            case "Skärholmens utegym":
                id = 92;
                break;
            case "Smedsuddsbadets utegym":
                id = 102;
                break;
            case "Solviks utegym":
                id = 80;
                break;
            case "Spånga utegym":
                id = 98;
                break;
            case "Spånga IP utegym":
                id = 56;
                break;
            case "Stora Mossens utegym":
                id = 91;
                break;
            case "Stora Sköndals utegym":
                id = 95;
                break;
            case "Stråkets utegym":
                id = 78;
                break;
            case "Sätra IP utegym":
                id = 108;
                break;
            case "Sätradals utegym":
                id = 83;
                break;
            case "Sätrastrandsbadet utegym":
                id = 73;
                break;
            case "Tanto strandbads utegym":
                id = 79;
                break;
            case "Uteträffen i Tessinparken, utegym för seniorer":
                id = 100;
                break;
            case "Vanadislundens utegym":
                id = 96;
                break;
            case "Vasaparkens utegym":
                id = 64;
                break;
            case "Vintervikens utegym":
                id = 67;
                break;
            case "Vårgårdens utegym":
                id = 63;
                break;
            case "Ågesta utegym":
                id = 74;
                break;
            case "Årstaskogens utegym":
                id = 86;
                break;
            case "Årstavikens utegym":
                id = 76;
                break;
            case "Östberga utegym":
                id = 87;
                break;
        }
        return id;
    }

}
