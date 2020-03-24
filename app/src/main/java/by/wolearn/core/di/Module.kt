package by.wolearn.core.di

import android.speech.tts.TextToSpeech
import by.wolearn.auth.AuthInterceptor
import by.wolearn.core.backend.OnAuthFailedListenerImpl
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import java.util.*


val coreModule = module {

    single { OnAuthFailedListenerImpl(get()) }

    single {
        listOf(
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY },
            AuthInterceptor(get<OnAuthFailedListenerImpl>())
        )
    }

    single {
        TextToSpeech(get()) {}.also { it.language = Locale.UK }
    }
}