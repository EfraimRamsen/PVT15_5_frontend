package se.su.dsv.pvt.helloworldapp.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import se.su.dsv.pvt.helloworldapp.model.Challenge;
import se.su.dsv.pvt.helloworldapp.model.OutdoorGym;
import se.su.dsv.pvt.helloworldapp.model.Place;

public interface BackendApiService {
//    @GET("outdoorgymtest?gymID=60") // allGyms/
//    Call<OutdoorGym> getJsonResponse();

    @GET("allGyms")
    Call<List<OutdoorGym>> getAllGymsResponse();

    @POST("createChallenge")
    Call<Challenge> createNewChallengeRequest(@Body Challenge params);

    @POST("addString")
    Call<String> testMethod(@Body String params);
}