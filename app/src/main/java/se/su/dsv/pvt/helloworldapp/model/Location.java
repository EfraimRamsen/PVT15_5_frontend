package se.su.dsv.pvt.helloworldapp.model;

import com.google.android.gms.maps.model.LatLng;

public class Location {

    private double x;
    private double y;
    private LatLng latLng;

    public Location(double lat, double lng) {
        x = lat;
        y = lng;

        latLng = new LatLng(lat, lng);
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

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
