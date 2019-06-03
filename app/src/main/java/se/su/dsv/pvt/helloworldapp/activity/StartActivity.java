package se.su.dsv.pvt.helloworldapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import se.su.dsv.pvt.helloworldapp.R;

public class StartActivity extends Activity implements View.OnClickListener {

    // konstanter som motsvarar fragmentet knappen leder till /JD
    final int ADD_CHALLENGES_VIEW = 1;
    final int MY_CHALLENGES_VIEW = 2;
    final int FIND_CHALLENGES_VIEW = 3;

    private static int userID;
    private static String userName;
    Intent infoIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        handleIntentPassthrough();

        ImageButton addBtn = (ImageButton) findViewById(R.id.plusBtn);
        addBtn.setOnClickListener(this);
        ImageButton myChallengesBtn = (ImageButton) findViewById(R.id.myChallengesBtn);
        myChallengesBtn.setOnClickListener(this);
        ImageButton findChallangesBtn = (ImageButton) findViewById(R.id.mapBtn);
        findChallangesBtn.setOnClickListener(this);
    }

    // Knapparna på startsidan som leder till specifika fragment. Intent används för att nästa activity ska avgöra vilket fragment som ska visas. /JD
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.plusBtn) {
            System.out.println("add challenge");
            infoIntent.putExtra("add", ADD_CHALLENGES_VIEW);
            infoIntent.removeExtra("my");
            infoIntent.removeExtra("find");
            v.getContext().startActivity(infoIntent);
        } else if (v.getId() == R.id.myChallengesBtn) {
            System.out.println("mina utmaningar");
            infoIntent.putExtra("my", MY_CHALLENGES_VIEW);
            infoIntent.removeExtra("add");
            infoIntent.removeExtra("find");
            v.getContext().startActivity(infoIntent);
        } else if (v.getId() == R.id.mapBtn) {
            System.out.println("hitta challenge");
            infoIntent.putExtra("find", FIND_CHALLENGES_VIEW);
            infoIntent.removeExtra("add");
            infoIntent.removeExtra("my");
            v.getContext().startActivity(infoIntent);
        }
    }

    private void handleIntentPassthrough() {
        Intent intent = getIntent();
        userID = intent.getIntExtra("userID", 0);
        System.out.println(userID);
        userName = intent.getStringExtra("userName");
        System.out.println(userName);

        infoIntent = new Intent(this, MainActivity.class);
        infoIntent.putExtra("userID", userID);
        infoIntent.putExtra("userName", userName);
    }
}
