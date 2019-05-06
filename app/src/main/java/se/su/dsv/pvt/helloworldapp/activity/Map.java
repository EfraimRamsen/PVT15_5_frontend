package se.su.dsv.pvt.helloworldapp.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import se.su.dsv.pvt.helloworldapp.R;


public class Map extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    public static final int titleIcon = R.drawable.ic_map;
    public static final int iconId = R.id.map_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
      
        // Create, configure, and set the toolbar:
        Toolbar toolbar = (Toolbar) TopActionBar
                .getToolbar(findViewById(TopActionBar.getToolbarIntLink())
                        , false);
        setSupportActionBar(toolbar);
        ImageView iV = (ImageView) toolbar.findViewById(iconId);
        iV.setImageDrawable(ResourcesCompat.getDrawable(getResources(), titleIcon, null));

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(new LatLng(59.3246656, 18.0410247))
                .zoom(10.5f)
                .bearing(0)
                .tilt(0)
                .build();

        googleMap.addMarker((new MarkerOptions()
                .position(new LatLng(	59.344187, 18.099205))
                .title("Utegym - Östermalm")
                .snippet("Tessinparkens norra del nära parkleken.")
        ));

        googleMap.addMarker((new MarkerOptions()
                .position(new LatLng(	59.357905, 17.865372))
                .title("Grimsta utegym")
                .snippet("Utegymmet är placerat invid Grimsta bollplan.")
        ));

        if (checkLocationPermission()) {
            googleMap.setMyLocationEnabled(true);
        } else {
            askPermission();
        }



//        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); // icke-animerad
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); //animerad laddning av kartan
    }

    public static final int PERMISSIONS_REQUEST_LOCATION = 99;

    private boolean checkLocationPermission() {
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED);
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSIONS_REQUEST_LOCATION
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (checkLocationPermission())
                        googleMap.setMyLocationEnabled(true);
                } else {
                    Toast.makeText(this, "Required permission was denied", Toast.LENGTH_LONG).show();
                }
                return;
        }

    }
    /*
     * example of how an menu should act when, for example, a button is pressed. This is atm.
     * unneccessary since we don't have anything but a back button; a functionality Android handles
     * for us.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

         * The only functionality we're using comes from calling super with the item. This sends us
         * to the parent-view, which is defined in AndroidManifest.xml, under each Views entry.

        switch(item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
