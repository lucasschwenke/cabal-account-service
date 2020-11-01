package io.github.lucasschwenke.cabal.accountservice.application.modules

import io.github.lucasschwenke.cabal.accountservice.application.configs.EnvironmentVariablesConfig
import io.github.lucasschwenke.cabal.accountservice.application.configs.FailureHandlerConfig
import io.github.lucasschwenke.cabal.accountservice.application.configs.RoutesConfig
import io.github.lucasschwenke.cabal.accountservice.application.configs.configJsonMapper
import org.koin.dsl.module

val applicationModules = module {
    single { EnvironmentVariablesConfig() }
    single { configJsonMapper() }
    single { RoutesConfig }
    single { FailureHandlerConfig }
}
