package com.example.apisample.iss

data class Request(val altitude: Int, val datetime: Long, val latitude: Float,
                   val longitude: Float, val passes: Int)