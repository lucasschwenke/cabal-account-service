package io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth

import io.github.lucasschwenke.cabal.accountservice.domain.account.Authentication
import io.github.lucasschwenke.cabal.accountservice.domain.repositories.AuthRepository
import io.github.lucasschwenke.cabal.accountservice.domain.logs.LogTags
import io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth.entity.AuthEntity
import io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth.entity.extensions.toAuthentication
import io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth.entity.mapper.AuthEntityMapper
import io.github.lucasschwenke.logging.LoggableClass
import org.jdbi.v3.core.Handle
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import org.jdbi.v3.core.locator.ClasspathSqlLocator

class SqlCabalAuthRepository(
    private val jdbi: Jdbi
) : AuthRepository {

    override fun insertAuth(authentication: Authentication, handle: Handle): Authentication {
        logger.debug(LogTags.REPOSITORY) {
            "creating new auth in insert_auth table for the username ${authentication.username}"
        }

        val authEntity = AuthEntity(
            id = authentication.username,
            password = authentication.password,
            login = authentication.login,
            email = authentication.email,
            hash = authentication.hash,
            key = authentication.key,
            authType = authentication.authType,
            perg = authentication.perg,
            identityNo = authentication.identityNo
        )

        val query = ClasspathSqlLocator.create().locate("sql.auth.insert_auth")
        val userNum = handle.createUpdate(query)
            .bind("id", authEntity.id)
            .bind("password", authEntity.password)
            .bind("email", authEntity.email)
            .bind("hash", authEntity.hash)
            .bind("key", authEntity.key)
            .bind("login", authEntity.login)
            .bind("authType", authEntity.authType)
            .bind("perg", authEntity.perg)
            .bind("identityNo", authEntity.identityNo)
            .executeAndReturnGeneratedKeys()
            .mapTo(Int::class.java)
            .one()

        return authentication.copy(userNum = userNum)
    }

    override fun findByUsername(username: String): Authentication? {
        logger.debug(LogTags.REPOSITORY) {
            "Finding username $username in cabal_auth table..."
        }

        val query = ClasspathSqlLocator.create().locate("sql.auth.select_auth_by_username")

        return jdbi.withHandleUnchecked {
            it.createQuery(query)
                .bind("username", username)
                .map(AuthEntityMapper())
                .firstOrNull()?.toAuthentication().also {
                    logger.info(LogTags.REPOSITORY) {
                        "Username $username was found in cabal_auth table!"
                    }
                }
        }
    }

    override fun findByEmail(email: String): Authentication? {
        logger.debug(LogTags.REPOSITORY) {
            "Finding email $email in cabal_auth table..."
        }

        val query = ClasspathSqlLocator.create().locate("sql.auth.select_auth_by_email")

        return jdbi.withHandleUnchecked {
            it.createQuery(query)
                .bind("email", email)
                .map(AuthEntityMapper())
                .firstOrNull()?.toAuthentication().also {
                    logger.info(LogTags.REPOSITORY) {
                        "email $email was found in cabal_auth table!"
                    }
                }
        }
    }

    companion object : LoggableClass()
}
