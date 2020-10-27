package io.github.lucasschwenke.cabal.accountservice.application.modules

import org.koin.core.context.startKoin

fun loadModules() {
    startKoin {
        modules(
            applicationModules,
            databaseModules,
            serviceModules,
            controllerModules
        )
    }
}
