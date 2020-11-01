package io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth.entity.extensions

import io.github.lucasschwenke.cabal.accountservice.domain.account.Authentication
import io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth.entity.AuthEntity

fun AuthEntity.toAuthentication() =
    Authentication(
        userNum = this.userNum,
        username = this.id,
        password = this.password,
        login = this.login,
        loginTime = this.loginTime,
        logoutTime = this.logoutTime,
        authType = this.authType,
        playTime = this.playTime,
        identityNo = this.identityNo,
        loginEx = this.loginEx,
        lastIp = this.lastIp,
        authKey = this.authKey,
        nationCode = this.nationCode,
        createDate = this.createDate,
        email = this.email,
        ip = this.ip,
        question = this.question,
        answer = this.answer,
        loginCounter = this.loginCounter,
        key = this.key,
        qtHackerSkill = this.qtHackerSkill,
        name = this.name,
        lastName = this.lastName,
        city = this.city,
        uf = this.uf,
        survey = this.survey,
        reset = this.reset,
        hash = this.hash,
        banTime = this.banTime,
        perg = this.perg
    )
