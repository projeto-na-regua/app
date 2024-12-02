import com.example.na_regua_app.data.model.Barbearia
import com.example.na_regua_app.data.model.DiaSemana
import com.example.na_regua_app.data.repository.BarbeariaRepository
import com.example.na_regua_app.viewmodel.PerfilBarbeariaViewModel
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class PerfilBarbeariaViewModelTest {

    @Mock
    private lateinit var barbeariaRepository: BarbeariaRepository

    private lateinit var viewModel: PerfilBarbeariaViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = PerfilBarbeariaViewModel(barbeariaRepository)
    }

//    @Test
//    fun `deve atualizar horario da barbearia com sucesso`() = runBlockingTest {
//        // Simulando o horário a ser atualizado
//        val horarioAtualizado = HorarioBarbearia("10:00", "18:00")
//
//        // Simulando o serviço ou objeto que será comparado
//        val horarioEsperado = HorarioBarbearia("10:00", "18:00")
//
//        // Verificação para garantir que os objetos não são nulos antes de comparar
//        assertNotNull(horarioAtualizado)
//        assertNotNull(horarioEsperado)
//
//        // Agora, podemos usar eq() com segurança
//        assertTrue(horarioAtualizado == horarioEsperado)  // Exemplo de comparação
//    }

}
