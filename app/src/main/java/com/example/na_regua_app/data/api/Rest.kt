package com.example.na_regua_app.data.api

import com.example.na_regua_app.BuildConfig
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {

    class apiInterceptor : Interceptor {
        override fun intercept(chain: Chain): Response {
            val oldRequest = chain.request()
            val newRequest = Request.Builder()
                .url(oldRequest.url)
                .method(oldRequest.method, oldRequest.body)
                .header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjE5Iiwibm9tZSI6IlJpYW4gTmVyaXMiLCJzZW5oYSI6InNlbmhhMTIzIiwiZW1haWwiOiJyaWFuQGdtYWlsLmNvbSIsImV4cCI6MTcyOTAxNDgwMzEyMn0.Pfoujf_XKd_EQsi8mMBGnFfe-KK8E15p95OF_EO7o7E")
                .build()
            return chain.proceed(newRequest)
        }
    }

    // Adiciona o HttpLoggingInterceptor
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Log do body da requisição e da resposta
    }

    val client by lazy {
        OkHttpClient
            .Builder()
            .addInterceptor(apiInterceptor())
            .build()
    }

    val api = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val usuarioService by lazy {
        api.create(UsuarioService::class.java)
    }

//    val agendamentoService by lazy {
//        api.create(AgendamentoService::class.java)
//    }

}