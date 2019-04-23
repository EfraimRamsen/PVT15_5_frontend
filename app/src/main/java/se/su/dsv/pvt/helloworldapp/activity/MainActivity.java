package se.su.dsv.pvt.helloworldapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.model.HelloWorldData;
import se.su.dsv.pvt.helloworldapp.se.su.dsv.pvt.helloworldapp.rest.HelloWorldApiService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    public static final  String BASE_URL = "https://pvt.dsv.su.se/Group05/";

    private static Retrofit retrofit = null;

    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectAndGetApiData();
    }

    public void connectAndGetApiData() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        HelloWorldApiService helloWorldApiService = retrofit.create(HelloWorldApiService.class);
        Call<HelloWorldData> call = helloWorldApiService.getJsonResponse();

        call.enqueue(new Callback<HelloWorldData>() {
            @Override
            public void onResponse(Call<HelloWorldData> call, Response<HelloWorldData> response) {
                String resultResponse = response.body().getResult();
                String nameResponse = response.body().getName();
                String ageResponse = response.body().getAge();

                resultText = (TextView) findViewById(R.id.responseText);
                resultText.setText(resultResponse);

                Log.d(TAG, "Received data (result, name, age): " + resultResponse + " " + nameResponse + " "
                + ageResponse);
            }

            @Override
            public void onFailure(Call<HelloWorldData> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }



}
