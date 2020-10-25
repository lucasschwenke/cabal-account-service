package io.github.lucasschwenke.cabal.accountservice.domain.repositories

import io.github.lucasschwenke.cabal.accountservice.domain.auth.Authentication
import org.jdbi.v3.core.Handle

interface AuthRepository {

    fun insertAuth(authentication: Authentication, handle: Handle): Authentication
}