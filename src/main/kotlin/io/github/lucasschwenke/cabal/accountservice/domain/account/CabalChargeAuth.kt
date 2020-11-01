package io.github.lucasschwenke.cabal.accountservice.domain.account

import java.time.LocalDateTime

data class CabalChargeAuth(
    val userNum: Int,
    val type: Int,
    val expireDate: LocalDateTime,
    val payMinutes: Int,
    val serviceKind: Int? = null
)
