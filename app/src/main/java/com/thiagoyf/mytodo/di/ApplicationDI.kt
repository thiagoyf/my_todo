package com.thiagoyf.mytodo.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module

object ApplicationDI {
    val module = module {
        single(named("applicationScope")) {
            CoroutineScope(SupervisorJob() + Dispatchers.Default)
        }
    }
}