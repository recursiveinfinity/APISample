package com.example.apisample.iss

import com.example.apisample.ISS_ENDPOINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ISSService {

    @GET(ISS_ENDPOINT)
    fun getISSPasses(@Query("lat") latitude: Float,
                     @Query("lon") longitude: Float): Call<ISSResponse>
}