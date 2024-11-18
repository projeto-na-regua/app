package com.example.na_regua_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.na_regua_app.data.model.Dado
import com.example.na_regua_app.ui.theme.BLUE_PRIMARY
import com.example.na_regua_app.ui.theme.ORANGE_SECUNDARY
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.piechart.PieChart
import com.github.tehras.charts.piechart.PieChartData
import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer

@Composable
fun PieChartSpan(lucratividade: Dado, modifier: Modifier) {
    // Limita o valor de lucratividade ao máximo de 100
    val lucro = if (lucratividade.value.toFloat() > 100) 100f else lucratividade.value.toFloat()
    val outraParteValor = 100f - lucro

    // Cria as fatias do gráfico
    val slices = listOf(
        PieChartData.Slice(
            value = lucro,
            color = lucratividade.color ?: BLUE_PRIMARY
        ),
        PieChartData.Slice(
            value = if (outraParteValor < 0) 0f else outraParteValor,
            color = ORANGE_SECUNDARY  // Cor da parte restante
        )
    )

    Column {
        PieChart(
            pieChartData = PieChartData(
                slices = slices
            ),
            modifier = modifier,
            sliceDrawer = SimpleSliceDrawer(
                sliceThickness = 100f
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Exibe as legendas com uma casa decimal
            Text(
                text = String.format("%s - %.1f%%", lucratividade.label, lucro),
                style = androidx.compose.ui.text.TextStyle(fontSize = 14.sp)
            )
            Text(
                text = String.format("Despesa - %.1f%%", outraParteValor),
                style = androidx.compose.ui.text.TextStyle(fontSize = 14.sp)
            )
        }
    }
}

@Composable
fun LineChartSpan(datas: List<Dado>, modifier: Modifier) {

    println("Dados recebidos para o gráfico: $datas")

    // Cria a lista de pontos para o gráfico
    val points = datas.mapIndexed { _, point ->
        LineChartData.Point(
            value = point.value.toFloat(),
            label = point.label
        )
    }

    // Define a cor da linha e dos pontos
    val lineColor = BLUE_PRIMARY

    // Define o LineDrawer com a cor e o shader de gradiente
    val lineDrawer = SolidLineDrawer(
        color = lineColor,
        thickness = 4.dp,
    )

    LineChart(
        linesChartData = listOf(LineChartData(
            points = points,
            padBy = .1f,
            startAtZero = true,
            lineDrawer = lineDrawer
        )),
        modifier = modifier,
        pointDrawer = FilledCircularPointDrawer(
            color = lineColor,
            diameter = 5.dp
        ),
        labels = points.map { it.label }
    )
}



