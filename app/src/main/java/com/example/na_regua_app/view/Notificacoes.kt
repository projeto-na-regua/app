package com.example.na_regua_app.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Notificacoes(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Notificações") })
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text(text = "Welcome to the Home Screen")



                Spacer(modifier = Modifier.height(16.dp))

                // Add navigation or other UI elements here
                Button(onClick = { /* Handle button click */ }) {
                    Text("Click Me")
                }
            }
        }
    )
}