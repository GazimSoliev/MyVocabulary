package com.gazim.myvocabluary

import android.app.Application
import com.gazim.myvocabluary.di.repositoryModule
import com.gazim.myvocabluary.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@Application)
            modules(repositoryModule, viewModelModule)
        }
    }
}
