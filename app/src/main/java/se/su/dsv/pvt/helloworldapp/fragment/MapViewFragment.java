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
import se.su.dsv.pvt.helloworldapp.activity.MainActivity;
import se.su.dsv.pvt.helloworldapp.model.CustomMapMarker;
import se.su.dsv.pvt.helloworldapp.model.OutdoorGym;
import se.su.dsv.pvt.helloworldapp.model.Place;

// Detta fragment är Google Maps-kartan som visas. /JD
public class MapViewFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private List<OutdoorGym> outdoorGyms; // Denna lista sparar alla utegym som hämtats från API. /JD
    private final Fragment locationViewFragment = new LocationViewFragment();

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
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.getAllGymsCall();  // Hämtar alla gym när kartan är färdig laddad. /JD

        googleMap = map;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setInfoWindowAdapter(new CustomMapMarker(getContext())); //Use CustomMapMarker instead of the standard one.

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(new LatLng(59.3246656, 18.0410247))
                .zoom(10.5f)
                .bearing(0)
                .tilt(0)
                .build();


        // Kontrollerar om behörighet för att hämta enhetens GPS-position har godkänts eller ej.
        // Syftet är att kunna visa användarens position på kartan. Om inte, begär om behörighet. /JD
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        } else {
            askPermission();
        }

        //        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); // icke-animerad
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); //animerad laddning av kartan


        /**
         * Denna metod används när man klickar på popupen av en mapmarker!
         */


        /**
         * Adds clickListener to each Marker marker.
         */
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            /**
             *
             * @param marker is each specific marker.
             * TODO: change so "+ visa mer" works and sends the user to the correct gym-fragment.
             */
            @Override
            public void onInfoWindowClick(Marker marker) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.showLocation();

                mainActivity.setOpenThisPlaceFragment((Place) marker.getTag());
                //LocationViewFragment lwf = new LocationViewFragment();
                //FragmentTransaction fm = getFragmentManager().beginTransaction();
                //fm.add(R.id.fragment_container, lwf).commit();


            }
        });

    }

    /**
     * Denna metod används för att hämta gym-listan från MainActivity och sparar den i en lista i detta fragment.
     * @param outdoorGyms
     * @author JD
     */
    public void addOutdoorGymList(List<OutdoorGym> outdoorGyms) {
        // Två första raderna - om man vill komma åt activity-metoderna
//        MainActivity mainActivity = (MainActivity) getActivity();
//        outdoorGyms = mainActivity.getPlaces();
        this.outdoorGyms = outdoorGyms;
    }

    /**
     * Placerar varje gym-objekt på kartan, representerad som en marker.
     * @author JD
     */
    public void addAllPlacesToMap() {
        for (Place place : outdoorGyms) {
            try {
                Marker marker = googleMap.addMarker(new MarkerOptions().position(place.getLocation().getLatLng()));
                marker.setTag(place);
            } catch (Exception e) {
                System.out.println("Null i: " + place);
            }
        }
    }

    public static final int PERMISSIONS_REQUEST_LOCATION = 99; // ignorera - tilldelar bara ett nummer till denna specifika behörighet /JD

    /**
     * Denna metod skickar själva begäran till användaren om att godkänna behörigheten för appen.
     * @author JD
     */
    private void askPermission() {
        requestPermissions(
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSIONS_REQUEST_LOCATION
        );
    }

    /**
     * Denna metod hämtar resultatet av behörighetsbegäran som gjordes. Den kontrollerar om behörighet för enhetens GPS-position har godkändes eller ej av användaren.
     * @author JD
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
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
