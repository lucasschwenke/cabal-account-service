package io.github.lucasschwenke.cabal.accountservice.application.web.requests

data class CreateAccountRequest(
    val username: String,
    val password: String,
    val email: String,
    val key: String
)
