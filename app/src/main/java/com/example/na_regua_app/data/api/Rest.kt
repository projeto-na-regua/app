package com.example.na_regua_app.data.api

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.na_regua_app.BuildConfig
import com.example.na_regua_app.utils.obterToken
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.first
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

@RequiresApi(Build.VERSION_CODES.O)
object Rest {
    class ApiInterceptor(
        private val context: Context
    ) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val token = runBlocking {
                obterToken(context).first()
            }
            val oldRequest = chain.request()
            val newRequest = Request
                .Builder()
                .url(oldRequest.url)
                .method(oldRequest.method, oldRequest.body)
                .header(
                    "Authorization",
                    token
                )
                .build()
            return chain.proceed(newRequest)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, JsonDeserializer { json, _, _ ->
            LocalDateTime.parse(json.asString, DateTimeFormatter.ISO_DATE_TIME)
        })
        .create()

    fun getInstance(context: Context): Retrofit {
        return Retrofit
            .Builder()
            .client(getClient(context))
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun getClient(context: Context): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(ApiInterceptor(context))
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor())
            .build()
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}
