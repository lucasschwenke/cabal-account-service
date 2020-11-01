package io.github.lucasschwenke.cabal.accountservice.application.web.controllers

import io.github.lucasschwenke.cabal.accountservice.application.extensions.toAccount
import io.github.lucasschwenke.cabal.accountservice.application.web.requests.CreateAccountRequest
import io.github.lucasschwenke.cabal.accountservice.application.web.responses.CreateAccountResponse
import io.github.lucasschwenke.cabal.accountservice.domain.services.CreateAccountService
import io.github.lucasschwenke.cabal.accountservice.domain.tags.LogTags
import io.github.lucasschwenke.logging.LoggableClass

class CreateAccountController(
    private val createAccountService: CreateAccountService
) {

    fun createAccount(createAccountRequest: CreateAccountRequest): CreateAccountResponse {
        logger.debug(LogTags.CONTROLLER, LogTags.CREATE_ACCOUNT) {
            "Request received to create a new account for username ${createAccountRequest.username}"
        }

        val createdAccount = createAccountService.createAccount(createAccountRequest.toAccount())

        return CreateAccountResponse(createdAccount).also {
            logger.debug(LogTags.CONTROLLER, LogTags.CREATE_ACCOUNT) {
                "Account created for username ${createAccountRequest.username}"
            }
        }
    }

    companion object : LoggableClass()
}
