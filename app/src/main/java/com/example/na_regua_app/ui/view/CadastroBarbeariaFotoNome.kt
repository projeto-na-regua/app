package com.example.na_regua_app.ui.view

import android.content.ClipData.Item
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.RectangleShape
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
import coil.compose.rememberAsyncImagePainter
import com.example.na_regua_app.ui.components.Botao
import com.example.na_regua_app.ui.components.BotaoSpan
import com.example.na_regua_app.ui.components.Input
import com.example.na_regua_app.ui.components.InputCadastro
import com.example.na_regua_app.ui.components.LogoImage
import com.example.na_regua_app.ui.components.SelecaoFuncionarios
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.viewmodel.CadastroBarbeariaViewModel
import com.example.na_regua_app.viewmodel.CadastroViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CadastroBarbeariaFotoNome(
    navController: NavController,
    cadastroBarbeariaViewModel: CadastroBarbeariaViewModel = koinViewModel()
) {
    var botaoClicado by remember { mutableStateOf(false) }
    var nomeBarbearia by remember { mutableStateOf("") }
    var descricaoBarbearia by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }

    var imagemSelecionadaBanner by remember { mutableStateOf<Uri?>(null) }
    var imagemSelecionadaPerfil by remember { mutableStateOf<Uri?>(null) }

    val galleryLauncherBanner = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imagemSelecionadaBanner = uri
    }

    val galleryLauncherPerfil = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imagemSelecionadaPerfil = uri
    }



    Scaffold(
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(vertical = 50.dp, horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                item {
                    Text(
                        text = "Vamos começar!",
                        fontSize = 24.sp,
                        color = BLUE_PRIMARY,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 40.dp),
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Para cadastrar sua barbearia, informe...",
                        fontSize = 12.sp,
                        color = BLUE_PRIMARY,
                        modifier = Modifier.padding(top = 12.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 1.sp,
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column (horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth(.9f)) {
                            Text(
                                text = "Adicione a foto de fundo da sua barbearia",
                                modifier = Modifier.padding(top = 30.dp),
                                color = Color(0xFF9E9E9E),
                                fontStyle = FontStyle.Italic,
                                letterSpacing = 1.sp,
                                fontSize = 15.sp
                                )
                        }
                        Box(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .width(300.dp)
                                .height(150.dp)
                                .background(color = Color.Transparent)
                                .border(
                                    BorderStroke(2.dp, color = Color(0xFFCBD5E0)),
                                    shape = RoundedCornerShape(15.dp)   // Define o formato arredondado
                                )

                        ) {
                            if(imagemSelecionadaBanner != null){
                                Image(
                                    painter = rememberAsyncImagePainter(imagemSelecionadaBanner),
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                Image(
                                    painter = painterResource(id = com.example.na_regua_app.R.drawable.png_background),
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }


                        }
                        Button(onClick = { galleryLauncherBanner.launch("image/*") },
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
                        Column (horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth(.9f)) {
                            Text(
                                text = "Adicione a foto de perfil da sua barbearia",
                                modifier = Modifier.padding(top = 30.dp),
                                color = Color(0xFF9E9E9E),
                                fontStyle = FontStyle.Italic,
                                letterSpacing = 1.sp,
                                fontSize = 15.sp
                            )
                        }
                        Box(
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .size(150.dp)
                                .clip(CircleShape) // Certifica-se de que o Box seja recortado como um círculo
                                .background(Color.Transparent)
                                .border(1.dp, color = Color(0xFF9E9E9E), CircleShape)
                        ) {
                            if(imagemSelecionadaPerfil != null){
                                Image(
                                    painter = rememberAsyncImagePainter(imagemSelecionadaPerfil),
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop, // Isso garante que a imagem preencha o círculo corretamente
                                    modifier = Modifier.fillMaxSize() // Garante que a imagem ocupe o tamanho completo do Box
                                )
                            } else {
                                Image(
                                    painter = painterResource(id = com.example.na_regua_app.R.drawable.png_background),
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop, // Isso garante que a imagem preencha o círculo corretamente
                                    modifier = Modifier.fillMaxSize() // Garante que a imagem ocupe o tamanho completo do Box
                                )
                            }

                        }
                        Button(onClick = { galleryLauncherPerfil.launch("image/*")},
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
                                text = "Por segurança, informe seu CPF",
                                modifier = Modifier.padding(top = 20.dp),
                                color = Color(0xFF9E9E9E),
                                fontStyle = FontStyle.Italic,
                                letterSpacing = 1.sp,
                                fontSize = 15.sp
                            )
                        }
                        InputCadastro(
                            value = cpf,
                            onValueChange = { novoValor -> cpf = novoValor },
                            label = { Text("CPF") }
                        )

                        Column (horizontalAlignment = Alignment.Start,
                            modifier = Modifier.fillMaxWidth(1f)) {
                            Text(
                                text = "Este vai ser o nome da sua barbearia",
                                modifier = Modifier.padding(top = 20.dp),
                                color = Color(0xFF9E9E9E),
                                fontStyle = FontStyle.Italic,
                                letterSpacing = 1.sp,
                                fontSize = 15.sp
                            )
                        }
                        InputCadastro(
                            value = nomeBarbearia,
                            onValueChange = { novoValor -> nomeBarbearia = novoValor },
                            label = { Text("Nome da barbearia") }
                        )

                        Column (horizontalAlignment = Alignment.Start,
                            modifier = Modifier.fillMaxWidth(1f)) {
                            Text(
                                text = "Adicione uma descrição, para conhecerem melhor sobre a barbearia",
                                modifier = Modifier.padding(top = 20.dp),
                                color = Color(0xFF9E9E9E),
                                fontStyle = FontStyle.Italic,
                                letterSpacing = 1.sp,
                                fontSize = 15.sp
                            )
                        }
                        InputCadastro(
                            value = descricaoBarbearia,
                            onValueChange = { novoValor -> descricaoBarbearia = novoValor },
                            label = { Text("Descrição") }
                        )

                    }
                    Row {
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                    Botao(
                        onClick = {
//                            cadastroBarbeariaViewModel.atualizarImgBanner(imagemSelecionadaBanner)
//                            cadastroBarbeariaViewModel.atualizarImgPerfil(imagemSelecionadaPerfil)
//                            cadastroBarbeariaViewModel.atualizarDescricao(descricaoBarbearia)
                            navController.navigate("cadastroBarbeariaEndereco/$cpf/$nomeBarbearia")
                                  },
                        textButton = "Próximo"
                    )
                }

            }
        }
    )
}

@Preview
@Composable
fun CadastroBarbeariaFotoNomePreview() {
    CadastroBarbeariaFotoNome(navController = rememberNavController())
}