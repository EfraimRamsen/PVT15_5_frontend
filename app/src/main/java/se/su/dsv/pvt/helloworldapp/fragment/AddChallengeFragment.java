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
        Button b = (Button) view.findViewById(R.id.createButton);
        b.setOnClickListener(this::onClick);
        return view;
    }

    @Override
    public void onClick(View v) {

        MainActivity mainActivity = (MainActivity) getActivity();
        EditText challenge = (EditText) vy.findViewById(R.id.challengeText);
        String cString = challenge.getText().toString();
        EditText description = vy.findViewById(R.id.descriptionText);
        String dString = description.getText().toString();
        TextView date = vy.findViewById(R.id.date);
        TextView time = vy.findViewById(R.id.time);
        Challenge c = new Challenge(cString, dString, 1,mainActivity.getChallengeNumber()+1,0,null);
        mainActivity.addChallengeNumber();
        mainActivity.setActive();
        mainActivity.addActiveChallenge(c);
        debugAddChallenges();

    }
    public void debugAddChallenges() {
        Challenge c = new Challenge("10k armhämtningar", "Jag är starkare än din pappa",
                1, 1, 12, null);
        Challenge d = new Challenge("Dricka alkohol", "Smuggla plunta in på Foobar",
                25, 2, 10, null);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.addChallengeNumber();
        mainActivity.addChallengeNumber();
        mainActivity.setActive();
        mainActivity.addActiveChallenge(c);
        mainActivity.addActiveChallenge(d);
    }
}
