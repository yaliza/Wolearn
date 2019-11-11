package by.wolearn.core.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import org.koin.dsl.module


val coreModule = module {

    single { get<Context>().getSharedPreferences("prefs", MODE_PRIVATE) }

}