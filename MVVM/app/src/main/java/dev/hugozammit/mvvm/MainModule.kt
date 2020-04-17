package dev.hugozammit.mvvm

import dev.hugozammit.mvvm.repository.DataRepository
import dev.hugozammit.mvvm.viewmodel.ProductViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module {

    single { DataRepository( get() ) }
    single { createWebService() }
    viewModel { ProductViewModel( get() ) }
}

fun createWebService(): NetworkAPI {

    val retrofit = Retrofit.Builder()
        .addConverterFactory( GsonConverterFactory.create() )
        .addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
        .baseUrl( "http://mobcategories.s3-website-eu-west-1.amazonaws.com" )
        .build()

    return retrofit.create( NetworkAPI::class.java )
}