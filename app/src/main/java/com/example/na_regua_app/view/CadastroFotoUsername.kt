package com.example.na_regua_app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.components.Botao
import com.example.na_regua_app.components.BotaoSpan
import com.example.na_regua_app.components.Input
import com.example.na_regua_app.components.InputCadastro
import com.example.na_regua_app.components.LogoImage
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY

@Composable
fun CadastroFotoUsername(
    navController: NavController
) {
    var botaoClicado by remember { mutableStateOf(false) }
    var apelido by remember { mutableStateOf("") }

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(vertical = 50.dp, horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Informações pessoais",
                    fontSize = 24.sp,
                    color = BLUE_PRIMARY,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 40.dp),
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Para iniciarmos seu cadastro informe seu dados!",
                    fontSize = 12.sp,
                    color = BLUE_PRIMARY,
                    modifier = Modifier.padding(top = 12.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 1.sp,
                )

                Column(
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 80.dp)
                            .size(150.dp)
                            .clip(CircleShape)
                            .background(color = Color.Black)
                    ) {
                        Image(
                            painter = painterResource(id = com.example.na_regua_app.R.drawable.png_background),
                            contentDescription = "",
                            contentScale = ContentScale.Crop, // Garante que a imagem preencha o círculo
                            modifier = Modifier.clip(CircleShape) // Aplica a forma circular também na imagem
                        )
                    }
                    Button(onClick = { /*TODO*/},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BLUE_PRIMARY
                        ),
                        modifier = Modifier
                            .fillMaxWidth(.55f)
                            .padding(top = 15.dp),
                                shape = RoundedCornerShape(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = com.example.na_regua_app.R.drawable.icone_adicionar_foto),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "Adicionar foto", color = ORANGE_SECUNDARY,
                            fontSize = 14.sp,
                            letterSpacing = 1.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column (horizontalAlignment = Alignment.Start,
                        modifier = Modifier.fillMaxWidth(1f)) {
                        Text(
                            text = "Este vai ser seu apelido",
                            modifier = Modifier.padding(top = 20.dp),
                            color = Color(0xFF9E9E9E),
                            fontStyle = FontStyle.Italic,
                            letterSpacing = 1.sp,
                            fontSize = 15.sp
                        )
                    }
                    InputCadastro(
                        value = apelido,
                        onValueChange = { novoValor -> apelido = novoValor },
                        label = { Text("Apelido") }
                    )

                }
                Spacer(modifier = Modifier.weight(1f))
                Botao(
                    onClick = { /*TODO*/ },
                    textButton = "Próximo"
                )
            }
        }
    )
}

@Preview
@Composable
fun CadastroFotoUsernamePreview() {
    CadastroFotoUsername(navController = rememberNavController())
}