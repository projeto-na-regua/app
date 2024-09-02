package com.example.na_regua_app.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Input(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .height(70.dp),
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BLUE_PRIMARY,
            unfocusedBorderColor = BLUE_PRIMARY,
            focusedLabelColor = BLUE_PRIMARY,
            unfocusedLabelColor = BLUE_PRIMARY
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Input(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
    focusedBorderColor: Color,
    unfocusedBorderColor: Color,
    focusedLabelColor: Color,
    unfocusedLabelColor: Color
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .height(70.dp),
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = focusedBorderColor,
            unfocusedBorderColor = unfocusedBorderColor,
            focusedLabelColor = focusedLabelColor,
            unfocusedLabelColor = unfocusedLabelColor
        )
    )
}