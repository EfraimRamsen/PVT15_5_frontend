package se.su.dsv.pvt.helloworldapp.activity;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.model.Challenge;
import se.su.dsv.pvt.helloworldapp.model.OutdoorGym;
import se.su.dsv.pvt.helloworldapp.model.Participation;
import se.su.dsv.pvt.helloworldapp.model.Place;
import se.su.dsv.pvt.helloworldapp.rest.BackendApiService;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
  
    final Fragment myProfileFragment = new MyProfileFragment();
    Fragment addChallengeFragment = new AddChallengeFragment();
    final Fragment mapViewFragment = new MapViewFragment();
    final FragmentManager fm = getSupportFragmentManager();
    BottomNavigationView bottomNavigation;

    Fragment active = myProfileFragment;
    Fragment active2 = active;
    Intent intent;

    // Här sparas alla hämtade gym. /JD
    List<OutdoorGym> outdoorGyms;

    List<Challenge> activeChallengesList = new ArrayList<>();
    List<Challenge> completedChallengesList = new ArrayList<>();

    // Här sparas alla deltagande-objekt med de utmaningar en viss användare är med i. /JD
    List<Participation> participationList;

    // Här sparas alla utmaningar en viss användare är med i. /JD
    ArrayList<Challenge> userChallengesList;

    private Place openThisPlaceFragment = null; // ugly solution to a problem.

    private static int userID;
    private static String userName;

    //  TAG används för logg/debug i Android, innehåller bara namnet på klassen. /JD
    private static final String TAG = MainActivity.class.getSimpleName(); // ignorera

    // API:ns grundläggande URL. /JD
    public static final  String BASE_URL = "https://pvt.dsv.su.se/Group05/";

    private static Retrofit retrofit = null;
    OkHttpClient okHttpClient;
    BackendApiService backendApiService;
    String responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Här väljs vy-fil! Finns i /res/toptoolbar-mappen.

        intentHandler();

        bottomNavigation = findViewById(R.id.bottom_navigation);

        toolbar = findViewById(R.id.my_toolbar);
        toolbar.inflateMenu(R.menu.toptoolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId()==R.id.update_map_item)
                {
                    getAllGymsCall();
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

        createConnectionToApi();
        getParticipationCall(userID);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navListener);

        //fm.beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();

        // Nedan if-satser hämtar intent från föregående activity. Innehållet i intent säger vilket fragment som ska visas först. /JD
        if (intent.hasExtra("my")) {
            bottomNavigation.setSelectedItemId(R.id.nav_my_profile);
            fm.beginTransaction().add(R.id.fragment_container, mapViewFragment, "3").hide(mapViewFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, addChallengeFragment, "2").hide(addChallengeFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, myProfileFragment, "1").commit();
            active = myProfileFragment;
        } else if (intent.hasExtra("add")) {
            bottomNavigation.setSelectedItemId(R.id.nav_add_challenge);
            fm.beginTransaction().add(R.id.fragment_container, mapViewFragment, "3").hide(mapViewFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, myProfileFragment, "1").hide(myProfileFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, addChallengeFragment, "2").commit();
            active = addChallengeFragment;
        } else if (intent.hasExtra("find")) {
            bottomNavigation.setSelectedItemId(R.id.nav_map);
            fm.beginTransaction().add(R.id.fragment_container, addChallengeFragment, "2").hide(addChallengeFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, myProfileFragment, "1").hide(myProfileFragment).commit();
            fm.beginTransaction().add(R.id.fragment_container, mapViewFragment, "3").commit();
            active = mapViewFragment;
        }

        //Lägger till fragmenten i fragment_container, och döljer fragment 2 och 3
