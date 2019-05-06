package se.su.dsv.pvt.helloworldapp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import se.su.dsv.pvt.helloworldapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Här väljs vy-fil! Finns i /res/layout-mappen.
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_profile:
                    selectedFragment = new ProfileFragment();
                    break;
                case R.id.nav_map:
                    selectedFragment = new MapFragment();
                    break;
                case R.id.nav_challenges:
                    selectedFragment = new ChallengeFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;

        }
    };
}


    /**
     * Den här och den efterföljande metoden startar aktiviteter, och i det här fallet är
     * aktiviteten en vy. Intent-objektet behövs för att välja vart vi ska: detta genom xxx.class-
     * ordet. En intent kan innehålla en mängd andra saker, men ja. Sen används metoden startActivity()
     * för att starta aktiviteten.
     *
     */
//    public void gotoProfile(View view) {
//        Intent intent = new Intent(this, Profile.class);
//        startActivity(intent);
//    }
//    public void gotoMap(View view) {
//        Intent intent = new Intent(this, Map.class);
//        startActivity(intent);
//    }

