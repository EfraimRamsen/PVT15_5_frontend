package se.su.dsv.pvt.helloworldapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import se.su.dsv.pvt.helloworldapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Här väljs vy-fil! Finns i /res/layout-mappen.

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
