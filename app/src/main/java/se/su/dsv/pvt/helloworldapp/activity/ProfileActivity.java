package se.su.dsv.pvt.helloworldapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import se.su.dsv.pvt.helloworldapp.R;

public class ProfileActivity extends AppCompatActivity {
	public static final String GOOGLE_ACCOUNT = "google_account";
	private TextView profileName, profileEmail;
	private ImageView profileImage;
	private Button signOut;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tutorial_profile);
		profileName = findViewById(R.id.profile_text);
		profileEmail = findViewById(R.id.profile_email);
		profileImage = findViewById(R.id.profile_image);
		signOut=findViewById(R.id.sign_out);
	}

	private void setDataOnView() {
		GoogleSignInAccount googleSignInAccount = getIntent().getParcelableExtra(GOOGLE_ACCOUNT);
		Picasso.get().load(googleSignInAccount.getPhotoUrl()).centerInside().fit().into(profileImage);
		profileName.setText(googleSignInAccount.getDisplayName());
		profileEmail.setText(googleSignInAccount.getEmail());

	}

}
