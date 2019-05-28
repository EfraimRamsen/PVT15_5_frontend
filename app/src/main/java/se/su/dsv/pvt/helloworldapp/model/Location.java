package se.su.dsv.pvt.helloworldapp.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("x") // SerializedName motsvarar namnet på variabeln i JSON
    @Expose // Expose: egentligen onödig/används inte - säger vilken data som ska konverteras till JSON och tillbaka
    private Double x;
    @SerializedName("y")
    @Expose
    private Double y;

    private LatLng latLng;

    public Location(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Location() {
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Denna metod sätter LatLng-objektet med koordinater-värdena. Behövs för att Google Maps-marker ska kunna placeras på kartan.
     * @author JD
     * @param x
     * @param y
     */
    public void setLatLng(double x, double y) {
        this.latLng = new LatLng(x, y);
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y+ "latLng: " + latLng;
    }
}
