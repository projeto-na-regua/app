@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.na_regua_app.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.R
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.MessageBubble
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY

@Composable
fun Chat(
    navController: NavController,
    userName: String,
    profilePic: Int,
    origin: String
) {
    var messages by remember { mutableStateOf(listOf<String>()) }

    Scaffold(
        topBar = {
            TopBarCustom(navController, "Voltar ao perfil", false, false, true)
        },
        content = { paddingValues ->
            ChatContent(
                paddingValues = paddingValues,
                navController = navController,
                userName = userName,
                profilePic = profilePic,
                messages = messages,
                origin = origin
            )
        },
        bottomBar = {
            BottomBarChat { message ->
                messages = messages + message
            }
        }
    )
}

@Composable
fun ChatContent(paddingValues: PaddingValues, navController: NavController,userName: String, profilePic: Int, messages: List<String>, origin: String) {
    var descricao by remember { mutableStateOf("Envie uma mensagem para tirar suas dúvidas!") }


    Box(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
        contentAlignment = Alignment.Center
    ){
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = userName,
                style = typography.titleLarge,
                fontWeight = Bold,
                color = BLUE_PRIMARY,
                modifier = Modifier.padding(top = 25.dp)
            )

            Spacer(modifier = Modifier.size(15.dp))

            Text(
                text = descricao,
                style = typography.bodyLarge,
                color = BLUE_PRIMARY,
                textAlign = TextAlign.Center
            )
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.Bottom,
    ) {

        if (origin == "perfilUsuario") {
            item {
                MessageBubble("Olá, tudo bem?", profilePic, isCurrentUser = true)
                MessageBubble("Estou bem sim!", R.drawable.foto_perfil, isCurrentUser = false)
            }
        } else if (origin == "perfilBarbearia") {
            if (messages.size == 1) {
                item {
                    MessageBubble("Olá, tudo bem! Bem-vindo a $userName", profilePic, isCurrentUser = false)
                    MessageBubble("Deseja marcar um horário? Visite nossa barbearia para saber mais sobre nossos serviços, funcionando de segunda a sexta, das 9:00 às 18:00 hrs", profilePic, isCurrentUser = false)
                }
            }
        }

        items(messages.size) { index ->
            val message = messages[index]
            MessageBubble(message, profilePic, isCurrentUser = true)
        }
    }
}


@Composable
fun BottomBarChat(
    onMessageSent: (String) -> Unit
) {
    var postMessage by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .border(1.dp, Color(0xFFCBD5E0), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(painter = painterResource(id = R.drawable.attachment_icon),
                    contentDescription = "Anexar",
                    tint = Color(0xFFCBD5E0)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = postMessage,
                onValueChange = { postMessage = it },
                modifier = Modifier
                    .width(230.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BLUE_PRIMARY,
                    unfocusedBorderColor = Color(0xFFCBD5E0),
                    focusedLabelColor = BLUE_PRIMARY,
                    unfocusedLabelColor = Color(0xFFCBD5E0)
                )
            )
        }

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(color = BLUE_PRIMARY, shape = RoundedCornerShape(12.dp))
                .clickable {
                    if (postMessage.isNotBlank()) {
                        onMessageSent(postMessage)
                        postMessage = ""
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Enviar",
                tint = ORANGE_SECUNDARY
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatPreview(){
    val navController = rememberNavController()
    Chat(navController = navController, userName = "Diego", profilePic = R.drawable.barbeiro1, "perfilUsuario")
}