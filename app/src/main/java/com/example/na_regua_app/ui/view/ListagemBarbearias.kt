// ListagemBarbearias.kt
package com.example.na_regua_app.ui.view

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.na_regua_app.R
import com.example.na_regua_app.data.model.BarbeariaArgs
import com.example.na_regua_app.data.model.BarbeariaPesquisa
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.model.usuarios
import com.example.na_regua_app.ui.components.BarraPesquisar
import com.example.na_regua_app.ui.components.BottomBarCustom
import com.example.na_regua_app.ui.components.TopBarCustom
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.ui.theme.Typography
import com.example.na_regua_app.utils.obterToken
import com.example.na_regua_app.viewmodel.PesquisaViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ListagemBarbearias(
    navController: NavHostController,
    usuario: Usuario,
    args: BarbeariaArgs,
    pesquisaViewmodel: PesquisaViewModel = koinViewModel()
) {
    // Colete a lista de barbearias como um estado
    val barbearias = remember { mutableStateListOf<BarbeariaPesquisa>() }
    val servico = args.servico ?: "Serviço vazio"
    val data = args.data ?: "Data vazia"
    val hora = args.hora ?: "Hora vazia"
    val nomeBarbearia = args.nomeBarbearia

    Scaffold(
        modifier = Modifier.background(Color.White),
        topBar = {
            TopBarCustom(navController, "Buscar", true)
        },
        content = { paddingValues ->
            val context = LocalContext.current
            val tokenFlow = obterToken(context).collectAsState(initial = "VAZIO")

            // Atualizar o ViewModel assim que o token for obtido

            if (nomeBarbearia != null) {
                LaunchedEffect(tokenFlow.value) {
                    pesquisaViewmodel.atualizarToken(tokenFlow.value)

                    pesquisaViewmodel.listarBarbeariasPorNome(nomeBarbearia) { success, barbeariasList: List<BarbeariaPesquisa>? ->
                        if (success) {
                            Log.d("Listar barbearias", "Listagem de barbearias realizada com sucesso!")
                            barbearias.clear()
                            barbearias.addAll(barbeariasList ?: emptyList())
                            Toast.makeText(context, "Listagem de barbearias realizada com sucesso!", Toast.LENGTH_SHORT).show()
                            println("MEU DEUS OLHA A LISTA DE BARBEARIAS: $barbeariasList")
                        } else {
                            Log.d("Listar barbearias", "Erro ao listar as barbearias.")
                            Toast.makeText(context, "Erro ao listar as barbearias. Tente novamente.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                LaunchedEffect(tokenFlow.value) {
                    pesquisaViewmodel.atualizarData(data)
                    pesquisaViewmodel.atualizarHora(Uri.decode(hora))
                    pesquisaViewmodel.atualizarServico(servico)
                    pesquisaViewmodel.atualizarRaio(500000)
                    pesquisaViewmodel.atualizarToken(tokenFlow.value)

                    pesquisaViewmodel.listarBarbeariasWToken(context) { success, barbeariasList: List<BarbeariaPesquisa>? ->
                        if (success) {
                            barbearias.clear()
                            barbearias.addAll(barbeariasList ?: emptyList())
                            Toast.makeText(context, "Listagem de barbearias realizada com sucesso!", Toast.LENGTH_SHORT).show()
                            println("MEU DEUS OLHA A LISTA DE BARBEARIAS: $barbeariasList")
                        } else {
                            Log.d("Listar barbearias", "Erro ao listar as barbearias.")
                            Toast.makeText(context, "Erro ao listar as barbearias. Tente novamente.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }



            LazyColumn(
                contentPadding = paddingValues,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            ) {
                item {
                    BarraPesquisar({ navController.navigate("listagemBarbearias") }, navController, nomeBarbearia!!)
                }
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
                // Adiciona um item para cada barbearia na lista
                items(barbearias) { barbearia ->
                    CardSelecionarBarbearia(
                        barbearia = barbearia,
                        navController = navController
                    )
                }
            }
        },
        bottomBar = {
            BottomBarCustom(navController, LocalContext.current)
        }
    )
}

@Composable
fun CardSelecionarBarbearia(
    barbearia: BarbeariaPesquisa, // Supondo que você tenha uma classe BarbeariaPesquisa
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp))
            .clickable { navController.navigate("perfilBarbearia/${false}/${barbearia.id}") }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // Caixa para a imagem da barbearia
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
                    // Verifica se imgPerfil é uma URL ou um ID de recurso
                    if (barbearia.imgPerfil.isNotEmpty()) {
                        Image(
                            painter = rememberAsyncImagePainter(model = barbearia.imgPerfil),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.capa_perfil_barbeiro),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            // Caixa para informações da barbearia
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = barbearia.nomeNegocio,
                        style = Typography.labelLarge
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "${barbearia.logradouro} - ${barbearia.numero}",
                        style = Typography.labelSmall
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.LocationOn, contentDescription = "")
                        Text(
                            text = "200m",
                            style = Typography.labelSmall
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Icon(imageVector = Icons.Default.Star, tint = ORANGE_SECUNDARY, contentDescription = "")
                        Text(
                            text = barbearia.mediaAvaliacao.toString(),
                            style = Typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}



@Preview
@Composable
fun ListagemBarbeariasPreview() {
    ListagemBarbearias(rememberNavController(), usuarios()[1], )
}

fun ListagemBarbearias(navController: NavHostController, usuario: Usuario) {
    TODO("Not yet implemented")
}

