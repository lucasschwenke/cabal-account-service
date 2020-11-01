package io.github.lucasschwenke.cabal.accountservice.domain.services

import io.github.lucasschwenke.cabal.accountservice.domain.account.Account
import io.github.lucasschwenke.cabal.accountservice.domain.tags.LogTags
import io.github.lucasschwenke.logging.LoggableClass
import org.jdbi.v3.core.Handle
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked

class CreateAccountService(
    private val authenticationService: AuthenticationService,
    private val cabalVoteService: CabalVoteService,
    private val cabalChargeAuthService: ChargeAuthService,
    private val jdbi: Jdbi
) {

    fun createAccount(account: Account): Account {
        checkAccountAlreadyRegistered(account.username)

        jdbi.withHandleUnchecked {
            it.begin().also {
                logger.debug(LogTags.SERVICE, LogTags.SERVICE) {
                    "Starting the creation for a new account for the username ${account.username}"
                }
            }

            val createdAuthentication = createAuthentication(account, it)
            val userNum = createdAuthentication.userNum!!
            val username = createdAuthentication.username

            createCabalVote(userNum, username, it)
            createChargeAuth(userNum, username, it)

            it.commit()
        }

        return account
    }

    private fun checkAccountAlreadyRegistered(username: String) {
        logger.debug(LogTags.SERVICE, LogTags.SERVICE) {
            "Checking if the username $username is already registered...."
        }

        authenticationService.findUsername(username)?.run {
            throw RuntimeException("")
        }
    }

    private fun createAuthentication(account: Account, handle: Handle) =
        authenticationService.createAuthentication(account, handle).also {
            logger.debug(LogTags.SERVICE, LogTags.SERVICE) {
                "Authentication created for the username ${account.username}"
            }
        }

    private fun createCabalVote(userNum: Int, username: String, handle: Handle) =
        cabalVoteService.createCabalVote(userNum, username, handle).also {
            logger.debug(LogTags.SERVICE, LogTags.SERVICE) {
                "Cabal vote created for the username $username"
            }
        }

    private fun createChargeAuth(userNum: Int, username: String, handle: Handle) =
        cabalChargeAuthService.createChargeAuth(userNum, handle).also {
            logger.debug(LogTags.SERVICE, LogTags.SERVICE) {
                "Charge auth created for the username $username"
            }
        }

    companion object : LoggableClass()
}
