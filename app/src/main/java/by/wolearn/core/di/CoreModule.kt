package by.wolearn.core.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.room.Room
import by.wolearn.core.model.WolearnDatabase
import org.koin.dsl.module


val coreModule = module {
    single { get<Context>().getSharedPreferences("prefs", MODE_PRIVATE) }
    single { Room.databaseBuilder(get(), WolearnDatabase::class.java, "wolearn").build() }
    single { get<WolearnDatabase>().categoryDao() }
}