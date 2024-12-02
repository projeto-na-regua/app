import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.na_regua_app.data.repository.UsuarioRepository
import com.example.na_regua_app.viewmodel.LoginViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: LoginViewModel
    private val usuarioRepository: UsuarioRepository = mock()

    @Before
    fun setup() {
        // Define o dispatcher de teste
        Dispatchers.setMain(testDispatcher)
        viewModel = LoginViewModel(usuarioRepository)
    }

    @Test
    fun `atualizarEmail deve alterar o valor do email`() {
        // Definir o novo email
        val novoEmail = "teste@example.com"

        // Chamar a função
        viewModel.atualizarEmail(novoEmail)

        // Verificar se o email foi alterado
        assertEquals(novoEmail, viewModel.email.value)
    }

    @Test
    fun `atualizarSenha deve alterar o valor da senha`() {
        // Definir a nova senha
        val novaSenha = "senha123"

        // Chamar a função
        viewModel.atualizarSenha(novaSenha)

        // Verificar se a senha foi alterada
        assertEquals(novaSenha, viewModel.senha.value)
    }

    // Testes adicionais para a função `logar` podem ser realizados aqui, dependendo do comportamento desejado
}
