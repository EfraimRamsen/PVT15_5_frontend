package se.su.dsv.pvt.helloworldapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OutdoorGym extends Place {
    @SerializedName("description")
    @Expose
    private String description;

    public OutdoorGym(Location location, String name, Integer id, String description) {
        super(location, name, id);
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String s) {
        this.description = s;
    }
}
