package io.github.lucasschwenke.cabal.accountservice.application.extensions

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.toIsoLocalDateTime(): String = this.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
fun LocalDate.toIsoLocalDate(): String = this.format(DateTimeFormatter.ISO_LOCAL_DATE)
