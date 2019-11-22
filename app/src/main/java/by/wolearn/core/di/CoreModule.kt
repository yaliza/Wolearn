package by.wolearn.core.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import by.wolearn.network.WolearnApi
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


val coreModule = module {
    single { get<Context>().getSharedPreferences("prefs", MODE_PRIVATE) }
    single {
        Retrofit.Builder()
            .baseUrl("https://wolearn-api.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create(WolearnApi::class.java)
    }
}