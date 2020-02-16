package by.wolearn

import android.app.Application
import by.wolearn.categories.di.categoriesModule
import by.wolearn.core.di.coreModule
import by.wolearn.learning.di.learningModule
import by.wolearn.settings.di.settingsModule
import by.wolearn.login.di.loginModule
import by.wolearn.login.di.registrationModule
import by.wolearn.network.di.networkModule
import by.wolearn.profile.di.profileModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class WolearnApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WolearnApp)
            modules(
                listOf(
                    coreModule,
                    networkModule,
                    registrationModule,
                    loginModule,
                    learningModule,
                    profileModule,
                    settingsModule,
                    categoriesModule
                )
            )
        }
    }
}