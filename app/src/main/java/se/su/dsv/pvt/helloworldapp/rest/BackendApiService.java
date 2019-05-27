package se.su.dsv.pvt.helloworldapp.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import se.su.dsv.pvt.helloworldapp.model.Challenge;
import se.su.dsv.pvt.helloworldapp.model.OutdoorGym;
import se.su.dsv.pvt.helloworldapp.model.Place;

// Detta interface deklarerar vilka API-anrop som kan göras och typ av HTTP-request.
// Här definieras adress vilken adress anropet görs till och namnet på tillhörande metod som ska köras. /JD
public interface BackendApiService {
//    @GET("outdoorgymtest?gymID=60")
//    Call<OutdoorGym> getJsonResponse();

    @GET("allGyms")
    Call<List<OutdoorGym>> getAllGyms();

    @POST("createChallenge")
    Call<Challenge> createNewChallengeRequest(@Body Challenge params);

    @POST("createParticipation/user/{userID}/challenge/{challengeID}")
    Call<String> createParticipation(@Path("userID") int userID, @Path("challengeID")  int challengeID);

    @PUT("removeChallenge/{challengeID}")
    Call<String> removeChallenge(@Path("challengeID") int challengeID);
}