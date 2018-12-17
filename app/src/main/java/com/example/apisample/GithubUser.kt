package com.example.apisample

import com.google.gson.annotations.SerializedName

data class GithubUser(val name: String,
                      val email: String,
                      val id: Int,
                      @SerializedName("node_id")val nodeId: String)