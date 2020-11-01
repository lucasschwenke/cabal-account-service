package io.github.lucasschwenke.cabal.accountservice.domain.services

import io.github.lucasschwenke.cabal.accountservice.domain.account.Account
import io.github.lucasschwenke.cabal.accountservice.domain.account.Authentication
import io.github.lucasschwenke.cabal.accountservice.domain.extensions.toMD5
import io.github.lucasschwenke.cabal.accountservice.domain.repositories.AuthRepository
import io.github.lucasschwenke.cabal.accountservice.domain.logs.LogTags
import io.github.lucasschwenke.logging.LoggableClass
import org.jdbi.v3.core.Handle
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AuthenticationService(
    private val authRepository: AuthRepository
) {

    fun createAuthentication(account: Account, handle: Handle): Authentication {
        val username = account.username

        logger.debug(LogTags.SERVICE, LogTags.CREATE_ACCOUNT) {
            "Generating authentication for the username $username"
        }

        val authentication = Authentication(
            username = username,
            password = account.password,
            email = account.email,
            hash = generateHash(username),
            key = account.key,
            login = LOGIN_INITIAL_VALUE,
            authType = AUTH_TYPE_INITIAL_VALUE,
            perg = PERG_INITIAL_VALUE,
            identityNo = IDENTITY_NO_INITIAL_VALUE
        )

        return authRepository.insertAuth(authentication, handle)
    }

    fun findUsername(username: String): Authentication? {
        logger.debug(LogTags.SERVICE) {
            "Finding username $username..."
        }

        return authRepository.findByUsername(username)
    }

    fun findEmail(email: String): Authentication? {
        logger.debug(LogTags.SERVICE) {
            "Finding email $email..."
        }

        return authRepository.findByEmail(email)
    }

    private fun generateHash(username: String): String {
        logger.debug(LogTags.SERVICE, LogTags.CREATE_ACCOUNT) {
            "Generating hash for the username $username"
        }

        return StringBuilder("[")
            .append(LocalDateTime.now().format(formatter).toString())
            .append("]")
            .toString()
            .toMD5()
    }

    companion object : LoggableClass() {
        private const val LOGIN_INITIAL_VALUE = 0
        private const val AUTH_TYPE_INITIAL_VALUE = 1
        private const val PERG_INITIAL_VALUE = 3
        private const val IDENTITY_NO_INITIAL_VALUE = "7700000000000"
        private val formatter = DateTimeFormatter.ofPattern("d-m-Y")
    }
}
