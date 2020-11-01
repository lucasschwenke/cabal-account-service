package io.github.lucasschwenke.cabal.accountservice.domain.services

import io.github.lucasschwenke.cabal.accountservice.domain.account.CabalChargeAuth
import io.github.lucasschwenke.cabal.accountservice.domain.repositories.CabalChargeAuthRepository
import io.github.lucasschwenke.cabal.accountservice.domain.tags.LogTags
import io.github.lucasschwenke.logging.LoggableClass
import org.jdbi.v3.core.Handle
import java.time.LocalDateTime.now

class ChargeAuthService(
    private val expireAuthDays: Long,
    private val chargeAuthRepository: CabalChargeAuthRepository
) {

    fun createChargeAuth(userNum: Int, username: String, handle: Handle): CabalChargeAuth {
        logger.debug(LogTags.SERVICE, LogTags.CREATE_ACCOUNT) {
            "Configuring new charge auth for username $username"
        }

        val cabalChargeAuth = CabalChargeAuth(
            userNum = userNum,
            type = INITIAL_TYPE,
            expireDate = now().plusDays(expireAuthDays),
            payMinutes = INITIAL_PAY_MINUTE
        )

        return chargeAuthRepository.insertCabalChargeAuth(cabalChargeAuth, handle)
    }

    companion object: LoggableClass() {
        private const val INITIAL_TYPE = 0
        private const val INITIAL_PAY_MINUTE = 0
    }
}
