package com.example.na_regua_app.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.R
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.model.usuarios
import com.example.na_regua_app.ui.components.BotaoIcon
import com.example.na_regua_app.ui.components.BottomBarCustom
import com.example.na_regua_app.ui.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.WHITE_BACKGROUND
import com.example.na_regua_app.utils.obterUsuarioDtype
import com.example.na_regua_app.viewmodel.FinancaViewModel
import com.google.android.material.progressindicator.CircularProgressIndicator
import org.koin.compose.viewmodel.koinViewModel
import java.time.LocalDate



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home(
    navController: NavController, usuario: Usuario
) {
    Scaffold(
        modifier = Modifier.background(White),
        topBar = {
            TopBarCustom(navController, "Home", true)
        },
        content = { paddingValues ->
            HomeContent(
                paddingValues = paddingValues, navController
            )
        },
        bottomBar = {
            BottomBarCustom(navController, LocalContext.current)
        }
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeContent(paddingValues: PaddingValues, navController: NavController, financaViewModel: FinancaViewModel = koinViewModel()) {

    var agendamentosProximos = financaViewModel.agendamentosProximos.collectAsState().value
    var agendamentosPendentes = financaViewModel.agendamentosPendentes.collectAsState().value
    var saldo = financaViewModel.dadosSaldoHome.collectAsState().value
    var visitantes = financaViewModel.qtdClientesHome.collectAsState().value

    var isLoading = true
    var isLoadingSaldo = true
    var isLoadingClientes = true;

    val context = LocalContext.current
    val nomeUsuario = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {

        financaViewModel.obterMetricasGerais(LocalDate.now(), LocalDate.now().plusDays(3), 0) { success -> if(success){ isLoading = false }
        }

        financaViewModel.obterFinancasHome(999, LocalDate.now().minusDays(999), LocalDate.now()) { success ->
            if(success){ isLoadingSaldo = false }
        }

        financaViewModel.obterMetricasGeraisClientes(LocalDate.now().minusDays(999), LocalDate.now(), 999) { success ->
            if(success) { isLoadingClientes = false }
        }

        obterUsuarioDtype(context).collect { userDtype ->
            userDtype?.let {
                nomeUsuario.value = it.nome.split(" ").firstOrNull() ?: ""
                println("Nome do usuário: ${it.nome}")
            } ?: run {
                println("Usuário não encontrado")
            }
        }
    }


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White)
            ) {

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(White)

                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .background(BLUE_PRIMARY)
                                .drawBehind {
                                    val strokeWidth = 10.dp.toPx()
                                    val y = size.height - strokeWidth / 2
                                    drawLine(
                                        color = ORANGE_SECUNDARY,
                                        start = Offset(0f, y),
                                        end = Offset(size.width, y),
                                        strokeWidth = strokeWidth
                                    )
                                }
                        ) {

                            Column (
                                modifier = Modifier.padding(20.dp)
                            ){
                                Text(
                                    text = buildAnnotatedString {
                                        append("Bom dia, ")
                                        append(nomeUsuario.value.fraseLaranja())
                                        append("!")
                                    },
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Normal
                                )

                                Espacamento(10.dp)

                                MiddleText(
                                    text = "Abaixo estão as informações do dia.", White
                                )
                            }
                        }


                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .offset(y = 60.dp)
                                .size(130.dp)
                                .clip(RoundedCornerShape(50))
                                .background(Color.Transparent)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(50))
                                    .background(Color.White)
                                    .border(
                                        width = 10.dp,
                                        color = ORANGE_SECUNDARY,
                                        shape = RoundedCornerShape(50)
                                    )
                            ) {
                                    Text(
                                        text = "$agendamentosProximos",
                                        color = BLUE_PRIMARY,
                                        fontSize = 40.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.align(Alignment.Center)
                                    )

                            }
                        }
                    }
                }


                item {
                    Espacamento(70.dp)
                }


                item {
                    Text(
                        text = "Agendamentos próximos",
                        color = BLUE_PRIMARY,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    Espacamento(10.dp)
                }

                item {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ){
                        Text("Para você", fontSize = 16.sp)
                        Box (modifier = Modifier.width(230.dp)){
                            BotaoIcon(textButton = "Adicionar campo", onClick = {}, idIcon = R.drawable.add_campos, tamFont = 14.sp, altura = 50.dp)
                        }

                    }
                }


               item {
                   Box(
                       contentAlignment = Alignment.Center,
                       modifier = Modifier
                           .padding(20.dp, 0.dp)
                           .border(
                               width = 1.dp,
                               color = WHITE_BACKGROUND,
                               RoundedCornerShape(15)
                           )
                           .height(110.dp)
                   ){

                       if(agendamentosPendentes > 1){
                           Text(text = buildAnnotatedString {
                               append("Você possui $agendamentosPendentes compromissos pendentes. ")
                               append("Pressione para \nvisualizar.".fraseLaranja())
                           },
                               modifier = Modifier.padding(15.dp)
                                   .clickable { navController.navigate("dashboard") },
                               fontSize = 18.sp,
                               lineHeight = 25.sp,
                               fontWeight = FontWeight.Light,
                               color = BLUE_PRIMARY
                           )
                       } else if (agendamentosPendentes > 0){
                           Text(text = buildAnnotatedString {
                               append("Você possui um compromissos pendentes. ")
                               append("Pressione para \nvisualizar.".fraseLaranja())
                           },
                               modifier = Modifier.padding(15.dp)
                                   .clickable { navController.navigate("dashboard") },
                               fontSize = 18.sp,
                               lineHeight = 25.sp,
                               fontWeight = FontWeight.Light,
                               color = BLUE_PRIMARY
                           )
                       } else {
                           Text(text = buildAnnotatedString {
                               append("Você não possui nenhum compromisso pendente ou agendado.")
                           },
                               modifier = Modifier.padding(15.dp),
                               fontSize = 18.sp,
                               lineHeight = 25.sp,
                               fontWeight = FontWeight.Light,
                               color = BLUE_PRIMARY
                           )
                       }
                   }
               }

               item {
                   Espacamento(15.dp)
               }


                item {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ){
                        CardIconText(value ="R$${"%.2f".format(saldo)}",
                            icon =  painterResource(R.drawable.money_icon),
                            title = "Seu saldo atual"
                        )

                        Espacamento(15.dp)

                        CardIconText(value ="$visitantes",
                            icon =  painterResource(R.drawable.visitors_icon),
                            title = "Visitantes"
                        )
                    }
                }

                item {
                    Text(
                        text = "Mais informações",
                        color = BLUE_PRIMARY,
                        fontSize = 16.sp,
                        letterSpacing = .5.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 20.dp)
                    )
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(20.dp, bottom = 20.dp, end = 20.dp)
                            .clip(RoundedCornerShape(15.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.home_barbeiro),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        Box(
                            modifier = Modifier
                                .matchParentSize()
                        )
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .fillMaxHeight()
                                .fillMaxWidth(.9f)
                        ) {
                            Text(
                                text = buildAnnotatedString {
                                    append("Como usar nossa ")

                                    withStyle(style = SpanStyle(color = Color(0xFFFFA500))) { // Cor laranja
                                        append("plataforma")
                                    }

                                    append("?")
                                },
                                color = White, // Define a cor padrão do restante do texto
                                fontWeight = FontWeight(500),
                                letterSpacing = 1.sp,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            Text(
                                text = "Um tutorial fácil e prático para a utilização da plataforma",
                                color = White,
                                fontSize = 14.sp,
                                letterSpacing = .5.sp,
                                lineHeight = 24.sp,
                                modifier = Modifier.fillMaxWidth(.8f),
                                fontWeight = FontWeight(400)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate("cadastroBarbearia")
                                    }
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(7.dp))
                                    .background(color = BLUE_PRIMARY)
                                    .padding(5.dp)
                                    .align(Alignment.BottomEnd),
                                contentAlignment = Alignment.Center,
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.icone_setinha_svg),
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }

            }

    }




