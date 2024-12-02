import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.na_regua_app.viewmodel.CadastroBarbeariaViewModel
import com.example.na_regua_app.data.repository.UsuarioRepository
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock

class CadastroBarbeariaViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CadastroBarbeariaViewModel
    private val usuarioRepository: UsuarioRepository = mock()

    @Before
    fun setup() {
        viewModel = CadastroBarbeariaViewModel(usuarioRepository)
    }

    @Test
    fun `atualizarCpf deve alterar o valor do cpf`() {
        // Definir o novo CPF
        val novoCpf = "123.456.789-00"

        // Chamar a função
        viewModel.atualizarCpf(novoCpf)

        // Verificar se o CPF foi alterado
        assertEquals(novoCpf, viewModel.cpf.value)
    }

    @Test
    fun `atualizarCep deve alterar o valor do cep`() {
        // Definir o novo CEP
        val novoCep = "05882000"

        // Chamar a função
        viewModel.atualizarCep(novoCep)

        // Verificar se o CEP foi alterado
        assertEquals(novoCep, viewModel.cep.value)
    }

    @Test
    fun `atualizarLogradouro deve alterar o valor do logradouro`() {
        // Definir o novo logradouro
        val novoLogradouro = "Rua Henrique Sam Mindlin"

        // Chamar a função
        viewModel.atualizarLogradouro(novoLogradouro)

        // Verificar se o logradouro foi alterado
        assertEquals(novoLogradouro, viewModel.logradouro.value)
    }

    @Test
    fun `atualizarNumero deve alterar o valor do numero`() {
        // Definir o novo número
        val novoNumero = "1263"

        // Chamar a função
        viewModel.atualizarNumero(novoNumero)

        // Verificar se o número foi alterado
        assertEquals(novoNumero, viewModel.numero.value)
    }
}
