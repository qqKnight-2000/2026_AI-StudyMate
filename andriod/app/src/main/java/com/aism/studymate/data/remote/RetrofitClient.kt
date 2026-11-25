package com.aism.studymate.data.remote

import com.aism.studymate.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitClient {
    private val json = Json { ignoreUnknownKeys = true } // 忽略后端多出来的字段，防止崩溃

    val api: AismApi by lazy {
        Retrofit.Builder()
            // 这里读取你在 build.gradle 配置的 Apifox Mock 地址
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(AismApi::class.java)
    }
}

