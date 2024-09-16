
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.R
import com.example.na_regua_app.classes.Usuario
import com.example.na_regua_app.classes.usuarios
import com.example.na_regua_app.components.BotaoComIcone
import com.example.na_regua_app.components.BottomBarCustom
import com.example.na_regua_app.components.TopBarCustom
import com.example.na_regua_app.ui.theme.textSection
import com.example.na_regua_app.ui.theme.titleSection
import com.example.na_regua_app.ui.theme.titleSectionBold

@Composable
fun ConfiguracoesSeuNegocio(
    navController: NavController,
    usuario: Usuario
) {

    Scaffold(
        topBar = {
            TopBarCustom(navController, "Configurações", false, false, true)
        },
        content = { paddingValues ->
            Column (
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    Column (
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Box (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                        ) {
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.imagem_de_fundo_exemplo),
                                    contentDescription = "Imagem de fundo da Barbearia",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(150.dp)
                                        .border(1.dp, Color(0xFFCBD5E0), RoundedCornerShape(12.dp)),
                                )

                                Box(modifier = Modifier
                                    .offset(y = (-70).dp, x = 24.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.imagem_de_perfil_barbearia),
                                        contentDescription = "Imagem de perfil da Barbearia",
                                        modifier = Modifier
                                            .size(100.dp)
                                            .clip(CircleShape)
                                            .border(1.dp, Color(0xFFCBD5E0), CircleShape)
                                    )
                                }
                            }
                        }

                        Column (
                            modifier = Modifier
                                .offset(y = (-50).dp)
                        ) {
                            Text(text = "Seu Negócio", style = titleSectionBold)

                            Spacer(modifier = Modifier.height(24.dp))

                            Box {
                                Column {
                                    Text(text = "Nome da Barbearia", style = titleSection)

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(text = "Barbearia Exemplo", style = textSection)
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Box {
                                Column {
                                    Text(text = "Telefone", style = titleSection)

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(text = "(11) 9 9999-9999", style = textSection)
                                }
                            }

                            Spacer(modifier = Modifier.height(32.dp))

                            Text(text = "Informações de Endereço", style = titleSectionBold)

                            Spacer(modifier = Modifier.height(24.dp))

                            Box {
                                Column {
                                    Text(text = "Endereço", style = titleSection)

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(text = "Rua de Exemplo, 222 - Vila Exemplo", style = textSection)
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Box {
                                Column {
                                    Text(text = "Cidade/Estado", style = titleSection)

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(text = "São Paulo - São Paulo", style = textSection)
                                }
                            }

                            Spacer(modifier = Modifier.height(32.dp))

                            Text(text = "Mais informações", style = titleSectionBold)

                            Spacer(modifier = Modifier.height(24.dp))

                            Box {
                                Column {
                                    Text(text = "Descrição", style = titleSection)

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(text = "Resumo de Exemplo da barbearia", style = textSection)
                                }
                            }

                            Spacer(modifier = Modifier.height(32.dp))

                            Button(
                                onClick = {
                                    navController.navigate("deletebusiness")
                                },
                                colors = ButtonDefaults.buttonColors (
                                    containerColor = Color.Transparent,
                                    contentColor = Color(0xFFCC2828)
                                ),
                                border = BorderStroke(1.dp, Color(0xFFCC2828)),
                                shape = RoundedCornerShape(12.dp),
                                contentPadding = PaddingValues(16.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Row (
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Excluir barbearia",
                                        style = androidx.compose.ui.text.TextStyle(
                                            color = Color(0xFFCC2828),
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )

                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowRight,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        BotaoComIcone(
                            onClick = { /*TODO*/ },
                            textButton = "Editar Informações",
                            imagePainter = painterResource(id = R.drawable.icon_edit),

                            )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Desenvolvido por NaRégua", style = textSection)
                    }
                }
            }
        },
        bottomBar = {
            BottomBarCustom(navController, usuario)
        }
    )
}

@Preview
@Composable
fun PreviewConfiguracoesSeuNegocio() {
    val usuarios = usuarios()
    ConfiguracoesSeuNegocio(navController = rememberNavController(), usuario = usuarios.first())
}
