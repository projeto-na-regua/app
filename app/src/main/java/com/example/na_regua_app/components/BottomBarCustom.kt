package com.example.na_regua_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.na_regua_app.R
import com.example.na_regua_app.classes.Usuario
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.WHITE_BACKGROUND


@Composable
fun BottomBarCustom(navController: NavController, usuario: Usuario){


    BottomAppBar(
        containerColor = Color.White,
        contentColor = ORANGE_SECUNDARY,
        modifier = Modifier.fillMaxWidth()
            .border(
            width = 1.dp,
            color = Color.Transparent, // Defina a cor da borda, se necessário
            shape = RoundedCornerShape(0.dp) // Ajuste a forma da borda, se necessário
        )
            .drawBehind {
                val strokeWidth = 2.dp.toPx()

                // Desenhar a borda superior
                drawLine(
                    color = WHITE_BACKGROUND,
                    start = Offset(0f, -strokeWidth / 2),
                    end = Offset(size.width, -strokeWidth / 2),
                    strokeWidth = strokeWidth
                )
            }
            .padding(top = 2.dp),
        actions = {

            if(usuario.dtype == "Barbeiro"){

                Spacer(modifier = Modifier.weight(1f))

                IconSpan(id = R.drawable.dash_icon,
                    descripition = "Dashboard",
                    navController = navController,
                    route = "dashboard")



                Spacer(modifier = Modifier.weight(1f))

                IconSpan(id = R.drawable.servico_funcionario,
                    descripition = "Servico Funcionario",
                    navController = navController,
                    route = "gestao")

                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(54.dp)
                        .clip(RoundedCornerShape(28))
                        .background(BLUE_PRIMARY)
                        .clickable {
                            navController.navigate("home")
                        }
                ) {
                    IconSpan(id = R.drawable.home_vazado,
                        descripition = "Home",
                        navController = navController,
                        route = "home")
                }

                Spacer(modifier = Modifier.weight(1f))

                IconSpan(id = R.drawable.comunnity_icon,
                    descripition = "Comunidade",
                    navController = navController,
                    route = "comunidade")

                Spacer(modifier = Modifier.weight(1f))


                IconSpan(id = R.drawable.person_icon,
                    descripition = "Perfil",
                    navController = navController,
                    route = "perfilBarbearia")


                Spacer(modifier = Modifier.weight(1f))


            }else{
                Spacer(modifier = Modifier.weight(1f))

                IconSpan(id = R.drawable.calendar,
                    descripition = "Calendar",
                    navController = navController,
                    route = "agendaUsuarios")

                Spacer(modifier = Modifier.weight(1f))

                IconSpan(id = R.drawable.pesquisar,
                    descripition = "Pesquisar",
                    navController = navController,
                    route = "buscarBarbearias")

                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(54.dp)
                        .clip(RoundedCornerShape(28))
                        .background(BLUE_PRIMARY)
                        .clickable {
                            navController.navigate("homeUsuario")
                        }
                ) {
                    IconSpan(id = R.drawable.home_vazado,
                        descripition = "Home",
                        navController = navController,
                        route = "homeUsuario")
                }

                Spacer(modifier = Modifier.weight(1f))

                IconSpan(id = R.drawable.galeria,
                    descripition = "Galeria",
                    navController = navController,
                    route = "galeria")

                Spacer(modifier = Modifier.weight(1f))


                IconSpan(id = R.drawable.person_icon,
                    descripition = "Perfil",
                    navController = navController,
                    route = "perfilUsuario")


                Spacer(modifier = Modifier.weight(1f))
            }

        },
    )
}

@Composable
fun IconSpan(
    id: Int,
    descripition: String,
    navController: NavController,
    route: String
){

    Icon(
        painter = painterResource(id = id),
        contentDescription = descripition,
        modifier = Modifier
            .size(54.dp)
            .padding(12.dp)
            .clickable{
                navController.navigate(route)
            }
    )
}

