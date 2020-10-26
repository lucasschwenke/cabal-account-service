package io.github.lucasschwenke.cabal.accountservice.domain.services

import io.github.lucasschwenke.cabal.accountservice.domain.account.Account
import io.github.lucasschwenke.cabal.accountservice.domain.auth.Authentication
import io.github.lucasschwenke.cabal.accountservice.domain.extensions.toMD5
import io.github.lucasschwenke.cabal.accountservice.domain.repositories.AuthRepository
import org.jdbi.v3.core.Handle
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AuthenticationService(
    private val authRepository: AuthRepository
) {

    fun createAuthentication(account: Account, handle: Handle): Authentication {
        val authentication = Authentication(
            username = account.username,
            password = account.password,
            email = account.email,
            hash = generateHash(),
            key = account.key,
            login = LOGIN_INITIAL_VALUE,
            authType = AUTH_TYPE_INITIAL_VALUE,
            perg = PERG_INITIAL_VALUE,
            identityNo = IDENTITY_NO_INITIAL_VALUE
        )

        return authRepository.insertAuth(authentication, handle)
    }

    private fun generateHash() = StringBuilder("[")
        .append(LocalDateTime.now().format(formatter).toString())
        .append("]")
        .toString()
        .toMD5()

    companion object {
        private const val LOGIN_INITIAL_VALUE = 0
        private const val AUTH_TYPE_INITIAL_VALUE = 1
        private const val PERG_INITIAL_VALUE = 3
        private const val IDENTITY_NO_INITIAL_VALUE = "7700000000000"
        private val formatter = DateTimeFormatter.ofPattern("d-m-Y")
    }
}