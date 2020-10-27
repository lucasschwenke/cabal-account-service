package io.github.lucasschwenke.cabal.accountservice.application.web.controllers

import io.github.lucasschwenke.cabal.accountservice.application.extensions.toAccount
import io.github.lucasschwenke.cabal.accountservice.application.web.requests.CreateAccountRequest
import io.github.lucasschwenke.cabal.accountservice.application.web.responses.CreateAccountResponse
import io.github.lucasschwenke.cabal.accountservice.domain.services.CreateAccountService

class CreateAccountController(
    private val createAccountService: CreateAccountService
) {

    fun createAccount(createAccountRequest: CreateAccountRequest): CreateAccountResponse {
        val createdAccount = createAccountService.createAccount(createAccountRequest.toAccount())

        return CreateAccountResponse(createdAccount)
    }
}