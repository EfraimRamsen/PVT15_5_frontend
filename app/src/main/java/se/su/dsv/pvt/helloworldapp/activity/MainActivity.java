package se.su.dsv.pvt.helloworldapp.activity;

import se.su.dsv.pvt.helloworldapp.fragment.*;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.gms.maps.MapFragment;

import se.su.dsv.pvt.helloworldapp.R;

public class MainActivity extends AppCompatActivity {

    // Used for the top title bar:
    public static final int titleIcon = 0;
    public static final int iconId = R.id.main_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Här väljs vy-fil! Finns i /res/toptoolbar-mappen.

        // Create toolbar:
        Toolbar toolbar = (Toolbar) TopActionBar
                .getToolbar(findViewById(TopActionBar.getToolbarIntLink()), true);
        setSupportActionBar(toolbar);
        /*
        int windowWidth = TopActionBar.getWindowWidth(getWindowManager().getDefaultDisplay());
        toolbar.setTitleMarginStart(windowWidth / 4);
        toolbar.setTitleMarginEnd(windowWidth / 4);
        TextView tv = (TextView) toolbar.findViewById(R.id.main_title_text);
        tv.setText(getString(R.string.main_title));
        getSupportActionBar().setDisplayShowTitleEnabled(false);*/
        ImageView iV = (ImageView) toolbar.findViewById(iconId);
        iV.setImageDrawable(ResourcesCompat.getDrawable(getResources(), titleIcon, null));

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
                    selectedFragment = new MapViewFragment();
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
