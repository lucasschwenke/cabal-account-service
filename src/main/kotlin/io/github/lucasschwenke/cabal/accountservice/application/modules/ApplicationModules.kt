package io.github.lucasschwenke.cabal.accountservice.application.modules

import io.github.lucasschwenke.cabal.accountservice.application.config.EnvironmentVariablesConfig
import org.koin.dsl.module

val applicationModules = module {
    single { EnvironmentVariablesConfig() }
}
