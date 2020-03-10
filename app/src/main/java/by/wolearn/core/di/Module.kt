package by.wolearn.core.di

import android.speech.tts.TextToSpeech
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import by.wolearn.auth.AuthPreferences
import by.wolearn.auth.OnAuthFailedListenerImpl
import by.wolearn.core.backend.AuthInterceptor
import by.wolearn.core.model.TokenStorage
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import java.util.*


val coreModule = module {

    single { MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC) }
    single {
        EncryptedSharedPreferences.create(
            "wolearn",
            get(),
            get(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }


    single {
        OnAuthFailedListenerImpl(get())
    }

    single {
        listOf(
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY },
            AuthInterceptor(get<OnAuthFailedListenerImpl>())
        )
    }

    single {
        TextToSpeech(get()) {}.also { it.language = Locale.UK }
    }

    single { TokenStorage(get()) }
}