package com.example.na_regua_app.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.ui.components.BotaoSpan
import com.example.na_regua_app.ui.components.Input
import com.example.na_regua_app.ui.components.LogoImage
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY

@Composable
fun CadastroInicio(
    navController: NavController
) {
    var botaoClicado by remember { mutableStateOf(false) }

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
                    text = "Vamos começar!",
                    fontSize = 24.sp,
                    color = BLUE_PRIMARY,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 40.dp),
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Explore o que nós podemos oferecer!",
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
                    Image(
                        painter = painterResource(id = com.example.na_regua_app.R.drawable.icone_cadastro),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(top = 80.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                BotaoSpan(
                    onClick = { navController.navigate("cadastroFotoUsername") },
                    textButton = "Iniciar",
                    textEsquerda = "Já possui conta?",
                    textDireita = "Entre",
                    onTextoDireitaClick = {
                        navController.navigate("login")
                    }
                )
            }
        }
    )
}

@Preview
@Composable
fun CadastroInicioPreview() {
    CadastroInicio(navController = rememberNavController())
}