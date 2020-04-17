package dev.hugozammit.mvvm

import dev.hugozammit.mvvm.model.ProductFamily
import retrofit2.Call
import retrofit2.http.GET

interface NetworkAPI {

    @GET( "/" )
    fun getProducts(): Call<List<ProductFamily>>

}