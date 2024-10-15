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
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.piechart.PieChart
import com.github.tehras.charts.piechart.PieChartData
import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer

@Composable
fun PieChartSpan(datas: List<Dado>, modifier: Modifier){

    var slices = ArrayList<PieChartData.Slice>()

    datas.mapIndexed{ index, data ->
        slices.add(PieChartData.Slice(
            value = data.value,
            color = (data.color ?: BLUE_PRIMARY) as Color
        ))
    }

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


        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            datas.forEach { data ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(color = data.color ?: Color.Blue, shape = RoundedCornerShape(4.dp))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "${data.label} - %${data.value}", style = androidx.compose.ui.text.TextStyle(fontSize = 14.sp))
                }
            }
        }
    }


}

@Composable
fun LineChartSpan(datas: List<Dado>, modifier: Modifier) {

    // Cria a lista de pontos para o gráfico
    val points = datas.mapIndexed { _, point ->
        LineChartData.Point(
            value = point.value,
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

    // Renderiza o gráfico de linhas
    LineChart(
        linesChartData = listOf(LineChartData(
            points = points,
            padBy = 0.1f,
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



