package se.su.dsv.pvt.helloworldapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import se.su.dsv.pvt.helloworldapp.R;

public class StartActivity extends Activity implements View.OnClickListener {

    final int PROFILE_VIEW = 1;
    final int MY_CHALLENGES_VIEW = 2;
    final int FIND_CHALLENGES_VIEW = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageButton profileBtn = (ImageButton) findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(this);
        ImageButton myChallengesBtn = (ImageButton) findViewById(R.id.myChallengesBtn);
        myChallengesBtn.setOnClickListener(this);
        ImageButton findChallangesBtn = (ImageButton) findViewById(R.id.mapBtn);
        findChallangesBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.profileBtn) {
            System.out.println("profil");
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("profile", PROFILE_VIEW);
            v.getContext().startActivity(intent);
        } else if (v.getId() == R.id.myChallengesBtn) {
            System.out.println("mina utmaningar");
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("my", MY_CHALLENGES_VIEW);
            v.getContext().startActivity(intent);
        } else if (v.getId() == R.id.mapBtn) {
            System.out.println("hitta challenge");
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("find", FIND_CHALLENGES_VIEW);
            v.getContext().startActivity(intent);
        }
    }
}
