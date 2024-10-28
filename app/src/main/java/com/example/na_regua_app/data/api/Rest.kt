package com.example.na_regua_app.data.api

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.na_regua_app.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

@RequiresApi(Build.VERSION_CODES.O)
object Rest {

    class apiInterceptor() : Interceptor {
        override fun intercept(chain: Chain): Response {

//          val token = obterTokenSincrono(context)

            val oldRequest = chain.request()
            val newRequest = Request.Builder()
                .url(oldRequest.url)
                .method(oldRequest.method, oldRequest.body)
                .header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjIxIiwibm9tZSI6Ikpvw6NvIFNpbHZhIiwic2VuaGEiOiJzZW5oYVNlZ3VyYTEyMyIsImVtYWlsIjoiam9hb3NpbHZhMUBleGFtcGxlLmNvbSIsImV4cCI6MTczMDA4MTUwMDEyN30.X-uM9w-QENBKhQtqLpQ7GRJ3F1YpA00A-B4WViChp2U")
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

    val client by lazy {
        OkHttpClient
            .Builder()
            .addInterceptor(apiInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS) // Aumente para 30 segundos ou mais
            .readTimeout(30, TimeUnit.SECONDS)    // Aumente para 30 segundos ou mais
            .writeTimeout(30, TimeUnit.SECONDS)   // Aumente para 30 segundos ou mais
            .build()
    }


    val api by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create()) // Para strings
            .addConverterFactory(GsonConverterFactory.create(gson)) // Para objetos JSON
            .build()
    }

    val usuarioService by lazy { api.create(UsuarioService::class.java) }
    val barbeariaService by lazy { api.create(BarbeariaService::class.java) }
    val servicoService by lazy { api.create(ServicoService::class.java) }
    val funcionarioService by lazy { api.create(FuncionarioService::class.java) }
    val pesquisaService by lazy { api.create(PesquisaService::class.java) }
    val agendamentoService by lazy { api.create(AgendamentoService::class.java) }
    val financaService by lazy { api.create(FinancasService::class.java) }
}