@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val navController = rememberNavController()
    val usuarios = usuarios()
    Home(navController = navController, usuarios.first())
}


@Composable
fun TitleText(text: String, color: Color){

    Text(text = text,
        color = color,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold)
}

@Composable
fun MiddleText(text: String, color: Color){

    Text(text = text,
        color = color,
        fontSize = 18.sp,
        fontWeight = FontWeight.Light)
}

@Composable
fun Espacamento(espaco: Dp){
    Spacer(modifier = Modifier.size(espaco))
}

fun String.fraseLaranja(): androidx.compose.ui.text.AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = SpanStyle(color = ORANGE_SECUNDARY)) {
            append(this@fraseLaranja) // Adiciona a string à Anotação
        }
    }
}


fun ModifierCardBoxCustom(height: Dp) : Modifier{
    return Modifier
        .border(
            width = 1.dp,
            color = WHITE_BACKGROUND,
            RoundedCornerShape(15)
        )
        .height(height)
}

@Composable
fun CardIconText(value: String, title: String, icon: Painter){

    Box(
        contentAlignment = Alignment.Center,
        modifier =  ModifierCardBoxCustom(100.dp).width(180.dp)
    ){
        Column (
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .size(40.dp)
                        .clip(RoundedCornerShape(28))
                        .background(BLUE_PRIMARY)
                ) {
                    Icon(
                        painter = icon,
                        tint = ORANGE_SECUNDARY,
                        contentDescription = title,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(8.dp)
                            .size(36.dp)
                    )
                }

                Box(modifier = Modifier.width(100.dp)){
                    Text(
                        text = value,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = BLUE_PRIMARY)
                }
            }

            Espacamento(10.dp)
            Text(
                modifier = Modifier.padding(10.dp, 0.dp),
                text =  title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = BLUE_PRIMARY)
        }
    }

}


