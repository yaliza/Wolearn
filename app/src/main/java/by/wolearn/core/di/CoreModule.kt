package by.wolearn.core.di

import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import by.wolearn.core.model.TokenStorage
import by.wolearn.core.model.WolearnDatabase
import org.koin.dsl.module


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
        TokenStorage(get())
    }


    single { Room.databaseBuilder(get(), WolearnDatabase::class.java, "wolearn").build() }
    single { get<WolearnDatabase>().categoryDao() }
}