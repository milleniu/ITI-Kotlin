package dev.hugozammit.retrofit.common

import dev.hugozammit.retrofit.`interface`.RetrofitService
import dev.hugozammit.retrofit.retrofit.RetrofitClient

object Common {
    private var BASE_URL = "http://simplifiedcoding.net/demos/"
    val retrofitService: RetrofitService
    get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}