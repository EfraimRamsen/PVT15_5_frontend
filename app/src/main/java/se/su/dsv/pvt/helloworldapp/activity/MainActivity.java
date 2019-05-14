package se.su.dsv.pvt.helloworldapp.activity;

import se.su.dsv.pvt.helloworldapp.fragment.*;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import se.su.dsv.pvt.helloworldapp.R;

public class MainActivity extends AppCompatActivity {

    final Fragment fragment1 = new ChallengeFragment();
    final Fragment fragment2 = new AddChallengeFragment();
    final Fragment fragment3 = new MapViewFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;
    Intent intent;


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

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

}
