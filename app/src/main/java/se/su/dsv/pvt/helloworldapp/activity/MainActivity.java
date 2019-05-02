package se.su.dsv.pvt.helloworldapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import se.su.dsv.pvt.helloworldapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Här väljs vy-fil! Finns i /res/layout-mappen.
        Toolbar toolbar = (Toolbar) TopActionBar.getToolbar("title", findViewById(R.id.my_toolbar));
        setSupportActionBar(toolbar);

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /**
         * Denna klass borde abstraheras iväg, dvs. man skickar iväg MenuItem item i guess???
         */
        switch(item.getItemId()) {
            case R.id.back_button:
                System.out.println("loool");
                Intent intent = new Intent(this, getParent().getClass());
                startActivity(intent);
                return true;

            default:
                System.out.println("defulllt");

                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toptoolbar, menu);
        return true;
    }
}
