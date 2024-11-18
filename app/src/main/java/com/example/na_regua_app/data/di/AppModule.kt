package com.example.na_regua_app.data.di

import com.example.na_regua_app.viewmodel.GaleriaViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.na_regua_app.data.api.AgendamentoService
import com.example.na_regua_app.data.api.BarbeariaService
import com.example.na_regua_app.data.api.FinancasService
import com.example.na_regua_app.data.api.FuncionarioService
import com.example.na_regua_app.data.api.GaleriaService
import com.example.na_regua_app.data.api.PesquisaService
import com.example.na_regua_app.data.api.Rest
import com.example.na_regua_app.data.api.ServicoService
import com.example.na_regua_app.data.api.UsuarioService
import com.example.na_regua_app.data.repository.AgendamentoRepository
import com.example.na_regua_app.data.repository.AgendamentoRepositoryImpl
import com.example.na_regua_app.data.repository.BarbeariaRepository
import com.example.na_regua_app.data.repository.BarbeariaRepositoryImpl
import com.example.na_regua_app.data.repository.FinancaRepository
import com.example.na_regua_app.data.repository.FinancaRepositoryImpl
import com.example.na_regua_app.data.repository.FuncionarioRepository
import com.example.na_regua_app.data.repository.FuncionarioRepositoryImpl
import com.example.na_regua_app.data.repository.GaleriaRepository
import com.example.na_regua_app.data.repository.GaleriaRepositoryImpl
import com.example.na_regua_app.data.repository.PesquisaRepository
import com.example.na_regua_app.data.repository.PesquisaRepositoryImpl
import com.example.na_regua_app.data.repository.ServicoRepository
import com.example.na_regua_app.data.repository.ServicoRepositoryImpl
import com.example.na_regua_app.data.repository.UsuarioRepository
import com.example.na_regua_app.data.repository.UsuarioRepositoryImpl
import com.example.na_regua_app.viewmodel.AgendamentoViewModel
import com.example.na_regua_app.viewmodel.CadastroBarbeariaViewModel
import com.example.na_regua_app.viewmodel.CadastroViewModel
import com.example.na_regua_app.viewmodel.FinancaViewModel
import com.example.na_regua_app.viewmodel.FuncionarioViewModel
import com.example.na_regua_app.viewmodel.LoginViewModel
import com.example.na_regua_app.viewmodel.PerfilBarbeariaViewModel
import com.example.na_regua_app.viewmodel.PerfilUsuarioViewModel
import com.example.na_regua_app.viewmodel.PesquisaViewModel
import com.example.na_regua_app.viewmodel.ServicoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val appModule = module {

    // Services (com Retrofit já configurado)
    single<UsuarioService> {
        Rest.getInstance(androidContext()).create(UsuarioService::class.java)
    }
    single<BarbeariaService> {
        Rest.getInstance(androidContext()).create(BarbeariaService::class.java)
    }
    single<ServicoService> {
        Rest.getInstance(androidContext()).create(ServicoService::class.java)
    }
    single<FuncionarioService> {
        Rest.getInstance(androidContext()).create(FuncionarioService::class.java)
    }
    single<PesquisaService> {
        Rest.getInstance(androidContext()).create(PesquisaService::class.java)
    }
    single<AgendamentoService> {
        Rest.getInstance(androidContext()).create(AgendamentoService::class.java)
    }
    single<FinancasService> {
        Rest.getInstance(androidContext()).create(FinancasService::class.java)
    }
    single<GaleriaService>{
        Rest.getInstance(androidContext()).create(GaleriaService::class.java)
    }

    // Repositories
    single<UsuarioRepository> { UsuarioRepositoryImpl(get()) }
    single<BarbeariaRepository> { BarbeariaRepositoryImpl(get()) }
    single<ServicoRepository> { ServicoRepositoryImpl(get()) }
    single<FuncionarioRepository> { FuncionarioRepositoryImpl(get()) }
    single<PesquisaRepository> { PesquisaRepositoryImpl(get()) }
    single<AgendamentoRepository> { AgendamentoRepositoryImpl(get()) }
    single<FinancaRepository> { FinancaRepositoryImpl(get()) }
    single<GaleriaRepository> { GaleriaRepositoryImpl(get()) }

    // ViewModels
    viewModel<AgendamentoViewModel> { AgendamentoViewModel(get()) }
    viewModel<PesquisaViewModel> { PesquisaViewModel(get()) }
    viewModel<LoginViewModel> { LoginViewModel(get()) }
    viewModel<CadastroBarbeariaViewModel> { CadastroBarbeariaViewModel(get()) }
    viewModel<CadastroViewModel> { CadastroViewModel(get()) }
    viewModel<PerfilUsuarioViewModel> { PerfilUsuarioViewModel(get()) }
    viewModel<PerfilBarbeariaViewModel> { PerfilBarbeariaViewModel(get()) }
    viewModel<ServicoViewModel> { ServicoViewModel(get()) }
    viewModel<FuncionarioViewModel> { FuncionarioViewModel(get()) }
    viewModel<FinancaViewModel> { FinancaViewModel(get()) }
    viewModel<GaleriaViewModel> { GaleriaViewModel(get()) }
}
