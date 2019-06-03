package se.su.dsv.pvt.helloworldapp.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import se.su.dsv.pvt.helloworldapp.model.Challenge;
import se.su.dsv.pvt.helloworldapp.model.OutdoorGym;
import se.su.dsv.pvt.helloworldapp.model.Participation;
import se.su.dsv.pvt.helloworldapp.model.Place;

// Detta interface deklarerar vilka API-anrop som kan göras och typ av HTTP-request.
// Här definieras adress vilken adress anropet görs till och namnet på tillhörande metod som ska köras. /JD
public interface BackendApiService {
    @GET("allGyms")
    Call<List<OutdoorGym>> getAllGyms();

    @POST("rateGym/gym/{gymID}/user/{userID}/rate/{rate}")
    Call<ResponseBody> rateGym(@Path("gymID") int gymID, @Path("userID") int userID, @Path("rate") int rate);

    @POST("user/{userID}/createChallenge")
    Call<String> createNewChallengeRequest(@Path("userID") int userID, @Body Challenge params);

    @PUT("removeChallenge/{challengeID}")
    Call<String> removeChallenge(@Path("challengeID") int challengeID);

    @GET("getChallenges/{userID}")
    Call<ArrayList<Challenge>> getUserChallenges(@Path("userID") int userID);

    @PUT("completeChallenge/{participationID}")
    Call<String> completeChallenge(@Path("participationID") int participationID);

    @POST("createParticipation/user/{userID}/challenge/{challengeID}")
    Call<String> createParticipation(@Path("userID") int userID, @Path("challengeID")  int challengeID);

    @GET("getParticipation/{userID}")
    Call<ArrayList<Participation>> getParticipation(@Path("userID") int userID);

    @PUT("removeParticipation/{participationID}")
    Call<String> removeParticipation(@Path("participationID") int participationID);

    @GET("login/{userName}/{password}")
    Call<ResponseBody> login(@Path(value = "userName", encoded = true) String userName, @Path(value = "password", encoded = true) String password);

    @POST("createUser/{userName}/{password}")
    Call<ResponseBody> register(@Path(value = "userName", encoded = true) String userName, @Path(value = "password", encoded = true) String password);
}