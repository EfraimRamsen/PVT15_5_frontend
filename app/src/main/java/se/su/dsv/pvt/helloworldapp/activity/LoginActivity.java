package se.su.dsv.pvt.helloworldapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.SignInButton;

import se.su.dsv.pvt.helloworldapp.R;

public class LoginActivity extends AppCompatActivity {
	private static final String TAG = "AndroidClarified";
	private SignInButton googleSignInButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		googleSignInButton = findViewById(R.id.sign_in_button);

	}
}
