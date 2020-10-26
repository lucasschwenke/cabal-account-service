package io.github.lucasschwenke.cabal.accountservice.domain.services

import io.github.lucasschwenke.cabal.accountservice.domain.account.CabalChargeAuth
import io.github.lucasschwenke.cabal.accountservice.domain.repositories.CabalChargeAuthRepository
import org.jdbi.v3.core.Handle
import java.time.LocalDateTime.now

class ChargeAuthService(
    private val expireAuthDays: Long,
    private val chargeAuthRepository: CabalChargeAuthRepository
) {

    fun createChargeAuth(userNum: Int, handle: Handle): CabalChargeAuth {
        val cabalChargeAuth = CabalChargeAuth(
            userNum = userNum,
            type = 0,
            expireDate = now().plusDays(expireAuthDays),
            payMinutes = 0
        )

        return chargeAuthRepository.insertCabalChargeAuth(cabalChargeAuth, handle)
    }
}