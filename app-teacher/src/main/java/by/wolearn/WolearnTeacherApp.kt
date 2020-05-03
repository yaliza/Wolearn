package by.wolearn

import android.app.Application
import by.wolearn.addword.di.addWordsModule
import by.wolearn.auth.AuthPreferences
import by.wolearn.chooser.di.categoryChooserModule
import by.wolearn.core.di.coreModule
import by.wolearn.login.di.loginModule
import by.wolearn.mywords.di.myWordsModule
import by.wolearn.settings.di.settingsModule
import by.wolearn.words.di.wordsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class WolearnTeacherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WolearnTeacherApp)
            modules(
                listOf(
                    coreModule,
                    loginModule,
                    wordsModule,
                    myWordsModule,
                    settingsModule,
                    addWordsModule,
                    categoryChooserModule
                )
            )
        }
        AuthPreferences.init(this)
    }
}