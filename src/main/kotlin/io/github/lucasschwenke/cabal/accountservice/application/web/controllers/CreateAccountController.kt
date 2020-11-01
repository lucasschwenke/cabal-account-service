package io.github.lucasschwenke.cabal.accountservice.application.web.controllers

import io.github.lucasschwenke.cabal.accountservice.application.extensions.toAccount
import io.github.lucasschwenke.cabal.accountservice.application.web.requests.CreateAccountRequest
import io.github.lucasschwenke.cabal.accountservice.application.web.responses.CreateAccountResponse
import io.github.lucasschwenke.cabal.accountservice.application.web.validators.Validator
import io.github.lucasschwenke.cabal.accountservice.domain.exceptions.InvalidRequestException
import io.github.lucasschwenke.cabal.accountservice.domain.services.CreateAccountService
import io.github.lucasschwenke.cabal.accountservice.domain.logs.LogTags
import io.github.lucasschwenke.logging.LoggableClass

class CreateAccountController(
    private val createAccountService: CreateAccountService,
    private val validator: Validator<CreateAccountRequest>
) {

    fun createAccount(createAccountRequest: CreateAccountRequest): CreateAccountResponse {
        logger.debug(LogTags.CONTROLLER, LogTags.CREATE_ACCOUNT) {
            "Request received to create a new account for username ${createAccountRequest.username}"
        }

        validateRequest(createAccountRequest)

        val createdAccount = createAccountService.createAccount(createAccountRequest.toAccount())

        return CreateAccountResponse(createdAccount).also {
            logger.debug(LogTags.CONTROLLER, LogTags.CREATE_ACCOUNT) {
                "Account created for username ${createAccountRequest.username}"
            }
        }
    }

    private fun validateRequest(createAccountRequest: CreateAccountRequest) {
        val errors = validator.validate(createAccountRequest)

        if (errors.isNotEmpty()) {
            val message = "There are some invalids fields in request."
            logger.error(LogTags.CONTROLLER, LogTags.CREATE_ACCOUNT) { message }

            throw InvalidRequestException(
                message = message,
                details = errors
            )
        }
    }

    companion object : LoggableClass()
}
