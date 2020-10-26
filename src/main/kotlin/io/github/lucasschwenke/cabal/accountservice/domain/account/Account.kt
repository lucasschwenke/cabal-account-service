package io.github.lucasschwenke.cabal.accountservice.domain.account

data class Account(
    val username: String,
    val password: String,
    val email: String,
    val key: String
)
