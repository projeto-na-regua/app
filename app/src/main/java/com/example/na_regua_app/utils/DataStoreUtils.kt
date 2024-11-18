package com.example.na_regua_app.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.na_regua_app.data.model.UserDType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.first
import com.google.gson.Gson

val Context.dataStore by preferencesDataStore(name = "api_preferences")
val tokenPreferencesKey = stringPreferencesKey(name = "api_token")
val userDtypePreferencesKey = stringPreferencesKey(name = "api_userDType")

suspend fun salvarToken(context: Context, token: String) {
    context.dataStore.edit { preferences ->
        preferences[tokenPreferencesKey] = token
    }
}

fun obterToken(context: Context): Flow<String> {
    return context.dataStore.data.map { preferences ->
        preferences[tokenPreferencesKey] ?: "VAZIO"
    }
}

fun obterTokenSincrono(context: Context): String {
    return runBlocking {
        context.dataStore.data.map { preferences ->
            preferences[tokenPreferencesKey] ?: "VAZIO"
        }.first()
    }
}

suspend fun salvarUsuarioDtype(context: Context, userDtype: UserDType) {
    val gson = Gson()
    val userDtypeJson = gson.toJson(userDtype)  // Serializa o objeto para JSON
    context.dataStore.edit { preferences ->
        preferences[userDtypePreferencesKey] = userDtypeJson
    }
}

fun obterUsuarioDtype(context: Context): Flow<UserDType?> {
    val gson = Gson()
    return context.dataStore.data.map { preferences ->
        val userDtypeJson = preferences[userDtypePreferencesKey] ?: return@map null
        gson.fromJson(userDtypeJson, UserDType::class.java)
    }
}

fun obterUsuarioDtypeSincrono(context: Context): Flow<UserDType?>{
    val gson = Gson()
    return runBlocking {
        context.dataStore.data.map { preferences ->
            val userDtypeJson = preferences[userDtypePreferencesKey] ?: return@map null
            gson.fromJson(userDtypeJson, UserDType::class.java)
        }
    }
}


suspend fun limparDadosLogin(context: Context) {
    context.dataStore.edit { preferences ->
        preferences.remove(tokenPreferencesKey)
        preferences.remove(userDtypePreferencesKey)
    }
}

fun isUsuarioLogado(context: Context): Boolean {
    val token = obterTokenSincrono(context)
    println(token)
    return token != "VAZIO"
}

