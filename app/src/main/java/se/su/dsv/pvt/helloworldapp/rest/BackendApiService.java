package se.su.dsv.pvt.helloworldapp.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import se.su.dsv.pvt.helloworldapp.model.Challenge;
import se.su.dsv.pvt.helloworldapp.model.OutdoorGym;
import se.su.dsv.pvt.helloworldapp.model.Place;

// Detta interface deklarerar vilka API-anrop som kan göras och typ av HTTP-request.
// Här definieras adress vilken adress anropet görs till och namnet på tillhörande metod som ska köras. /JD
public interface BackendApiService {
//    @GET("outdoorgymtest?gymID=60") // allGyms/
//    Call<OutdoorGym> getJsonResponse();

    @GET("allGyms")
    Call<List<OutdoorGym>> getAllGymsResponse();

    @POST("createChallenge")
    Call<Challenge> createNewChallengeRequest(@Body Challenge params);
}