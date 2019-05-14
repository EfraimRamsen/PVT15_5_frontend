package se.su.dsv.pvt.helloworldapp.activity;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.su.dsv.pvt.helloworldapp.fragment.*;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.concurrent.TimeUnit;

import se.su.dsv.pvt.helloworldapp.R;
//import se.su.dsv.pvt.helloworldapp.model.Location;
import se.su.dsv.pvt.helloworldapp.model.Location;
import se.su.dsv.pvt.helloworldapp.model.OutdoorGym;
import se.su.dsv.pvt.helloworldapp.rest.BackendApiService;

public class MainActivity extends AppCompatActivity {

    final Fragment challengeFragment = new ChallengeFragment();
    final Fragment addChallengeFragment = new AddChallengeFragment();
    final Fragment mapViewFragment = new MapViewFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = challengeFragment;
    Intent intent;
    List<OutdoorGym> outdoorGyms;

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final  String BASE_URL = "https://pvt.dsv.su.se/Group05/";
    private static Retrofit retrofit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Här väljs vy-fil! Finns i /res/toptoolbar-mappen.

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navListener);

        //fm.beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();

        intent = getIntent();
        if (intent.hasExtra("profile")) {
            bottomNavigation.setSelectedItemId(R.id.nav_challenges);
            fm.beginTransaction().add(R.id.fragment_container, mapViewFragment, "3").hide(mapViewFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, addChallengeFragment, "2").hide(addChallengeFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, challengeFragment, "1").commit();
            active = challengeFragment;
        } else if (intent.hasExtra("my")) {
            bottomNavigation.setSelectedItemId(R.id.nav_add_challenge);
            fm.beginTransaction().add(R.id.fragment_container, mapViewFragment, "3").hide(mapViewFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, challengeFragment, "1").hide(challengeFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, addChallengeFragment, "2").commit();
            active = addChallengeFragment;
        } else if (intent.hasExtra("find")) {
            bottomNavigation.setSelectedItemId(R.id.nav_map);
            fm.beginTransaction().add(R.id.fragment_container, addChallengeFragment, "2").hide(addChallengeFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, challengeFragment, "1").hide(challengeFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, mapViewFragment, "3").commit();
            active = mapViewFragment;
        }

        //Lägger till fragmenten i fragment_container, och döljer fragment 2 och 3
//        fm.beginTransaction().add(R.id.fragment_container, mapViewFragment, "3").hide(mapViewFragment).commit();
//        fm.beginTransaction().add(R.id.fragment_container, addChallengeFragment, "2").hide(addChallengeFragment).commit();
//        fm.beginTransaction().add(R.id.fragment_container,challengeFragment, "1").commit();

        connectAndGetApiData();
    }

    //visar det valda fragmnetet och döljer det som va aktivt innan

    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {



        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            TextView titel = (TextView) findViewById(R.id.main_title_text);
            switch (item.getItemId()) {
                case R.id.nav_challenges:
                    fm.beginTransaction().hide(active).show(challengeFragment).commit();
                    active = challengeFragment;
                    titel.setText(R.string.challenges);
                    return true;

                case R.id.nav_add_challenge:
                    fm.beginTransaction().hide(active).show(addChallengeFragment).commit();
                    active = addChallengeFragment;
                    titel.setText(R.string.add_challenge);
                    return true;

                case R.id.nav_map:
                    fm.beginTransaction().hide(active).show(mapViewFragment).commit();
                    active = mapViewFragment;
                    titel.setText(R.string.map);
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void connectAndGetApiData() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        }

        BackendApiService backendApiService = retrofit.create(BackendApiService.class);
        Call<List<OutdoorGym>> call = backendApiService.getAllGymsResponse();


        call.enqueue(new Callback<List<OutdoorGym>>() {
            @Override
            public void onResponse(Call<List<OutdoorGym>> call, Response<List<OutdoorGym>> response) {
                try {
                    outdoorGyms = response.body();

                    // behövs för av någon anledning deklareras inte Location direkt när man hämtar från json
                    for (OutdoorGym outdoorGym : outdoorGyms) {
                        outdoorGym.getLocation().setLatLng(outdoorGym.getLocation().getX(), outdoorGym.getLocation().getY());
                    }

                    Log.d(TAG, "Received data: " + outdoorGyms);

                    ((MapViewFragment) mapViewFragment).addOutdoorGymList(outdoorGyms);
                    ((MapViewFragment) mapViewFragment).addAllPlacesToMap();
                } catch (NullPointerException e) {
                    System.out.println("API-data contained null.");
                    Log.d(TAG, "API-data contained null.");
                }

            }
            @Override
            public void onFailure(Call<List<OutdoorGym>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    // Används ej för tillfället
//    public List<OutdoorGym> getPlaces() {
//        return outdoorGyms;
//    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}
