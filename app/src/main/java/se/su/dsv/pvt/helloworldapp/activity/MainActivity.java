package se.su.dsv.pvt.helloworldapp.activity;

import se.su.dsv.pvt.helloworldapp.fragment.*;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import se.su.dsv.pvt.helloworldapp.R;

public class MainActivity extends AppCompatActivity {

    final Fragment fragment1 = new ChallengeFragment();
    final Fragment fragment2 = new AddChallengeFragment();
    final Fragment fragment3 = new MapViewFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Här väljs vy-fil! Finns i /res/toptoolbar-mappen.

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navListener);

        //fm.beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();

        //Lägger till fragmenten i fragment_container, och döljer fragment 2 och 3
        fm.beginTransaction().add(R.id.fragment_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.fragment_container,fragment1, "1").commit();

    }

    //visar det valda fragmnetet och döljer det som va aktivt innan

    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_challenges:
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;

                case R.id.nav_add_challenge:
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    return true;

                case R.id.nav_map:
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;
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


}
