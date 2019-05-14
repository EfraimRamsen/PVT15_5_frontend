package se.su.dsv.pvt.helloworldapp.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.List;
import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.model.CustomMapMarker;
import se.su.dsv.pvt.helloworldapp.model.OutdoorGym;
import se.su.dsv.pvt.helloworldapp.model.Place;

public class MapViewFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private List<OutdoorGym> outdoorGyms;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment fragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // Denna här raden sätter sätter custommarker istället för standard:
        googleMap.setInfoWindowAdapter(new CustomMapMarker(getContext()));

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(new LatLng(59.3246656, 18.0410247))
                .zoom(10.5f)
                .bearing(0)
                .tilt(0)
                .build();


        /**
         * TODO:
         * - we should probably create a better way of reading and adding the markers to the map.
         * This is probably something we do after adding all the markers, and finishing the Gym-class.
         * - Are we always going to show every marker, or will we give the user the ability to show
         * every marker that's, say, 3 km from the users location?
         */

        /**
         * Om vi vill skicka med information med en marker gör man som följer.
         * Skillnaden gentemot innan är att man skapar en Marker som refererar till den MarkerOptions
         * man skapar, och sedan adderar en tag medelest marker.setTag(Object o);
         * Denna tag läses sedan av i CustomMapMarker.java, under getInfoContents-metoden som har ett
         * Marker-objekt som argument.
         */
        /*
        Marker marker = googleMap.addMarker((new MarkerOptions()
                .position(new LatLng(59.344187, 18.099205))
                .title("Utegym - Östermalm")
                .snippet("Tessinparkens norra del nära parkleken.")
        ));
        marker.setTag(new String("this is not a gym"));
        */

        // TEST NIKLAS
//        OutdoorGym oG = new OutdoorGym(outdoorGym.getLocation(), "majs", 25, "hejhej" );
//        Marker bengt = googleMap.addMarker(new MarkerOptions().position(oG.getLocation().getLatLng()));
//        bengt.setTag(oG);
        // SLUT TEST

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        } else {
            askPermission();
        }

        //        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); // icke-animerad
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); //animerad laddning av kartan

    }

    public void addOutdoorGymList(List<OutdoorGym> outdoorGyms) {
        // Två första raderna - om man vill komma åt activity-metoderna
//        MainActivity mainActivity = (MainActivity) getActivity();
//        outdoorGyms = mainActivity.getPlaces();
        this.outdoorGyms = outdoorGyms;
    }

    public void addAllPlacesToMap() {
        for (Place place : outdoorGyms) {
            try {
                Marker marker = googleMap.addMarker(new MarkerOptions().position(place.getLocation().getLatLng()));
                marker.setTag(place);
            } catch (IllegalArgumentException e) {
                System.out.println("Null i: " + place);
            }
        }
    }

    public static final int PERMISSIONS_REQUEST_LOCATION = 99;


    private void askPermission() {
        requestPermissions(
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSIONS_REQUEST_LOCATION
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED)
                        googleMap.setMyLocationEnabled(true);
                } else {
                    Toast.makeText(getContext(), "Required permission was denied", Toast.LENGTH_LONG).show();
                }
                return;
        }
    }
}
