package com.example.samsungproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    /**
     * @return
     */
    @GET("mrmaga1/DataBace/master/dbWork.json")
    Call<List<InfoWork>> getWorkPC();
    @GET("mrmaga1/DataBace/master/dbGame.json")
    Call<List<InfoGame>> getGamePC();
}
