package io.github.lucasschwenke.cabal.accountservice.application.modules

import io.github.lucasschwenke.cabal.accountservice.application.config.DatabaseConfig
import io.github.lucasschwenke.cabal.accountservice.application.config.EnvironmentVariablesConfig
import io.github.lucasschwenke.cabal.accountservice.domain.repositories.AuthRepository
import io.github.lucasschwenke.cabal.accountservice.domain.repositories.CabalVoteRepository
import io.github.lucasschwenke.cabal.accountservice.domain.repositories.ChargeAuthRepository
import io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth.SqlAuthRepository
import io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth.SqlCabalVoteRepository
import io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth.SqlChargeAuthRepository
import org.koin.dsl.module

val databaseModules = module {
    single {
        DatabaseConfig.connect(
            databaseJdbcUrl = get<EnvironmentVariablesConfig>().databaseJdbcUrl,
            databaseUsername = get<EnvironmentVariablesConfig>().databaseUsername,
            databasePassword = get<EnvironmentVariablesConfig>().databasePassword,
            databaseDriverClassName = get<EnvironmentVariablesConfig>().databaseDriver,
            databaseConnectionTimeout = get<EnvironmentVariablesConfig>().databaseTimeout,
            databaseValidationTimeout = get<EnvironmentVariablesConfig>().databaseValidationTimeout,
            databaseMaximumPoolSize = get<EnvironmentVariablesConfig>().databaseMaximumPoolSize
        )
    }

    single<AuthRepository> {
        SqlAuthRepository()
    }

    single<CabalVoteRepository> {
        SqlCabalVoteRepository()
    }

    single<ChargeAuthRepository> {
        SqlChargeAuthRepository()
    }
}