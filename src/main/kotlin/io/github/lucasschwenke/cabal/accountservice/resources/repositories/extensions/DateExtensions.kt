package io.github.lucasschwenke.cabal.accountservice.resources.repositories.extensions

import java.sql.Date
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

fun Date.toLocalDateTime(): LocalDateTime = this.toInstant()
    .atZone(ZoneId.of("UTC-3"))
    .toLocalDateTime()

fun Date.toLocalDateWithUtc(): LocalDate = this.toInstant()
    .atZone(ZoneId.of("UTC-3"))
    .toLocalDate()
