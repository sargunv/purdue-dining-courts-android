package me.sargunvohra.android.diningcourts.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DiningCourtApi {
    @GET("locations")
    Call<LocationResponse> getLocations();
}

