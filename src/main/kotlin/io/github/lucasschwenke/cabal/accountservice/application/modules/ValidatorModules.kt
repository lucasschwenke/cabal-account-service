package io.github.lucasschwenke.cabal.accountservice.application.modules

import io.github.lucasschwenke.cabal.accountservice.application.web.requests.CreateAccountRequest
import io.github.lucasschwenke.cabal.accountservice.application.web.validators.CreateAccountValidator
import io.github.lucasschwenke.cabal.accountservice.application.web.validators.Validator
import org.koin.dsl.module

val validatorModules = module {
    single<Validator<CreateAccountRequest>> {
        CreateAccountValidator()
    }
}
