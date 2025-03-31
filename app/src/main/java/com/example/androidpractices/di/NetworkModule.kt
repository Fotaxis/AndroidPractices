package com.example.androidpractices.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.androidpractices.gameList.data.api.RawgIoApi
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    factory { provideRetrofit(get()) }
    single { provideNetworkApi(get()) }
}

fun provideRetrofit(context: Context): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.rawg.io/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().apply {
            addInterceptor {
                Interceptor { chain ->
                    val request: Request = chain.request()
                    val url: HttpUrl =
                        request.url.newBuilder().addQueryParameter(
                            "key",
                            "8ac4f96e15aa4bdaa03577cdfba3259f"
                        ).build()
                    chain.proceed(request.newBuilder().url(url).build())
                }.intercept(it)
            }
            addInterceptor(ChuckerInterceptor(context))
        }.build())
        .build()
}

fun provideNetworkApi(retrofit: Retrofit): RawgIoApi =
    retrofit.create(RawgIoApi::class.java)