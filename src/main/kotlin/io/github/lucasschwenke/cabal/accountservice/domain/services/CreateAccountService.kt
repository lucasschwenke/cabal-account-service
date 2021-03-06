package io.github.lucasschwenke.cabal.accountservice.domain.services

import io.github.lucasschwenke.cabal.accountservice.domain.account.Account
import io.github.lucasschwenke.cabal.accountservice.domain.exceptions.AccountAlreadyRegisteredException
import io.github.lucasschwenke.cabal.accountservice.domain.logs.LogTags
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
        checkAccountAlreadyRegistered(account.username, account.email)

        jdbi.withHandleUnchecked {
            it.begin().also {
                logger.debug(LogTags.SERVICE, LogTags.CREATE_ACCOUNT) {
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

    private fun checkAccountAlreadyRegistered(username: String, email: String) {
        logger.debug(LogTags.SERVICE, LogTags.CREATE_ACCOUNT) {
            "Checking if the username $username is already registered...."
        }

        authenticationService.findUsername(username)?.run {
            throw AccountAlreadyRegisteredException("The username $username is already registered.")
        }

        logger.debug(LogTags.SERVICE, LogTags.CREATE_ACCOUNT) {
            "Checking if the email $email is already registered...."
        }

        authenticationService.findEmail(email)?.run {
            throw AccountAlreadyRegisteredException("The email $email is already registered.")
        }
    }

    private fun createAuthentication(account: Account, handle: Handle) =
        authenticationService.createAuthentication(account, handle).also {
            logger.debug(LogTags.SERVICE, LogTags.CREATE_ACCOUNT) {
                "Authentication created for the username ${account.username}"
            }
        }

    private fun createCabalVote(userNum: Int, username: String, handle: Handle) =
        cabalVoteService.createCabalVote(userNum, username, handle).also {
            logger.debug(LogTags.SERVICE, LogTags.CREATE_ACCOUNT) {
                "Cabal vote created for the username $username"
            }
        }

    private fun createChargeAuth(userNum: Int, username: String, handle: Handle) =
        cabalChargeAuthService.createChargeAuth(userNum, username, handle).also {
            logger.debug(LogTags.SERVICE, LogTags.SERVICE) {
                "Charge auth created for the username $username"
            }
        }

    companion object : LoggableClass()
}
