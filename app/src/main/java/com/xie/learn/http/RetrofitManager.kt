package com.xie.learn.http

import com.xie.learn.tools.Config
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Author:     Xie
 *
 * @Date:       2025/11/5 17:20
 *
 * @Desc:
 */
class RetrofitManager {
    val contentType = "application/json".toMediaType()

    val json = Json {
        ignoreUnknownKeys = true // 忽略 JSON 中额外字段
        isLenient = true
    }

    val apiService: ApiService = Retrofit.Builder()
        .baseUrl("")
        .addConverterFactory(json.asConverterFactory(contentType))
//        .client(genericOkClient())
        .build()
        .create(ApiService::class.java)

    private fun genericOkClient(): OkHttpClient {
        //  log 拦截器
        val httpLoggingInterceptor = HttpLoggingInterceptor { message -> "Timber.e(message)" }

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(Config.TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(Config.TIME_OUT, TimeUnit.MILLISECONDS)
            .writeTimeout(Config.TIME_OUT, TimeUnit.MILLISECONDS)
//            .addInterceptor(AuthTokenInterceptor.getInstance())
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}