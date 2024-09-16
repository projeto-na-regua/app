// ListagemBarbearias.kt
package com.example.na_regua_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.R
import com.example.na_regua_app.components.BarraPesquisar
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.ui.theme.Typography

@Composable
fun ListagemBarbearias(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.background(Color.White),
        topBar = {
            TopBarCustom(navController, "Buscar", true)
        },
        content = { paddingValues ->
            ListagemContent(paddingValues = paddingValues, navController = navController)
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )
}

@Composable
fun CardSelecionarBarbearia(
    nome: String,
    endereco: String,
    distancia: String,
    avaliacao: String,
    fotoResId: Int,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp))
            .clickable { navController.navigate("perfilBarbearia") } // Navega para a tela de perfil
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(Color.Transparent)
                        .border(2.dp, Color.Gray, CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = fotoResId),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = nome,
                        style = Typography.labelLarge
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = endereco,
                        style = Typography.labelSmall
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.LocationOn, contentDescription = "")
                        Text(
                            text = distancia,
                            style = Typography.labelSmall
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Icon(imageVector = Icons.Default.Star, tint = Color.Yellow, contentDescription = "")

                        Text(
                            text = avaliacao,
                            style = Typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ListagemContent(paddingValues: PaddingValues, navController: NavController) {
    LazyColumn(
        contentPadding = paddingValues,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        item {
            BarraPesquisar({ pesquisa -> navController.navigate("cadastro") }, navController)
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            CardSelecionarBarbearia(
                nome = "barbearia_top_de_linha",
                endereco = "Rua Piracicaba, 214 - SP",
                distancia = "200m",
                avaliacao = "4,5",
                fotoResId = R.drawable.perfil_barbearia, // Substitua pelo ID do seu recurso
                navController = navController
            )
        }
        item {
            CardSelecionarBarbearia(
                nome = "Barbearia do João",
                endereco = "Avenida das Flores - 123",
                distancia = "500m",
                avaliacao = "4,0",
                fotoResId = R.drawable.capa_perfil_barbeiro, // Substitua pelo ID do seu recurso
                navController = navController
            )
        }
    }
}

@Preview
@Composable
fun ListagemBarbeariasPreview() {
    ListagemBarbearias(rememberNavController())
}
