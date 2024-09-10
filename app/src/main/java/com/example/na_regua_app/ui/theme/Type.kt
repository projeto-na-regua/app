package com.example.na_regua_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.na_regua_app.ui.theme.FontProvider.PlusJakartaSans

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    titleLarge = TextStyle(
        color = BLUE_PRIMARY,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    ),

    titleMedium = TextStyle(
        color = BLUE_PRIMARY,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    ),

    labelLarge = TextStyle(
        color = BLUE_PRIMARY,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    ),

    labelMedium = TextStyle(
        color = BLUE_PRIMARY,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),

    labelSmall = TextStyle(
        color = BLUE_PRIMARY,
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal
    ),




    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val labelLargeOrange = TextStyle(
    color = ORANGE_SECUNDARY,
    fontSize = 16.sp,
    fontWeight = FontWeight.Bold
)