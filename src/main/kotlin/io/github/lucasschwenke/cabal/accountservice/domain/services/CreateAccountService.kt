package io.github.lucasschwenke.cabal.accountservice.domain.services

import io.github.lucasschwenke.cabal.accountservice.domain.auth.Account
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked

class CreateAccountService(
    private val authenticationService: AuthenticationService,
    private val jdbi: Jdbi
) {

    fun createAccount(account: Account) {

        jdbi.withHandleUnchecked {
            authenticationService.createAuthentication(account, it)
        }
    }
}