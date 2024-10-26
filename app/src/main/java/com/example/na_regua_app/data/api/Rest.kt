package com.example.na_regua_app.data.api

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.na_regua_app.BuildConfig
import com.example.na_regua_app.utils.obterToken
import com.example.na_regua_app.utils.obterTokenSincrono
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object Rest {

    class apiInterceptor : Interceptor {
        override fun intercept(chain: Chain): Response {

//            val token = obterTokenSincrono(context)

            val oldRequest = chain.request()
            val newRequest = Request.Builder()
                .url(oldRequest.url)
                .method(oldRequest.method, oldRequest.body)
                .header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjIwIiwibm9tZSI6Ikpvw6NvIGRhIFNpbHZhIiwic2VuaGEiOiJzZW5oYTEyMzQiLCJlbWFpbCI6ImpvYW8uc2lsdmFAZXhhbXBsZS5jb20iLCJleHAiOjE3Mjk5MTIzNjE4MzJ9.Zjp-ymGZeY7mBJyMC-yb51d0OknuYVvAj2suEryqgDw")
                .build()
            return chain.proceed(newRequest)
        }
    }

    val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    val client by lazy {
        OkHttpClient
            .Builder()
            .addInterceptor(logging)
            .addInterceptor(apiInterceptor())
            .connectTimeout(60, TimeUnit.SECONDS) // Aumente para 30 segundos ou mais
            .readTimeout(60, TimeUnit.SECONDS)    // Aumente para 30 segundos ou mais
            .writeTimeout(60, TimeUnit.SECONDS)   // Aumente para 30 segundos ou mais
            .build()
    }

    val api = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(client)
        .addConverterFactory(ScalarsConverterFactory.create()) // Para strings
        .addConverterFactory(GsonConverterFactory.create()) // Para objetos JSON
        .build()



    val usuarioService by lazy { api.create(UsuarioService::class.java) }
    val barbeariaService by lazy { api.create(BarbeariaService::class.java) }
    val servicoService by lazy { api.create(ServicoService::class.java) }
    val funcionarioService by lazy { api.create(FuncionarioService::class.java) }
    val chatService by lazy { api.create(ChatService::class.java) }
    val pesquisaService by lazy { api.create(PesquisaService::class.java) }
}