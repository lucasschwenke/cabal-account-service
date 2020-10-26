package io.github.lucasschwenke.cabal.accountservice.application.modules

import io.github.lucasschwenke.cabal.accountservice.application.config.EnvironmentVariablesConfig
import io.github.lucasschwenke.cabal.accountservice.domain.services.AuthenticationService
import io.github.lucasschwenke.cabal.accountservice.domain.services.CabalVoteService
import io.github.lucasschwenke.cabal.accountservice.domain.services.ChargeAuthService
import io.github.lucasschwenke.cabal.accountservice.domain.services.CreateAccountService
import org.koin.dsl.module

val serviceModules = module {
    single {
        AuthenticationService(
            authRepository = get()
        )
    }

    single {
        CabalVoteService(
            cabalVoteRepository = get()
        )
    }

    single {
        ChargeAuthService(
            expireAuthDays = get<EnvironmentVariablesConfig>().expireAuthDays,
            chargeAuthRepository = get()
        )
    }

    single {
        CreateAccountService(
            authenticationService = get(),
            cabalVoteService = get(),
            cabalChargeAuthService = get(),
            jdbi = get()
        )
    }
}
