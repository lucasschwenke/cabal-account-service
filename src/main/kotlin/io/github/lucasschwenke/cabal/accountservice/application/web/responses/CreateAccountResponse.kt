package io.github.lucasschwenke.cabal.accountservice.application.web.responses

import io.github.lucasschwenke.cabal.accountservice.domain.account.Account

data class CreateAccountResponse(
    val username: String,
    val password: String,
    val email: String,
    val key: String
) {

    constructor(account: Account) : this(
        username = account.username,
        password = account.password,
        email = account.email,
        key = account.key
    )
}
