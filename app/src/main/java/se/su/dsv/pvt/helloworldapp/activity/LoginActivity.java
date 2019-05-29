package se.su.dsv.pvt.helloworldapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.model.Challenge;
import se.su.dsv.pvt.helloworldapp.rest.BackendApiService;

import static android.support.constraint.Constraints.TAG;

public class LoginActivity extends Activity implements View.OnClickListener {

	private int userId;
	BackendApiService backendApiService;
	OkHttpClient okHttpClient;
	Retrofit retrofit;
	public static final  String BASE_URL = "https://pvt.dsv.su.se/Group05/";

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
			EditText username = findViewById(R.id.username);
			String uString = username.getText().toString();

			EditText password = findViewById(R.id.password);
			String pString = password.getText().toString();

		if(v.getId() == R.id.login){
			createLoginCall(uString,pString,v);
		}
		else if(v.getId() == R.id.register){
			createRegisterCall(uString,pString,v);
		}
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

	public void createLoginCall(String username, String password, View v) {
		Call<String> call = backendApiService.login(username,password);

		call.enqueue(new Callback<String>() {
			@Override
			public void onResponse(Call<String> call, Response<String> response) {
				try {
					Log.d(TAG, "Response data: " + response.body().toString());
					userId =  Integer.parseInt(response.body().toString());
					Toast.makeText(LoginActivity.this, "Framgång!", Toast.LENGTH_SHORT).show();

					Intent intent = new Intent(LoginActivity.this, StartActivity.class);
					startActivity(intent);
					
				} catch (NullPointerException e) {
					System.out.println("createLoginCall contains null");
					Log.d(TAG, "createLoginCall contains null");
				}
			}
			@Override
			public void onFailure(Call<String> call, Throwable t) {
				Log.e(TAG, "Felmeddelande: " +  t.toString());
				Toast.makeText(LoginActivity.this, "Fel: "+t, Toast.LENGTH_SHORT).show();
			}
		});
	}

	public void createRegisterCall(String username, String password, View v){
		Call<String> call = backendApiService.register(username,password);

		call.enqueue(new Callback<String>() {
			@Override
			public void onResponse(Call<String> call, Response<String> response) {
				try{
					Log.d(TAG,"Response data: " + response.body().toString());
					userId =  Integer.parseInt(response.body().toString());
					Toast.makeText(LoginActivity.this, "Framgång!", Toast.LENGTH_SHORT).show();

					Intent intent = new Intent(LoginActivity.this, StartActivity.class);
					startActivity(intent);

				}catch (NullPointerException e){
					System.out.println("createRegisterCall contains null");
					Log.d(TAG,  "createRegisterCall contains null");
				}

			}
			@Override
			public void onFailure(Call<String> call, Throwable t) {
				Log.e(TAG, "Felmeddelande: " + t.toString());
				Toast.makeText(LoginActivity.this, "Fel: "+t, Toast.LENGTH_SHORT).show();

			}
		});
	}

	public int getUserId(){
		return userId;
	}
}
