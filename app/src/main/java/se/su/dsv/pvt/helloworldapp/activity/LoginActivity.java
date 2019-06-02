package se.su.dsv.pvt.helloworldapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.rest.BackendApiService;

import static android.support.constraint.Constraints.TAG;

public class LoginActivity extends Activity implements View.OnClickListener {

	private int userId;
	private String userName;
	BackendApiService backendApiService;
	OkHttpClient okHttpClient;
	Retrofit retrofit;
	public static final String BASE_URL = "https://pvt.dsv.su.se/Group05/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		createConnectionToApi();

		Button login = findViewById(R.id.login);
		login.setOnClickListener(this);

		Button register = findViewById(R.id.register);
		register.setOnClickListener(this);

	}

	@Override
	public void onClick(View v){
			EditText usernameField = findViewById(R.id.username);
			userName = usernameField.getText().toString().trim();

			EditText passwordField = findViewById(R.id.password);
			String password = passwordField.getText().toString();

		if (userName.equals("")) {
			Toast.makeText(this, "Fyll i användarnamn", Toast.LENGTH_SHORT).show();
		} else if (password.equals("")) {
			Toast.makeText(this, "Fyll i lösenord", Toast.LENGTH_SHORT).show();
		} else {
			if(v.getId() == R.id.login){
				loginHandler(userName,password);
			}
			else if(v.getId() == R.id.register){
				registerHandler(userName, password);
			}
		}
	}

	private void loginHandler(String username, String password) {
		Call<ResponseBody> call = backendApiService.login(username, password);
		new LoginTask().execute(call);
	}

	private void registerHandler(String username, String password) {
		Call<ResponseBody> call = backendApiService.register(username, password);
		new RegisterTask().execute(call);
	}


	public void createConnectionToApi() {
		Gson gson = new GsonBuilder()
				.setLenient()
				.create();

		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		okHttpClient = new OkHttpClient.Builder()
				.addInterceptor(interceptor)
				.connectTimeout(1, TimeUnit.MINUTES)
				.readTimeout(30, TimeUnit.SECONDS)
				.writeTimeout(15, TimeUnit.SECONDS)
				.build();

		if (retrofit == null) {
			retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build();
		}
		backendApiService = retrofit.create(BackendApiService.class);
	}

	public String createLoginCall(Call<ResponseBody> call) {
		try {
//			Call<ResponseBody> call = backendApiService.login(userName, password);
			return call.execute().body().string();
		} catch (IOException | NullPointerException e) {
			Toast.makeText(LoginActivity.this, "Ett fel inträffade", Toast.LENGTH_SHORT).show();
			System.out.println("Login failed: " + e);
		}
		return null;

//		call.enqueue(new Callback<ResponseBody>() {
//			@Override
//			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//				try {
//					Log.d(TAG, "Response data: " + response.body().toString());
//					userId =  Integer.parseInt(response.body().toString());
//					Toast.makeText(LoginActivity.this, "Inloggning lyckades", Toast.LENGTH_SHORT).show();
//
//					Intent intent = new Intent(LoginActivity.this, StartActivity.class);
//					startActivity(intent);
//
//				} catch (NullPointerException e) {
//					System.out.println("createLoginCall contains null");
//					Log.d(TAG, "createLoginCall contains null");
//				}
//			}
//			@Override
//			public void onFailure(Call<ResponseBody> call, Throwable t) {
//				Log.e(TAG, "Felmeddelande: " +  t.toString());
//				Toast.makeText(LoginActivity.this, "Fel inträffade: "+t, Toast.LENGTH_SHORT).show();
//			}
//		});
	}

	public String createRegisterCall(Call<ResponseBody> call){
		try {
			return call.execute().body().string();
		} catch (IOException | NullPointerException e) {
			Toast.makeText(LoginActivity.this, "Ett fel inträffade", Toast.LENGTH_SHORT).show();
			System.out.println("Sign up failed: " + e);
		}
		return null;

//		Call<String> call = backendApiService.register(username,password);
//
//		call.enqueue(new Callback<String>() {
//			@Override
//			public void onResponse(Call<String> call, Response<String> response) {
//				try{
//					Log.d(TAG,"Response data: " + response.body().toString());
//					userId =  Integer.parseInt(response.body().toString());
//					Toast.makeText(LoginActivity.this, "Framgång!", Toast.LENGTH_SHORT).show();
//
//					Intent intent = new Intent(LoginActivity.this, StartActivity.class);
//					startActivity(intent);
//
//				}catch (NullPointerException e){
//					System.out.println("createRegisterCall contains null");
//					Log.d(TAG,  "createRegisterCall contains null");
//				}
//
//			}
//			@Override
//			public void onFailure(Call<String> call, Throwable t) {
//				Log.e(TAG, "Felmeddelande: " + t.toString());
//				Toast.makeText(LoginActivity.this, "Fel: "+t, Toast.LENGTH_SHORT).show();
//
//			}
//		});
	}

	public int getUserId(){
		return userId;
	}

	private class LoginTask extends AsyncTask<Call, Void, String> {
		@Override
		protected String doInBackground(Call... params) {
			try {
                Call<ResponseBody> call = params[0];
				return createLoginCall(call);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result != null) {
				if (result.equals("Fail, user does not exist") || result.equals("Password is wrong")) {
					Toast.makeText(LoginActivity.this, "Fel användarnamn eller lösenord", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(LoginActivity.this, "Inloggning lyckades", Toast.LENGTH_SHORT).show();
					userId =  Integer.parseInt(result);
					Intent intent = new Intent(LoginActivity.this, StartActivity.class);
					intent.putExtra("userID", userId);
					intent.putExtra("userName", userName);
					System.out.println("id är: " + userId + " & username är: " + userName);
					startActivity(intent);
				}
			} else {
				System.out.println("Login response contained null");
			}
		}
	}

	private class RegisterTask extends AsyncTask<Call, Void, String> {
		@Override
		protected String doInBackground(Call... params) {
			try {
				Call<ResponseBody> call = params[0];
				return createRegisterCall(call);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result != null) {
					Toast.makeText(LoginActivity.this, "Registrering lyckades", Toast.LENGTH_SHORT).show();
					userId =  Integer.parseInt(result);
					Intent intent = new Intent(LoginActivity.this, StartActivity.class);
					intent.putExtra("userID", userId);
					intent.putExtra("userName", userName);
					System.out.println("id är: " + userId + " & username är: " + userName);
					startActivity(intent);

			} else {
				System.out.println("Register response contained null");
			}
		}
	}
}
