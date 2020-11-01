package io.github.lucasschwenke.cabal.accountservice.application.extensions

import io.github.lucasschwenke.cabal.accountservice.application.web.requests.CreateAccountRequest
import io.github.lucasschwenke.cabal.accountservice.domain.account.Account

fun CreateAccountRequest.toAccount() = Account(
    username = this.username,
    password = this.password,
    email = this.email,
    key = this.key
)
