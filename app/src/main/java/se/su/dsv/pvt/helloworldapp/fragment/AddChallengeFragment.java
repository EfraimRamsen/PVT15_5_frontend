package se.su.dsv.pvt.helloworldapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Objects;

import se.su.dsv.pvt.helloworldapp.R;

public class AddChallengeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_challenge, container, false);
        String[] values =
                {"Välj plats", "Akalla Gårds utegym", "Björkhagens utegym", "Bredängs utegym", "Brotorp utegym", "Eriksdal utegym", "Fagersjöskogens utegym", "Farsta utegym", "Farstanäsets utegym", "Farstastrandsbadets utegym", "Flatenbadet utegym", "Fruängens utegym", "Grimsta utegym", "Grimstafältet - Grimsta mostionsspår", "Gärdet utegym", "Hammarby Sjöstads utegym", "Hellasgårdens utegym", "Hjorthagens utegym", "Hornsbergs strands utegym", "Hökarängsbadets utegym", "Kaknäs utegym", "Kanaanbadets utegym", "Kronobergsparkens utegym", "Kungsholms strandstigs utegym", "Kärrtorp utegym", "Lappkärrsbergets utegym, Docentbacken", "Liljeholmens utegym", "Lillsjöns utegym", "Mellanbergsparkens utegym", "Mälarhöjdsbadets utegym", "Nytorpsgärdets utegym", "Nälsta utegym", "Oppundaparkens utegym", "Pålsundsparkens utegym", "Rålambshovsparkens utegym", "Sannadalsparkens utegym", "Skarpnäcksfältets utegym", "Skärholmens utegym", "Smedsuddsbadets utegym", "Solviks utegym", "Spånga utegym", "Spånga IP utegym", "Stora Mossens utegym", "Stora Sköndals utegym", "Stråkets utegym", "Sätra IP utegym", "Sätradals utegym", "Sätrastrandsbadet utegym", "Tanto strandbads utegym", "Uteträffen i Tessinparken, utegym för seniorer", "Vanadislundens utegym", "Vasaparkens utegym", "Vintervikens utegym", "Vårgårdens utegym", "Ågesta utegym", "Årstaskogens utegym", "Årstavikens utegym", "Östberga utegym"
                };
        Spinner spinner = view.findViewById(R.id.locationPicker);
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<>(Objects.requireNonNull(this.getActivity()), android.R.layout.simple_spinner_item, values);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(LTRadapter);
        return view;
    }

}
