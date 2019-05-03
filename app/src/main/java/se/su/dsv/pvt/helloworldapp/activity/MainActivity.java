package se.su.dsv.pvt.helloworldapp.activity;

import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import se.su.dsv.pvt.helloworldapp.R;

public class MainActivity extends AppCompatActivity {

    // Used for the top title bar:
    public static final String viewTitle = "Witness-my-fitness";
    public static final int titleIcon = R.drawable.ic_main;
    public static final int iconId = R.id.main_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Här väljs vy-fil! Finns i /res/toptoolbar-mappen.
        // Create toolbar:
        Toolbar toolbar = (Toolbar) TopActionBar.getToolbar(viewTitle
                , findViewById(R.id.my_toolbar), true);
        setSupportActionBar(toolbar);
        ImageView iV = (ImageView) toolbar.findViewById(iconId);
        iV.setImageDrawable(ResourcesCompat.getDrawable(getResources(), titleIcon, null));

    }
    /**
     * Den här och den efterföljande metoden startar aktiviteter, och i det här fallet är
     * aktiviteten en vy. Intent-objektet behövs för att välja vart vi ska: detta genom xxx.class-
     * ordet. En intent kan innehålla en mängd andra saker, men ja. Sen används metoden startActivity()
     * för att starta aktiviteten.
     *
     */
    public void gotoProfile(View view) {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
    public void gotoMap(View view) {
        Intent intent = new Intent(this, Map.class);
        startActivity(intent);
    }

}
