package com.example.apisample.iss

data class ISSResponse(
    val message: String, val request: Request,
    val response: List<Response>
)