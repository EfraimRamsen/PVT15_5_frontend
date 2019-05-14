package se.su.dsv.pvt.helloworldapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import se.su.dsv.pvt.helloworldapp.R;

public class StartActivity extends Activity implements View.OnClickListener {

    final int ADD_CHALLENGES_VIEW = 1;
    final int MY_CHALLENGES_VIEW = 2;
    final int FIND_CHALLENGES_VIEW = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageButton addBtn = (ImageButton) findViewById(R.id.plusBtn);
        addBtn.setOnClickListener(this);
        ImageButton myChallengesBtn = (ImageButton) findViewById(R.id.myChallengesBtn);
        myChallengesBtn.setOnClickListener(this);
        ImageButton findChallangesBtn = (ImageButton) findViewById(R.id.mapBtn);
        findChallangesBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.plusBtn) {
            System.out.println("add challenge");
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("add", ADD_CHALLENGES_VIEW);
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
