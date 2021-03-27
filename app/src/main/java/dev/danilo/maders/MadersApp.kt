package dev.danilo.maders

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MadersApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MadersApp)
            modules(DIModules.inject())
        }
    }
}