package io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth.entity

import java.time.LocalDate
import java.time.LocalDateTime

data class AuthEntity(
    val userNum: Int? = null,
    val id: String,
    val password: String,
    val login: Int,
    val loginTime: LocalDateTime? = null,
    val logoutTime: LocalDateTime? = null,
    val authType: Int,
    val playTime: Int? = null,
    val identityNo: String? = null,
    val loginEx: Int? = null,
    val lastIp: String? = null,
    val authKey: String? = null,
    val nationCode: Int? = null,
    val createDate: LocalDate? = null,
    val email: String? = null,
    val ip: String? = null,
    val question: String? = null,
    val answer: String? = null,
    val loginCounter: Int? = null,
    val key: String? = null,
    val qtHackerSkill: Int? = null,
    val name: String? = null,
    val lastName: String? = null,
    val city: String? = null,
    val uf: String? = null,
    val survey: String? = null,
    val reset: Int? = null,
    val hash: String? = null,
    val banTime: String? = null,
    val perg: Int? = null
)
