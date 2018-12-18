package com.example.apisample.iss

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.apisample.BASE_URL_ISS
import com.example.apisample.R
import kotlinx.android.synthetic.main.activity_iss.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ISSActivity : AppCompatActivity() {

    private val adapter = ISSAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iss)

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

        val issClient = retrofit.create(ISSService::class.java)

        rvPasses.layoutManager = LinearLayoutManager(this)
        rvPasses.adapter = adapter

        btnSubmit.setOnClickListener {
            issClient.getISSPasses(etLatitude.text.toString().toFloat(),
                etLongitude.text.toString().toFloat()).enqueue(
                object : Callback<ISSResponse> {
                    override fun onFailure(call: Call<ISSResponse>, t: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(call: Call<ISSResponse>, response: Response<ISSResponse>) {
                        if (response.isSuccessful) {
                            adapter.setData(response.body()?.response ?: emptyList())
                        }
                    }

                }
            )
        }
    }
}