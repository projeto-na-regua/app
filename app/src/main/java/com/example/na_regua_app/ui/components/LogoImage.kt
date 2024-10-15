package com.example.na_regua_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun LogoImage(){
    Image(
        painter = painterResource(id = com.example.na_regua_app.R.drawable.logooficial),
        contentDescription = "Logo da empresa",
        modifier = Modifier.fillMaxWidth(0.8f),
        contentScale = ContentScale.Crop
    )
}