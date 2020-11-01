package io.github.lucasschwenke.cabal.accountservice.application.modules

import io.github.lucasschwenke.cabal.accountservice.application.web.controllers.CreateAccountController
import org.koin.dsl.module

val controllerModules = module {

    single {
        CreateAccountController(
            createAccountService = get(),
            validator = get()
        )
    }
}
