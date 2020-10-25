package io.github.lucasschwenke.cabal.accountservice.domain.auth

data class Account(
    val username: String,
    val password: String,
    val email: String,
    val hash: String,
    val key: String
)
