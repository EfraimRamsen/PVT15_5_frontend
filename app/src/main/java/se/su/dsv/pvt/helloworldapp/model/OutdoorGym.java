package se.su.dsv.pvt.helloworldapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OutdoorGym extends Place {
    @SerializedName("description")
    @Expose
    private String description;

    public OutdoorGym(Location location, String name, Integer id, String description, ArrayList<Challenge> challengeList, double avgRating) {
        super(location, name, id, challengeList, avgRating);
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String s) {
        this.description = s;
    }

    public void ifDescIsNull() {
        if (description.equals("null")) {
            this.description = "Beskrivning saknas.";
        }
    }
}
