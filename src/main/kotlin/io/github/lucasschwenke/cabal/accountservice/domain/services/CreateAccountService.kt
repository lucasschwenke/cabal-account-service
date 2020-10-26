package io.github.lucasschwenke.cabal.accountservice.domain.services

import io.github.lucasschwenke.cabal.accountservice.domain.account.Account
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked

class CreateAccountService(
    private val authenticationService: AuthenticationService,
    private val cabalVoteService: CabalVoteService,
    private val cabalChargeAuthService: ChargeAuthService,
    private val jdbi: Jdbi
) {

    fun createAccount(account: Account) {

        jdbi.withHandleUnchecked {
            it.begin()

            val createdAuthentication = authenticationService.createAuthentication(account, it)
            val userNum = createdAuthentication.userNum!!

            cabalVoteService.createCabalVote(userNum, createdAuthentication.username, it)
            cabalChargeAuthService.createChargeAuth(userNum, it)

            it.commit()
        }
    }
}