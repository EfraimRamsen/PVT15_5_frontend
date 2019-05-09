package se.su.dsv.pvt.helloworldapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import se.su.dsv.pvt.helloworldapp.R;

public class StartActivity extends Activity implements View.OnClickListener {

    final int PROFILE_VIEW = 1;
    final int MY_CHALLENGES_VIEW = 2;
    final int FIND_CHALLENGES_VIEW = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button profileBtn = (Button) findViewById(R.id.btnProfile);
        profileBtn.setOnClickListener(this);
        Button myChallengesBtn = (Button) findViewById(R.id.btnMyChallenges);
        myChallengesBtn.setOnClickListener(this);
        Button findChallangesBtn = (Button) findViewById(R.id.btnMyChallenges);
        findChallangesBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnProfile) {
            System.out.println("profil");
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("profile", PROFILE_VIEW);
            v.getContext().startActivity(intent);
        } else if (v.getId() == R.id.btnMyChallenges) {
            System.out.println("mina utmaningar");
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("my", MY_CHALLENGES_VIEW);
            v.getContext().startActivity(intent);
        } else if (v.getId() == R.id.btnFindChallenges) {
            System.out.println("hitta challenge");
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("find", FIND_CHALLENGES_VIEW);
            v.getContext().startActivity(intent);
        }
    }
}
