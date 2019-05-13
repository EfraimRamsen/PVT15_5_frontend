package se.su.dsv.pvt.helloworldapp.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import se.su.dsv.pvt.helloworldapp.model.OutdoorGym;

public interface BackendApiService {
    @GET("outdoorgymtest?gymID=60") // allGyms/
    Call<OutdoorGym> getJsonResponse();
}
