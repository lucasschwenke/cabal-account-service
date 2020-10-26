package io.github.lucasschwenke.cabal.accountservice.domain.repositories

import io.github.lucasschwenke.cabal.accountservice.domain.account.CabalChargeAuth
import org.jdbi.v3.core.Handle

interface CabalChargeAuthRepository {

    fun insertCabalChargeAuth(cabalChargeAuth: CabalChargeAuth, handle: Handle): CabalChargeAuth
}
