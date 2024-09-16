package com.example.na_regua_app.view

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
import com.example.na_regua_app.components.Botao
import com.example.na_regua_app.components.BotaoSpan
import com.example.na_regua_app.components.Input
import com.example.na_regua_app.components.LogoImage
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY

@Composable
fun CadastroFim(
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
                    text = "Tudo pronto!",
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

                Column (
                    modifier = Modifier
                        .padding(top = 50.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Column(
                        modifier = Modifier
                            .padding(top = 18.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Image(
                            painter = painterResource(id = com.example.na_regua_app.R.drawable.notificacao_fim_cadastro),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxWidth(0.25f),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Column (modifier = Modifier
                        .padding(top = 0.dp)
                        .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally){
                        Image(
                            painter = painterResource(id = com.example.na_regua_app.R.drawable.boneco_fim_cadastro),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxWidth(.9f),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
                Botao(
                    onClick = { navController.navigate("homeUsuario") },
                    textButton = "Ir para Página Inicial"
                )
            }
        }
    )
}

@Preview
@Composable
fun CadastroFimPreview() {
    CadastroFim(navController = rememberNavController())
}