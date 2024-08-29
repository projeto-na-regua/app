
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.WHITE_BACKGROUND


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier.background(White),
        topBar = {
            TopBarCustom(navController, "Home", true)
        },
        content = { paddingValues ->
            HomeContent(
                paddingValues = paddingValues
            )
        },
        bottomBar = {
            BottomBarCustom(navController)
        }
    )
}


@Composable
fun HomeContent(paddingValues: PaddingValues) {

    var nomeUsuario by remember { mutableStateOf("Melissa") }
    var agendamentosProximos by remember { mutableIntStateOf(2) }
    var possuiAgendamentos by remember { mutableStateOf(true) }
    var saldo by remember { mutableDoubleStateOf(214.00) }
    var visitantes by remember { mutableIntStateOf(50) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.White)
    ) {

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
                            append(nomeUsuario.fraseLaranja())
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


        Espacamento(70.dp)


        Text(
            text = "Agendamentos próximos",
            color = BLUE_PRIMARY,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Espacamento(15.dp)


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(20.dp, 0.dp)
                .border(
                width = 1.dp,
                color = WHITE_BACKGROUND,
                RoundedCornerShape(15))
                .height(110.dp)
        ){

            if(possuiAgendamentos){
                Text(text = buildAnnotatedString {
                    append("Você possui um compromisso pendente. ")
                    append("Pressione para \nvisualizar.".fraseLaranja())
                },
                modifier = Modifier.padding(15.dp),
                fontSize = 18.sp,
                lineHeight = 25.sp,
                fontWeight = FontWeight.Light,
                color = BLUE_PRIMARY
                )
            }
        }

        Espacamento(15.dp)


        Row (
            modifier = Modifier.fillMaxWidth().padding(20.dp, 0.dp),
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
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val navController = rememberNavController()
    Home(navController = navController)
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
            RoundedCornerShape(15))
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
                        fontSize = 20.sp,
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


