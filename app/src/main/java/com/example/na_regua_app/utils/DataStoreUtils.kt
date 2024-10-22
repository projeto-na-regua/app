package com.example.na_regua_app.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore(name = "api_preferences")
val tokenPreferencesKey = stringPreferencesKey(name = "api_token")

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