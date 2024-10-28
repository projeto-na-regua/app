import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class DashboardConsulta(
    var confirmados: Long? = null,
    var pendentes: Long? = null,
    var cancelados: Long? = null,
    var mediaAvaliacoes: Double? = null,
    var datasGrafico: List<String> = emptyList(), // Mantenha como String
    var valoresGrafico: List<Long> = emptyList()
) {
    // Método para converter datasGrafico de String para LocalDate
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDatasGraficoAsLocalDate(): List<LocalDate> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return datasGrafico.map { LocalDate.parse(it, formatter) }
    }
}
