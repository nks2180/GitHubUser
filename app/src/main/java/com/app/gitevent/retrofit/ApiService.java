package com.app.gitevent.retrofit;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by niranjan on 03/01/16.
 * Custom interface to declare all API calls
 */
public interface ApiService {

    @GET("api/v1/booking/slots/all")
    Call<String> fetchAppointmentSlots(@QueryMap Map<String, String> requestParams);

    @GET("users/{userName}/events")
    Call<String> fetchGitEvents(@Path("userName") String userName, @QueryMap Map<String, String> requestParams);

    @GET("users/{userName}")
    Call<String> validateUser(@Path("userName") String userName, @QueryMap Map<String, String> requestParams);


}
