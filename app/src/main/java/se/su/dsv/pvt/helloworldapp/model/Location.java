package se.su.dsv.pvt.helloworldapp.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("x")
    @Expose
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

    public void setLatLng(double x, double y) {
        this.latLng = new LatLng(x, y);
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y+ "latLng: " + latLng;
    }
}
