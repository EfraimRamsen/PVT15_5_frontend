package se.su.dsv.pvt.helloworldapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.model.HelloWorldData;
import se.su.dsv.pvt.helloworldapp.se.su.dsv.pvt.helloworldapp.rest.HelloWorldApiService;


public class Profile extends AppCompatActivity {

    private static final String TAG = Profile.class.getSimpleName();

    public static final  String BASE_URL = "https://pvt.dsv.su.se/Group05/";

    private static Retrofit retrofit = null;

    TextView resultView;
    TextView nameView;
    TextView ageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        connectAndGetApiData();
    }

    /*
    public void setTextProto(String[] s) {
        System.out.println("hej");
        System.out.println(s[1]);
        final TextView resultView =(TextView)findViewById(R.id.resulttext);
        final TextView nameView = (TextView) findViewById(R.id.nametext);
        final TextView ageView = (TextView) findViewById(R.id.agetext);
        resultView.setText(s[0]);
        nameView.setText(s[1]);
        ageView.setText(s[2]);
    }*/

    public void connectAndGetApiData() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        HelloWorldApiService helloWorldApiService = retrofit.create(HelloWorldApiService.class);
        Call<HelloWorldData> call = helloWorldApiService.getJsonResponse();


        call.enqueue(new Callback<HelloWorldData>() {
            @Override
            public void onResponse(Call<HelloWorldData> call, Response<HelloWorldData> response) {
                try {
                    final String[] output = new String[3];
                    String resultResponse = response.body().getResult();
                    String nameResponse = response.body().getName();
                    String ageResponse = response.body().getAge();
                    System.out.println(resultResponse + " lol");
                    resultView = (TextView) findViewById(R.id.resulttext);
                    nameView = (TextView) findViewById(R.id.nametext);
                    ageView = (TextView) findViewById(R.id.agetext);
                    resultView.setText(resultResponse);
                    nameView.setText(nameResponse);
                    ageView.setText(ageResponse);


                    Log.d(TAG, "Received data (result, name, age): " + resultResponse + " " + nameResponse + " "
                            + ageResponse);
                } catch (NullPointerException e) {
                    System.out.println("API-data contained null.");
                }

            }

            @Override
            public void onFailure(Call<HelloWorldData> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

        System.out.println(call.toString());
    }

}
