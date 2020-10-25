package io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth

import io.github.lucasschwenke.cabal.accountservice.domain.auth.Authentication
import io.github.lucasschwenke.cabal.accountservice.domain.repositories.AuthRepository
import io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth.entity.AuthEntity
import org.jdbi.v3.core.Handle
import org.jdbi.v3.core.locator.ClasspathSqlLocator

class SqlAuthRepository : AuthRepository {

    override fun insertAuth(authentication: Authentication, handle: Handle): Authentication {
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
}