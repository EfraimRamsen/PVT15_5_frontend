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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.model.Challenge;
import se.su.dsv.pvt.helloworldapp.model.OutdoorGym;
import se.su.dsv.pvt.helloworldapp.model.Place;
import se.su.dsv.pvt.helloworldapp.rest.BackendApiService;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private int totalChallenges = 0; // TODO: detta bör fixas, dvs. kopplas ihop med databasen.
  
    final Fragment challengeFragment = new ChallengeFragment();
    final Fragment addChallengeFragment = new AddChallengeFragment();
    final Fragment mapViewFragment = new MapViewFragment();
    final FragmentManager fm = getSupportFragmentManager();
    BottomNavigationView bottomNavigation;

    Fragment active = challengeFragment;
    Fragment active2 = active;
    Intent intent;
    List<OutdoorGym> outdoorGyms;
    List<Challenge> activeChallengesList = new ArrayList<>();
    List<Challenge> completedChallengesList = new ArrayList<>();

    private Place openThisPlaceFragment = null; // ugly solution to a problem.

    private static final String TAG = MainActivity.class.getSimpleName(); // ignorera
    public static final  String BASE_URL = "https://pvt.dsv.su.se/Group05/";
    private static Retrofit retrofit = null;
    OkHttpClient okHttpClient;
    BackendApiService backendApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Här väljs vy-fil! Finns i /res/toptoolbar-mappen.

        bottomNavigation = findViewById(R.id.bottom_navigation);

        toolbar = findViewById(R.id.my_toolbar);
        toolbar.inflateMenu(R.menu.toptoolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId()==R.id.update_map_item)
                {
                    getGymApiData();
                    return true;
                }
                else if(item.getItemId()== R.id.report_gym_item)
                {
                    openReportWebPage();
                    return true;
                }
                else{
                    return false;
                }
            }
        });


        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navListener);

        //fm.beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();

        intent = getIntent();
        if (intent.hasExtra("my")) {
            bottomNavigation.setSelectedItemId(R.id.nav_challenges);
            fm.beginTransaction().add(R.id.fragment_container, mapViewFragment, "3").hide(mapViewFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, addChallengeFragment, "2").hide(addChallengeFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, challengeFragment, "1").commit();
            active = challengeFragment;
        } else if (intent.hasExtra("add")) {
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

        createConnectionToApi();
        getGymApiData();
    }


    //visar det valda fragmnetet och döljer det som va aktivt innan

    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        /**
         * lyssnare till bottomnav
         * @param item
         * @author Gosia
         */
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            TextView title = (TextView) findViewById(R.id.main_title_text);
            fm.beginTransaction().hide(active2).commit();
            switch (item.getItemId()) {
                case R.id.nav_challenges:
                    fm.popBackStack();
                    fm.beginTransaction().hide(active).show(challengeFragment).commit();
                    active = challengeFragment;
                    title.setText(R.string.challenges);
                    return true;

                case R.id.nav_add_challenge:
                    fm.popBackStack();
                    fm.beginTransaction().hide(active).show(addChallengeFragment).commit();
                    active = addChallengeFragment;
                    title.setText(R.string.add_challenge);
                    return true;

                case R.id.nav_map:
                    fm.popBackStack();
                    fm.beginTransaction().hide(active).show(mapViewFragment).commit();
                    active = mapViewFragment;
                    title.setText(R.string.map);
                    return true;
            }
            return false;
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation, menu);

//        MenuItem item = menu.findItem(R.id.action_bar_spinner);
//        Spinner spinner = (Spinner) item.getActionView();
//
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_list_item_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinner.setAdapter(adapter);

        return true;
    }


    /**
     * Blabla detta är.........
     * @param v = view  för någonting
     * @return sak = returnerar en sak för blabla
     * @author JD
     */
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void createConnectionToApi() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        }
        backendApiService = retrofit.create(BackendApiService.class);
    }

    public void getGymApiData() {
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

    public void createChallengeApiData() {
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

                    Log.d(TAG, "Sent data: " + outdoorGyms);


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

    private void openReportWebPage() {
        // Tre raderna nedan är för om man implementerar webbsidan som fragment
//        Fragment reportWebPage = new ReportWebPageFragment();
//        fm.beginTransaction().hide(active).add(R.id.fragment_container, reportWebPage).addToBackStack("report").commit();
//        active = reportWebPage;

        Intent intent = new Intent(this, ReportWebPageActivity.class);
        startActivity(intent);
    }

    /*

    public void createChallenge(View v){
        EditText challenge = (EditText) v.findViewById(R.id.challengeText);
        String cString = challenge.getText().toString();
        EditText description = v.findViewById(R.id.descriptionText);
        String dString = description.getText().toString();
        TextView date = v.findViewById(R.id.date);
        TextView time = v.findViewById(R.id.time);
        Challenge c = new Challenge(cString, dString, 1,totalChallenges+1,0,"date", 0000);
        totalChallenges++;
        active = challengeFragment;
    }*/

    /**
     * Nedanstående metoder används för att koppla ihop olika fragment med MainActivity-klassen.
     * TODO: koppla ihop dem med backenden.
     *
     */

    public void addChallengeNumber() {
        totalChallenges++;
    }
    public int getChallengeNumber() {
        return totalChallenges;
    }
    public int getCompletedChallangeNumber() {
        try {
            return completedChallengesList.size();
        }
        catch (Exception e) {
            System.err.println(e);
        }
        return 0;
    }
    public void setActive() {
        active = challengeFragment;
    }
    public void addActiveChallenge(Challenge c) {
        activeChallengesList.add(c);
    }
    public void removeActiveChallenge(Challenge c) {
        activeChallengesList.remove(c);
    }
    public void setChallengeToComplete(Challenge c) {
        completedChallengesList.add(c);
        removeActiveChallenge(c);
    }

    /**
     * klasser som används för att koppla ihop fragment, mainactivity och backend slutar här.
     */

    /**
     * These three methods lets MapViewFragment send objects to LocationViewFragment.
     * @return
     * @author Niklas Edström
     */
    public Place getOpenThisPlaceFragment() {
        Place p = openThisPlaceFragment;
        removeOpenThisPlaceFragment();
        return p;
    }
    public void setOpenThisPlaceFragment(Place openThisPlaceFragment) {
        this.openThisPlaceFragment = openThisPlaceFragment;
    }
    public void removeOpenThisPlaceFragment() {
        this.openThisPlaceFragment = null;
    }

    /**
     * Visar upp ett locationfragment som man kan backa ut från
     * @author Gosia
     */
    public void showLocation() {
        Fragment locationViewFragment = new LocationViewFragment();
        fm.beginTransaction().hide(active).add(R.id.fragment_container, locationViewFragment).addToBackStack("back").commit();
        active2 = locationViewFragment;
    }

    /*@Override
    public void onBackPressed() {
        Fragment  f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (active instanceof LocationViewFragment) {
            bottomNavigation.setSelectedItemId(R.id.nav_map);
            active = mapViewFragment;
        }  else if (active instanceof MapViewFragment) {
            finish();
        } else if (active instanceof AddChallengeFragment) {
            finish();
        } else if (active instanceof ChallengeFragment) {
            finish();
        } else {
            super.onBackPressed();
        }
    }*/
}
