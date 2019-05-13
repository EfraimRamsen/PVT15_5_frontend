package se.su.dsv.pvt.helloworldapp.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Denna klass finns för att motsvara vad som finns hos frontenden.
 * NOTERA: denna klass är tom, då vi inväntar backend-teamet.
 */

public class OutdoorGym extends Place {
    private String description;

    public OutdoorGym(LatLng location, String name, int id, String description) {
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
