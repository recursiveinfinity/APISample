package com.example.apisample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.apisample.iss.ISSActivity
import com.example.apisample.iss.ISSResponse
import com.example.apisample.iss.ISSService
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()


        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL_ISS)
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit = retrofitBuilder
            .client(okHttpClient)
            .build()

        val gitHubClient = retrofit.create(GithubService::class.java)

        val issClient = retrofit.create(ISSService::class.java)

        /*btnSubmit.setOnClickListener {
            gitHubClient.getUserDetails(etInput.text.toString()).enqueue(object : Callback<GithubUser> {
                override fun onFailure(call: Call<GithubUser>, throwable: Throwable) {
                    throwable.printStackTrace()
                }

                override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                    if (response.isSuccessful) {
                        val githubUser = response.body()
                        tvStatus.text = githubUser?.name
                    }
                }
            })
        }*/

        btnSubmit.setOnClickListener {
           startActivity(Intent(this, ISSActivity::class.java))
        }





    }
}
