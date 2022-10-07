package com.thiagoyf.mytodo

import android.app.Application
import com.thiagoyf.dependencies.DependenciesDI
import com.thiagoyf.mytodo.di.ApplicationDI
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyTodoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        koinInit()
    }

    private fun koinInit() {
        startKoin {
            androidContext(this@MyTodoApplication)
            modules(DependenciesDI.modules + ApplicationDI.module)
        }
    }
}