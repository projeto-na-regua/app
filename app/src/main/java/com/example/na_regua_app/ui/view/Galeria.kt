package com.example.na_regua_app.ui.view

import android.content.Context
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.na_regua_app.R
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.model.usuarios
import com.example.na_regua_app.ui.components.BotaoAjustavel
import com.example.na_regua_app.ui.components.BottomBarCustom
import com.example.na_regua_app.ui.components.TopBarCustom
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.example.na_regua_app.viewmodel.GaleriaViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.compose.viewmodel.koinViewModel
import java.io.FileNotFoundException
import java.io.InputStream

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Galeria(
    navController: NavController,
    usuario: Usuario,
    galeriaViewModel: GaleriaViewModel = koinViewModel()
) {

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var imageList by remember { mutableStateOf(listOf<Uri?>()) } // Lista de imagens
    var imagens = galeriaViewModel.imagensGaleria.collectAsState().value

    LaunchedEffect(Unit) {
        galeriaViewModel.getImagens()
    }

    val context = LocalContext.current
    val getImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageList = imageList + uri
    }

    Scaffold(
        topBar = {
            TopBarCustom(navController, "Galeria", true)
        },
        content = { paddingValues ->
            GaleriaContent(paddingValues, getImage, selectedImageUri, imageList,galeriaViewModel = galeriaViewModel, context = LocalContext.current)
        },
        bottomBar = {
            BottomBarCustom(navController, context)
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GaleriaContent(
    paddingValues: PaddingValues,
    getImage: ActivityResultLauncher<String>,
    selectedImageUri: Uri?,
    imageList: List<Uri?>,
    galeriaViewModel: GaleriaViewModel,
    context: Context
) {
    // Combina as imagens da API e as imagens selecionadas
    val imagens = galeriaViewModel.imagensGaleria.collectAsState().value
    val allImages = imagens!! + imageList // Mescla as imagens da API com as selecionadas

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp) // Deixa espaço para o botão na parte inferior
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Suas fotos",
                    fontSize = 18.sp,
                    color = BLUE_PRIMARY,
                    fontWeight = FontWeight(500)
                )

                Button(
                    onClick = { getImage.launch("image/*") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BLUE_PRIMARY
                    ),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(top = 15.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icone_adicionar_foto),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "Adicionar foto",
                        color = ORANGE_SECUNDARY,
                        fontSize = 14.sp,
                        letterSpacing = 1.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Display selected image if any
            selectedImageUri?.let { uri ->
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = "Selected image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp) // Adjust the height as needed
                        .clip(RoundedCornerShape(15.dp))
                        .border(
                            BorderStroke(2.dp, color = Color(0xFFCBD5E0)),
                            shape = RoundedCornerShape(15.dp)
                        )
                )
            }

            // Display the list of images in a 2-column grid
            Spacer(modifier = Modifier.height(20.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp), // Add padding to the grid
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(allImages) { imageUri ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .background(color = Color.Transparent)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(imageUri),
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(15.dp))
                                .border(
                                    BorderStroke(2.dp, color = Color(0xFFCBD5E0)),
                                    shape = RoundedCornerShape(15.dp)
                                )
                        )
                    }
                }
                if (allImages.size % 2 != 0) {
                    item {
                        Spacer(modifier = Modifier.height(150.dp))
                    }
                }
            }
        }

        // Floating Button, always positioned at the bottom center
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(15.dp)
        ) {
            BotaoAjustavel(
                onClick = {
                    if (imageList.isNotEmpty()) {
                        imageList.forEach { uri ->
                            uri?.let {
                                postImagem(context, it, galeriaViewModel) { success ->
                                    if (success) {
                                            Toast.makeText(context, "Imagem enviada com sucesso!", Toast.LENGTH_SHORT).show()
                                    } else {
                                            Toast.makeText(context, "Erro ao enviar imagem", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                    } else {
                        Toast.makeText(context, "Nenhuma imagem selecionada", Toast.LENGTH_SHORT).show()
                    }
                },
                textButton = "Salvar",
                shape = RoundedCornerShape(10.dp),
                fontSize = 20
            )
        }
    }
}




@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun GaleriaPreview() {
    val navController = rememberNavController()
    Galeria(navController = navController, usuarios()[1])
}

@RequiresApi(Build.VERSION_CODES.O)
fun postImagem(context: Context, uri: Uri, galeriaViewModel: GaleriaViewModel, onResult: (Boolean) -> Unit) {
    val inputStream = getInputStreamFromUri(context, uri)
    if (inputStream != null) {
        // Converte o InputStream para RequestBody
        val requestBody = inputStream.readBytes().toRequestBody("image/*".toMediaTypeOrNull())

        // Cria o MultipartBody.Part para enviar no request
        val imagePart = MultipartBody.Part.createFormData("imagem", "image.jpg", requestBody)

        // Chama a função no ViewModel, passando o MultipartBody.Part
        galeriaViewModel.postarImagem(imagePart) { success ->
            if (success) {
                onResult(true)
            } else {
                onResult(false)
            }
        }
    } else {
        Toast.makeText(context, "Erro ao abrir o arquivo", Toast.LENGTH_SHORT).show()
    }
}

// Função auxiliar para obter o InputStream a partir do Content URI
private fun getInputStreamFromUri(context: Context, uri: Uri): InputStream? {
    return try {
        context.contentResolver.openInputStream(uri)
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
        null
    }
}




