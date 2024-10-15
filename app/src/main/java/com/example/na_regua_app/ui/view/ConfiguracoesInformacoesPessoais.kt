
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.na_regua_app.R
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.model.usuarios
import com.example.na_regua_app.ui.components.BotaoComIcone
import com.example.na_regua_app.ui.components.BottomBarCustom
import com.example.na_regua_app.ui.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.textSection
import com.example.na_regua_app.ui.theme.titleSection
import com.example.na_regua_app.ui.theme.titleSectionBold

@Composable
fun ConfiguracoesInformacoesPessoais(
    navController: NavController,
    usuario: Usuario
) {
    Scaffold(
        topBar = {
            TopBarCustom(navController, "Configurações", false, false, true)
        },
        content = { paddingValues ->
            Column(
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
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ImageWithEditIcon(
                                painter = painterResource(id = R.drawable.foto_exemplo),
                                onClick = {},
                                shape = CircleShape
                            )

                            Column {
                                Text(text = "Formatos permitidos", style = titleSection)

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(text = "JPG, PNG e JPEG", style = textSection)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Column {
                            Text(text = "Informações Pessoais", style = titleSectionBold)

                            Spacer(modifier = Modifier.height(24.dp))

                            Box {
                                Column {
                                    Text(text = "Apelido", style = titleSection)

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(text = "@antonio", style = textSection)
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Box {
                                Column {
                                    Text(text = "Nome", style = titleSection)

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(text = "Antonio", style = textSection)
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Box {
                                Column {
                                    Text(text = "E-mail", style = titleSection)

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(text = "antonio@email.com", style = textSection)
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Box {
                                Column {
                                    Text(text = "Senha", style = titleSection)

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(text = "************", style = textSection)
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

                            Button(
                                onClick = {
                                    navController.navigate("deleteaccount")
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = Color(0xFFCC2828)
                                ),
                                border = BorderStroke(1.dp, Color(0xFFCC2828)),
                                shape = RoundedCornerShape(12.dp),
                                contentPadding = PaddingValues(16.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Excluir conta",
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
fun ConfiguracoesInformacoesPessoaisPreview() {
    val usuarios = usuarios()
    ConfiguracoesInformacoesPessoais(navController = rememberNavController(), usuarios.first())
}

@Composable
fun ImageWithEditIcon(
    painter: Painter,
    onClick: () -> Unit,
    width: Int = 100,
    height: Int = 100,
    shape: Shape
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.clickable { onClick() }
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width.dp, height.dp)
                .border(
                    BorderStroke(2.dp, Color(0xFFCBD5E0)),
                    shape = shape
                )
        )

        Box (
            modifier = Modifier
                .offset(x = (-40).dp)
                .background(BLUE_PRIMARY, CircleShape)
                .size(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = ORANGE_SECUNDARY,
            )
        }
    }
}
