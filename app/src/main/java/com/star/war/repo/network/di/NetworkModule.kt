package com.star.war.repo.network.di

import com.star.war.BuildConfig
import com.star.war.repo.network.*
import com.star.war.repo.network.RxErrorHandlingCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkRepo(
        starWarsApi: StarWarsApi
    ): NetworkRepo = NetworkRepoImpl(starWarsApi)

    @Provides
    @Singleton
    fun providesRetrofitApi(
        httpClient: OkHttpClient
    ): StarWarsApi {
        return providesRetrofit(httpClient, BASE_URL).create(StarWarsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttp() = OkHttpClient.Builder().run {
        connectTimeout(15, TimeUnit.SECONDS)
        readTimeout(15, TimeUnit.SECONDS)
        callTimeout(15, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }
        return@run build()
    }

    private fun providesRetrofit(
        httpClient: OkHttpClient,
        baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}