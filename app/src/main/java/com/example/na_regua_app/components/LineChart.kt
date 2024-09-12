package com.example.na_regua_app.components

import android.text.TextPaint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY

@Composable
fun LineChart(
    barValues: List<Float>, // valores do gráfico
    modifier: Modifier, // O Modifier padrão é um valor vazio
    labelValues: List<String> = listOf() // Labels para os pontos do gráfico
) {
    // Checa se a lista de labels tem o mesmo tamanho que a de valores
    val hasLabels = labelValues.size == barValues.size

    Canvas(modifier = modifier) {
        val maxBarValue = barValues.maxOrNull() ?: 1f
        val minBarValue = barValues.minOrNull() ?: 0f

        // Margem para desenhar os eixos
        val yAxisMargin = 60f
        val xAxisMargin = 80f

        // Calcula o espaço disponível para os pontos
        val chartWidth = size.width - xAxisMargin
        val pointSpacing = if (barValues.size > 1) chartWidth / (barValues.size - 1) else chartWidth

        // Desenhar eixo X
        drawLine(
            color = Color.Black,
            start = androidx.compose.ui.geometry.Offset(xAxisMargin, size.height - yAxisMargin),
            end = androidx.compose.ui.geometry.Offset(size.width, size.height - yAxisMargin),
            strokeWidth = 4f
        )

        // Desenhar eixo Y
        drawLine(
            color = Color.Black,
            start = androidx.compose.ui.geometry.Offset(xAxisMargin, 0f),
            end = androidx.compose.ui.geometry.Offset(xAxisMargin, size.height - yAxisMargin),
            strokeWidth = 4f
        )

        // Desenhar linha conectando os pontos
        for (i in 0 until barValues.size - 1) {
            val startX = xAxisMargin + i * pointSpacing
            val startY = size.height - yAxisMargin - (barValues[i] / maxBarValue) * (size.height - yAxisMargin)
            val endX = xAxisMargin + (i + 1) * pointSpacing
            val endY = size.height - yAxisMargin - (barValues[i + 1] / maxBarValue) * (size.height - yAxisMargin)

            drawLine(
                color = BLUE_PRIMARY,
                start = androidx.compose.ui.geometry.Offset(startX, startY),
                end = androidx.compose.ui.geometry.Offset(endX, endY),
                strokeWidth = 5f
            )
        }

        // Desenhar pontos no gráfico
        barValues.forEachIndexed { index, value ->
            val x = xAxisMargin + index * pointSpacing
            val y = size.height - yAxisMargin - (value / maxBarValue) * (size.height - yAxisMargin)

            // Desenhar um círculo para marcar cada ponto
            drawCircle(
                color = BLUE_PRIMARY,
                radius = 8f,
                center = androidx.compose.ui.geometry.Offset(x, y)
            )

            // Desenhar labels abaixo de cada ponto no eixo X
            if (hasLabels) {
                drawIntoCanvas { canvas ->
                    val textPaint = TextPaint().apply {
                        color = android.graphics.Color.BLACK
                        textSize = 40f
                    }
                    canvas.nativeCanvas.drawText(
                        labelValues[index],
                        x - 20f, // Ajuste horizontal
                        size.height - yAxisMargin + 40f, // Posição logo abaixo do eixo X
                        textPaint
                    )
                }
            }
        }

        // Desenhar marcas no eixo Y e seus valores
        val steps = 5
        for (i in 0..steps) {
            val y = size.height - yAxisMargin - i * (size.height - yAxisMargin) / steps
            val labelValue = minBarValue + (i * (maxBarValue - minBarValue)) / steps

            // Desenhar marca no eixo Y
            drawLine(
                color = Color.Black,
                start = androidx.compose.ui.geometry.Offset(xAxisMargin - 10f, y),
                end = androidx.compose.ui.geometry.Offset(xAxisMargin, y),
                strokeWidth = 3f
            )

            // Desenhar valores no eixo Y
            drawIntoCanvas { canvas ->
                val textPaint = TextPaint().apply {
                    color = android.graphics.Color.BLACK
                    textSize = 30f
                }
                canvas.nativeCanvas.drawText(
                    String.format("%.1f", labelValue),
                    xAxisMargin - 50f, // Ajuste horizontal para o valor Y
                    y + 10f, // Ajuste vertical
                    textPaint
                )
            }
        }
    }
}
