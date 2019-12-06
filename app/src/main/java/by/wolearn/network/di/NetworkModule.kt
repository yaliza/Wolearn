package by.wolearn.network.di

import by.wolearn.network.BASE_URL
import by.wolearn.network.WolearnApi
import by.wolearn.network.WolearnInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        OkHttpClient
            .Builder()
            .addInterceptor(WolearnInterceptor(get()))
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(WolearnApi::class.java)
    }

}

