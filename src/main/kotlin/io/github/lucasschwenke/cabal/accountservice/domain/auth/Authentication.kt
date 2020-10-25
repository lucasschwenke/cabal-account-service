package io.github.lucasschwenke.cabal.accountservice.domain.auth

data class Authentication(
    val userNum: Int? = null,
    val username: String,
    val password: String,
    val login: Int,
    val authType: Int,
    val email: String,
    val hash: String? = null,
    val key: String? = null,
    val perg: Int? = null,
    val identityNo: String? = null
)
