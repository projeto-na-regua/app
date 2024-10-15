package com.example.na_regua_app.data.di

import com.example.na_regua_app.data.api.Rest
import com.example.na_regua_app.data.api.UsuarioService
import com.example.na_regua_app.data.repository.UsuarioRepository
import com.example.na_regua_app.data.repository.UsuarioRepositoryImpl
import com.example.na_regua_app.data.repository.UsuarioRepositoryLocalImpl
import com.example.na_regua_app.viewmodel.PerfilUsuarioViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule= module {

    single<UsuarioService> {
        Rest.usuarioService
    }

    single<UsuarioRepository> {
        // Descomentar para produção vvv:
         UsuarioRepositoryImpl(get())
        // Descomentar para teste mock vvv:
        // UsuarioRepositoryLocalImpl()
    }

    viewModel<PerfilUsuarioViewModel> {
        PerfilUsuarioViewModel(get())
    }
}