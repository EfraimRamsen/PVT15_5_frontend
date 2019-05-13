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

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.model.Challenge;
import se.su.dsv.pvt.helloworldapp.model.OutdoorGym;
import se.su.dsv.pvt.helloworldapp.rest.BackendApiService;

public class MainActivity extends AppCompatActivity {

    final Fragment fragment1 = new ChallengeFragment();
    final Fragment fragment2 = new AddChallengeFragment();
    final Fragment fragment3 = new MapViewFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;
    Intent intent;

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
            fm.beginTransaction().add(R.id.fragment_container, fragment3, "3").hide(fragment3).commit();
            fm.beginTransaction().add(R.id.fragment_container, fragment2, "2").hide(fragment2).commit();
            fm.beginTransaction().add(R.id.fragment_container,fragment1, "1").commit();
            active = fragment1;
        } else if (intent.hasExtra("my")) {
            bottomNavigation.setSelectedItemId(R.id.nav_add_challenge);
            fm.beginTransaction().add(R.id.fragment_container, fragment3, "3").hide(fragment3).commit();
            fm.beginTransaction().add(R.id.fragment_container, fragment1, "1").hide(fragment1).commit();
            fm.beginTransaction().add(R.id.fragment_container,fragment2, "2").commit();
            active = fragment2;
        } else if (intent.hasExtra("find")) {
            bottomNavigation.setSelectedItemId(R.id.nav_map);
            fm.beginTransaction().add(R.id.fragment_container, fragment2, "2").hide(fragment2).commit();
            fm.beginTransaction().add(R.id.fragment_container, fragment1, "1").hide(fragment1).commit();
            fm.beginTransaction().add(R.id.fragment_container,fragment3, "3").commit();
            active = fragment3;
        }

        //Lägger till fragmenten i fragment_container, och döljer fragment 2 och 3
//        fm.beginTransaction().add(R.id.fragment_container, fragment3, "3").hide(fragment3).commit();
//        fm.beginTransaction().add(R.id.fragment_container, fragment2, "2").hide(fragment2).commit();
//        fm.beginTransaction().add(R.id.fragment_container,fragment1, "1").commit();

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
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    titel.setText(R.string.challenges);
                    return true;

                case R.id.nav_add_challenge:
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    titel.setText(R.string.add_challenge);
                    return true;

                case R.id.nav_map:
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;
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

    /**
     * this corresponds with the button in the bottom of the custom marker-popup which shows
     * "+ visa mer".
     * @param v
     */
    public void markerButton(View v) {
        ;
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
        Call<OutdoorGym> call = backendApiService.getJsonResponse();


        call.enqueue(new Callback<OutdoorGym>() {
            @Override
            public void onResponse(Call<OutdoorGym> call, Response<OutdoorGym> response) {
                try {
                    LatLng location = response.body().getLocation(); // formatet på LatLng?
                    String name = response.body().getName();
                    int id = response.body().getId();
                    ArrayList<Challenge> challengeList = response.body().getChallengeList(); // kanske borde vara Array?
                    String description = response.body().getDescription();

                    Log.d(TAG, "Received data: " + location + ", " + name + ", "  + id + ", " + challengeList + ", " + description);
                } catch (NullPointerException e) {
                    System.out.println("API-data contained null.");
                    Log.d(TAG, "API-data contained null.");
                }

            }

            @Override
            public void onFailure(Call<OutdoorGym> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
