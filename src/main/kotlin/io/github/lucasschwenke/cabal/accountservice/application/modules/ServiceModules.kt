package io.github.lucasschwenke.cabal.accountservice.application.modules

import io.github.lucasschwenke.cabal.accountservice.domain.services.AuthenticationService
import io.github.lucasschwenke.cabal.accountservice.domain.services.CreateAccountService
import org.koin.dsl.module

val serviceModules = module {
    single {
        AuthenticationService(
            authRepository = get()
        )
    }

    single {
        CreateAccountService(
            authenticationService = get(),
            jdbi = get()
        )
    }
}
