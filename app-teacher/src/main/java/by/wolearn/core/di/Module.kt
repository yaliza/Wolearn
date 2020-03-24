package by.wolearn.core.di

import by.wolearn.auth.AuthInterceptor
import by.wolearn.core.backend.OnAuthFailedListenerImpl
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module


val coreModule = module {

    single { OnAuthFailedListenerImpl(get()) }

    single {
        listOf(
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY },
            AuthInterceptor(get<OnAuthFailedListenerImpl>())
        )
    }
}