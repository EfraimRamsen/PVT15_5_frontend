package se.su.dsv.pvt.helloworldapp.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import se.su.dsv.pvt.helloworldapp.R;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment fragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

    }
    @Override
    public void onMapReady(GoogleMap map){
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



//        if (checkLocationPermission()) {
//            googleMap.setMyLocationEnabled(true);
//        } else {
//            askPermission();
//        }

//        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); // icke-animerad
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); //animerad laddning av kartan


    }
}
