package com.example.na_regua_app.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.R
import com.example.na_regua_app.components.Botao
import com.example.na_regua_app.components.Input
import com.example.na_regua_app.components.LogoImage

@Composable
fun TelaInicial(navController: NavController) {
    var email = "";
    var senha by remember { mutableStateOf("") }
    var botaoEntrarClicado by remember { mutableStateOf(false) }
    var botaoEntrarOutraContaClicado by remember { mutableStateOf(false) }

    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.foto_fundo_tela_inicial),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 50.dp, horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    LogoImage()

                    Column (
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .background(Color.Transparent)
                                .clip(CircleShape)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.foto_perfil),
                                contentDescription = "Foto de perfil",
                                modifier = Modifier.fillMaxWidth(1f),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Text(
                            text = "Olá, Diego!",
                            fontSize = 30.sp,
                            color = Color.White,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Spacer(modifier = Modifier.padding(top = 30.dp))
                        if(botaoEntrarClicado){
                            Input(
                                value = senha,
                                onValueChange = {novoValor -> senha = novoValor},
                                label = { Text("Senha") },
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White,
                                focusedLabelColor = Color.White,
                                unfocusedLabelColor = Color.White
                            )
                            Spacer(modifier = Modifier.padding(bottom = 30.dp))
                        }
                        Botao(onClick = {
                            botaoEntrarClicado = true
                        },
                            textButton = "Entrar"
                        )
                        Text(
                            text = "Entrar com outra conta",
                            modifier = Modifier
                                .clickable {
                                    navController.navigate("login")
                                }
                                .padding(top = 15.dp),
                            color = Color.White
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun TelaInicialPreview() {
    TelaInicial(navController = rememberNavController())
}

