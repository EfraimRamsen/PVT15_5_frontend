package se.su.dsv.pvt.helloworldapp.se.su.dsv.pvt.helloworldapp.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import se.su.dsv.pvt.helloworldapp.model.HelloWorldData;

public interface HelloWorldApiService {
    @GET("sayJSON/")
    Call<HelloWorldData> getJsonResponse();
}
