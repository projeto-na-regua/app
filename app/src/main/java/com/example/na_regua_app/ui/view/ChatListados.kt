package com.example.na_regua_app.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.ui.components.ChatCard
import com.example.na_regua_app.ui.components.TopBarCustom

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatListados(navController: NavController) {
    Scaffold(
        topBar = {
            TopBarCustom(navController, "Voltar ao perfil", true, true, true)
        },
        content = {
            ChatsListContent()
        },
        bottomBar = {
        }
    )
}

@Composable
fun ChatsListContent(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.fillMaxSize()
            ){
            ChatCard(modifier = Modifier)
            ChatCard(modifier = Modifier)
            ChatCard(modifier = Modifier)
            ChatCard(modifier = Modifier)
            ChatCard(modifier = Modifier)
            ChatCard(modifier = Modifier)
            ChatCard(modifier = Modifier)
            ChatCard(modifier = Modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatListadosPreview(){
    val navController = rememberNavController()
    ChatListados(navController = navController)
}