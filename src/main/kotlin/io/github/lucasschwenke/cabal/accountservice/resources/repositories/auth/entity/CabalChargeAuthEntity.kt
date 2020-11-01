package io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth.entity

import java.time.LocalDateTime

data class CabalChargeAuthEntity(
    val userNum: Int,
    val type: Int,
    val expireDate: LocalDateTime,
    val payMinutes: Int,
    val serviceKind: Int? = null
)
