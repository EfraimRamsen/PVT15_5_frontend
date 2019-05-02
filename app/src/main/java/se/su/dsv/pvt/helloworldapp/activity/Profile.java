package se.su.dsv.pvt.helloworldapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.model.HelloWorldData;
import se.su.dsv.pvt.helloworldapp.se.su.dsv.pvt.helloworldapp.rest.HelloWorldApiService;


public class Profile extends AppCompatActivity {

    /**
     * de tre första variablerna hör till magin.
     * De tre efterföljande är de tre TextViews - dvs. textfält - som används i den här vyn.
     */
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
        Toolbar toolbar = (Toolbar) TopActionBar.getToolbar("Profile", findViewById(R.id.my_toolbar));
        toolbar.setNavigationIcon(R.drawable.icons8);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /**
         * Denna klass borde abstraheras iväg, dvs. man skickar iväg MenuItem item i guess???
         */
        switch(item.getItemId()) {
            case R.id.back_button:
                System.out.println("loool");
                return super.onOptionsItemSelected(item);

                /*Intent intent = new Intent(this, getParent().getClass());
                startActivity(intent);
                return true;*/
            default:
                System.out.println("defullllllet");

                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toptoolbar, menu);
        return true;
    }

    // Allting här under är magi, och inget jag kan förklara för varken mig själv eller någon annan.
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
                    String resultResponse = response.body().getResult();
                    String nameResponse = response.body().getName();
                    String ageResponse = response.body().getAge();
                    /**
                     * de, uh, sex nedanstående kodraderna deklarerar först de olika TextViews:arna;
                     * detta sker genom att anropa findViewById, slänga in R.id. och sen namnet
                     * på komponenten som ska ändras. Detta namn finns i respektive layouts xml-fil,
                     * vilket i det aktuella fallet är activity_profile.xml.
                     */
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
    }

}
