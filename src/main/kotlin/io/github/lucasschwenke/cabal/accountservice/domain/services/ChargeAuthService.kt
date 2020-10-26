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
            type = INITIAL_TYPE,
            expireDate = now().plusDays(expireAuthDays),
            payMinutes = INITIAL_PAY_MINUTE
        )

        return chargeAuthRepository.insertCabalChargeAuth(cabalChargeAuth, handle)
    }

    companion object {
        private const val INITIAL_TYPE = 0
        private const val INITIAL_PAY_MINUTE = 0
    }
}