package ru.cocovella.prof_android_dev.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.cocovella.prof_android_dev.di.application
import ru.cocovella.prof_android_dev.di.historyScreen
import ru.cocovella.prof_android_dev.di.mainScreen

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))

        }
    }
}
