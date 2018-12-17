package com.example.apisample

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("{user}")
    fun getUserDetails(@Path("user") userName: String): Call<GithubUser>
}