//        fm.beginTransaction().add(R.id.fragment_container, mapViewFragment, "3").hide(mapViewFragment).commit();
//        fm.beginTransaction().add(R.id.fragment_container, addChallengeFragment, "2").hide(addChallengeFragment).commit();
//        fm.beginTransaction().add(R.id.fragment_container,myProfileFragment, "1").commit();
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
                case R.id.nav_my_profile:
                    fm.popBackStack();
                    fm.beginTransaction().hide(active).show(myProfileFragment).commit();
                    active = myProfileFragment;
                    title.setText(R.string.challenges);
                    getUserChallengesCall(userID);
                    getParticipationCall(userID);
                    return true;

                case R.id.nav_add_challenge:
                    fm.popBackStack();
                    fm.beginTransaction().hide(active).show(addChallengeFragment).commit();
                    active = addChallengeFragment;
                    title.setText(R.string.add_challenge);
                    return true;

                case R.id.nav_map:
                    fm.popBackStack();
                    getAllGymsCall(); // kan behöva ladda om fragmentet för att visa nya utmaningar och ny info om gymmen
                    getParticipationCall(userID);
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

        return true;
    }

    private void intentHandler() {
        intent = getIntent();
        userID = intent.getIntExtra("userID", 0);
        userName = intent.getStringExtra("userName");
    }

    public static int getUserID() {
        return userID;
    }

    public static String getUserName() {
        return userName;
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    //*******************************************************************************************************************
    //************************************ BEGINNING OF API-CALLS *******************************************************
    //*******************************************************************************************************************

    /**
     * Denna metod instansierar själva HTTP-handlern samt JSON-handlern. Dessutom läggs en interceptor till för att läsa av requests och responses via HTTP.
     * @author JD
     */
    public void createConnectionToApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        backendApiService = retrofit.create(BackendApiService.class);
    }

    /**
     * Denna metod hämtar alla gym från API:n, sparar de i en lista och anropar metoderna som sätter ut gym på kartan.
     * @author JD
     */
    public void getAllGymsCall() {
        Call<List<OutdoorGym>> call = backendApiService.getAllGyms();

        call.enqueue(new Callback<List<OutdoorGym>>() {
            @Override
            public void onResponse(Call<List<OutdoorGym>> call, Response<List<OutdoorGym>> response) {
                try {
                    outdoorGyms = response.body();

                    // behövs för av någon anledning deklareras inte Location direkt när man hämtar från json
                    for (OutdoorGym outdoorGym : outdoorGyms) {
                        outdoorGym.getLocation().setLatLng(outdoorGym.getLocation().getX(), outdoorGym.getLocation().getY());
                        outdoorGym.ifDescIsNull();

                        for (Challenge challenge : outdoorGym.getChallengeList()) {
                            challenge.setTimeAndDate();
                        }
                    }

                    Log.d(TAG, "Response data: " + outdoorGyms);

                    addGymToMapMarkers();
                    ((MyProfileFragment) myProfileFragment).setOutdoorGyms(outdoorGyms);
                } catch (NullPointerException e) {
                    System.out.println("GET - all gyms: API-response contained null.");
                    Log.d(TAG, "GET - all gyms: API-response contained null.");
                    System.out.println("error: " + e);
                }
            }
            @Override
            public void onFailure(Call<List<OutdoorGym>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    /**
     * Denna metod skickar värdet för rankningen som en användare valt att ranka ett visst gym.
     * @author JD
     * @param gymID
     * @param userID
     * @param rate
     */
    public String rateGymCall(int gymID, int userID, int rate) {
        try {
            Call<ResponseBody> call = backendApiService.rateGym(gymID, userID, rate);
            return call.execute().body().string();
        } catch (IOException | NullPointerException e) {
            System.out.println("Rate gym failed: " + e);
        }
        return null;

//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                try {
//                    Log.d(TAG, "Response data: " + response.body());
//                    responseText = response.body();
//                } catch (NullPointerException e) {
//                    System.out.println("POST - rate gym: API-response contained null.");
//                    Log.d(TAG, "POST - rate gym: API-response contained null.");
//                }
//            }
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.e(TAG, "Felmeddelande: " +  t.toString());
//            }
//        });
//
//        return responseText;
    }

    /**
     * Denna metod anropas när man skapat ett gym. Metoden skickar med ett challenge-objekt i HTTP-request till backend.
     * @param challenge
     * @author JD
     */
    public void createChallengeCall(int userID, Challenge challenge) {
        Call<String> call = backendApiService.createNewChallengeRequest(userID, challenge);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    Log.d(TAG, "Response data: " + response.body().toString());
                } catch (NullPointerException e) {
                    System.out.println("POST - create challenge: API-response contained null.");
                    Log.d(TAG, "POST - create challenge: API-response contained null.");
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "Felmeddelande: " +  t.toString());
            }
        });
    }

    /**
     * Denna metod tar bort en existerande utmaning från databasen.
     * @author JD
     * @param challengeID
     */
    public void removeChallengeCall(int challengeID) {
        Call<String> call = backendApiService.removeChallenge(challengeID);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    Log.d(TAG, "Response data: " + response.body());
                } catch (NullPointerException e) {
                    System.out.println("PUT - remove challenge: API-response contained null.");
                    Log.d(TAG, "PUT - remove challenge: API-response contained null.");
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "Felmeddelande: " +  t.toString());
            }
        });
    }

    /**
     * Denna metod hämtar alla utmaningar som en specifik användare är med i.
     * @author JD
     * @param userID
     */
    public void getUserChallengesCall(int userID) {
        Call<ArrayList<Challenge>> call = backendApiService.getUserChallenges(userID);

        call.enqueue(new Callback<ArrayList<Challenge>>() {
            @Override
            public void onResponse(Call<ArrayList<Challenge>> call, Response<ArrayList<Challenge>> response) {
                try {
                    userChallengesList = response.body();

                    for (Challenge challenge : userChallengesList) {
                        challenge.setTimeAndDate();
                    }

                    Log.d(TAG, "Response data: " + response.body());

                    ((MyProfileFragment) myProfileFragment).setChallenges(userChallengesList);
                    ((MyProfileFragment) myProfileFragment).showUsersChallenges();

                } catch (NullPointerException e) {
                    System.out.println("GET - user challenges: API-response contained null.");
                    Log.d(TAG, "GET - user challenges: API-response contained null.");
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Challenge>> call, Throwable t) {
                Log.e(TAG, "Felmeddelande: " +  t.toString());
            }
        });
    }

    /**
     * Denna metod markerar att en specifik utmaning är slutförd av en viss användare.
     * @author JD
     * @param participationID
     */
    public void completeChallengeCall(int participationID) {
        Call<String> call = backendApiService.completeChallenge(participationID);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    Log.d(TAG, "Response data: " + response.body());
                } catch (NullPointerException e) {
                    System.out.println("PUT - complete challenge: API-response contained null.");
                    Log.d(TAG, "PUT - complete challenge: API-response contained null.");
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "Felmeddelande: " +  t.toString());
            }
        });
    }

    /**
     * Denna metod anger att en specifik användare vill gå med i en viss utmaning.
     * @author JD
     * @param userID
     * @param challengeID
     */
    public void createChallengeParticipationCall(int userID, int challengeID) {
        Call<String> call = backendApiService.createParticipation(userID, challengeID);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    Log.d(TAG, "Response data: " + response.body());
                } catch (NullPointerException e) {
                    System.out.println("POST - create participation: API-response contained null.");
                    Log.d(TAG, "POST - create participation: API-response contained null.");
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "Felmeddelande: " +  t.toString());
            }
        });
    }

    /**
     * Denna metod hämtar en lista över alla utmaningar en viss användare är med som deltagare i.
     * @author JD
     * @param userID
     */
    public void getParticipationCall(int userID) {
        Call<ArrayList<Participation>> call = backendApiService.getParticipation(userID);

        call.enqueue(new Callback<ArrayList<Participation>>() {
            @Override
            public void onResponse(Call<ArrayList<Participation>> call, Response<ArrayList<Participation>> response) {
                try {
                    participationList = response.body();
                    Log.d(TAG, "Response data: " + response.body());
                    ((MyProfileFragment) myProfileFragment).setParticipationList(participationList);
                } catch (NullPointerException e) {
                    System.out.println("GET - user participation: API-response contained null.");
                    Log.d(TAG, "GET - user participation: API-response contained null.");
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Participation>> call, Throwable t) {
                Log.e(TAG, "Felmeddelande: " +  t.toString());
            }
        });
    }

    /**
     * Denna metod tar bort en viss användare som deltagare på en utmaning.
     * @author JD
     * @param participationID
     */
    public void removeParticipationCall(int participationID) {
        Call<String> call = backendApiService.removeParticipation(participationID);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    Log.d(TAG, "Response data: " + response.body());
                } catch (NullPointerException e) {
                    System.out.println("PUT - remove participation: API-response contained null.");
                    Log.d(TAG, "PUT - remove participation: API-response contained null.");
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "Felmeddelande: " +  t.toString());
            }
        });
    }

    //*******************************************************************************************************************
    //****************************************** END OF API-CALLS *******************************************************
    //*******************************************************************************************************************

    /**
     * Metoden skickar användaren till ny activity som öppnar en webbsida till Stockholm Stads rapporteringssida.
     * @author JD
     */
    private void openReportWebPage() {
        // Tre raderna nedan är för om man implementerar webbsidan som fragment
//        Fragment reportWebPage = new ReportWebPageFragment();
//        fm.beginTransaction().hide(active).add(R.id.fragment_container, reportWebPage).addToBackStack("report").commit();
//        active = reportWebPage;

        Intent intent = new Intent(this, ReportWebPageActivity.class);
        startActivity(intent);
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
    public List<Participation> getParticipationList() {
        return participationList;
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
     * Visar upp ett fragment som man kan backa ut från
     * @author Gosia
     */
    public void showSomeFragment(Fragment someFragment) {
        fm.beginTransaction().hide(active).add(R.id.fragment_container, someFragment).addToBackStack("back").commit();
        active2 = someFragment;
    }

    /**
     * Anropar tömning av textfälten och återställning av spinnern
     * @author Gosia, JD
     */
    public void clearAddChallenge(){
        ((AddChallengeFragment)addChallengeFragment).resetFields();
    }

    private void addGymToMapMarkers() {
        ((MapViewFragment) mapViewFragment).addOutdoorGymList(outdoorGyms);
        ((MapViewFragment) mapViewFragment).addAllPlacesToMap();
    }

//    public String findPlaceById(int workoutPlaceID) {
//        String name = null;
//        for (Place place : outdoorGyms) {
//            if (place.getId() == workoutPlaceID) {
//                System.out.println("sant");
//                name = place.getName();
//                return name;
//            }
//        }
//        System.out.println("falskt");
//
//        return "teeeest";
//    }
}
