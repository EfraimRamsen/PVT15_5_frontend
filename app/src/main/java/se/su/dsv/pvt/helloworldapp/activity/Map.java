package se.su.dsv.pvt.helloworldapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import se.su.dsv.pvt.helloworldapp.R;

public class Map extends AppCompatActivity implements OnMapReadyCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(new LatLng(59.3246656, 18.0410247))
                .zoom(10.5f)
                .bearing(0)
                .tilt(0)
                .build();

//        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); // icke-animerad
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); //animerad laddning av kartan
    }
}
