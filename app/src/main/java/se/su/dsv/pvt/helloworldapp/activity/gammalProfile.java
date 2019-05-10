package se.su.dsv.pvt.helloworldapp.activity;

import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.model.HelloWorldData;
import se.su.dsv.pvt.helloworldapp.rest.HelloWorldApiService;


public class gammalProfile extends AppCompatActivity {

    /**
     * de tre första variablerna hör till magin.
     * De tre efterföljande är de tre TextViews - dvs. textfält - som används i den här vyn.
     */
    private static final String TAG = gammalProfile.class.getSimpleName();

    public static final  String BASE_URL = "https://pvt.dsv.su.se/Group05/";

    private static Retrofit retrofit = null;

    // Used for the top title bar:
    public static final int titleIcon = 0;
    public static final int iconId = R.id.profile_icon;

    TextView resultView;
    TextView nameView;
    TextView ageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gammal_activity_profile);
        connectAndGetApiData();
        // Create, configure, and set the toolbar:
        Toolbar toolbar = (Toolbar) TopActionBar
                .getToolbar(findViewById(TopActionBar.getToolbarIntLink())
                        , false);
        setSupportActionBar(toolbar);
        /*
        int windowWidth = TopActionBar.getWindowWidth(getWindowManager().getDefaultDisplay());
        toolbar.setTitleMarginStart(windowWidth / 4);
        toolbar.setTitleMarginEnd(windowWidth / 4);
        TextView tv = (TextView) toolbar.findViewById(R.id.profile_title_text);
        tv.setText(getString(R.string.profile_title));
        getSupportActionBar().setDisplayShowTitleEnabled(false);*/
        ImageView iV = (ImageView) toolbar.findViewById(iconId);
        iV.setImageDrawable(ResourcesCompat.getDrawable(getResources(), titleIcon, null));
    }

    // Allting här under är magi, och inget jag kan förklara för varken mig själv eller någon annan.
    public void connectAndGetApiData() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
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
                     * vilket i det aktuella fallet är gammal_activity_profileprofile.xml.
                     */
                    resultView = (TextView) findViewById(R.id.resulttext);
                    nameView = (TextView) findViewById(R.id.nametext);
                    ageView = (TextView) findViewById(R.id.agetext);
                    resultView.setText(resultResponse);
                    nameView.setText(nameResponse);
                    ageView.setText(ageResponse);


                    Log.d(TAG, "Received data - Result: " + resultResponse + " Name: " + nameResponse + " Age: "  + ageResponse);
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