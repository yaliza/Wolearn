package by.wolearn

import android.app.Application
import by.wolearn.learning.di.learningModule
import by.wolearn.login.di.loginModule
import by.wolearn.login.di.registrationModule
import by.wolearn.profile.di.profileModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class WolearnApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WolearnApp)
            modules(listOf(registrationModule, loginModule, learningModule, profileModule))
        }
    }
}