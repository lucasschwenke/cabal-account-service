package io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth.entity.mapper

import io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth.entity.AuthEntity
import io.github.lucasschwenke.cabal.accountservice.resources.repositories.extensions.toLocalDateTime
import io.github.lucasschwenke.cabal.accountservice.resources.repositories.extensions.toLocalDateWithUtc
import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.statement.StatementContext
import java.sql.ResultSet

class AuthEntityMapper : RowMapper<AuthEntity> {

    override fun map(rs: ResultSet, ctx: StatementContext): AuthEntity =
        AuthEntity(
            userNum = rs.getInt("userNum"),
            id = rs.getString("id"),
            password = rs.getString("password"),
            login = rs.getInt("login"),
            loginTime = rs.getDate("loginTime")?.toLocalDateTime(),
            logoutTime = rs.getDate("logoutTime")?.toLocalDateTime(),
            authType = rs.getInt("authType"),
            playTime = rs.getInt("playTime"),
            identityNo = rs.getString("identityNo"),
            loginEx = rs.getInt("loginEx"),
            lastIp = rs.getString("lastIp"),
            authKey = rs.getString("authKey"),
            nationCode = rs.getInt("nationCode"),
            createDate = rs.getDate("createDate")?.toLocalDateWithUtc(),
            email = rs.getString("email"),
            ip = rs.getString("ip"),
            question = rs.getString("question"),
            answer = rs.getString("answer"),
            loginCounter = rs.getInt("loginCounter"),
            key = rs.getString("authKey"),
            qtHackerSkill = rs.getInt("qtHackerSkill"),
            name = rs.getString("name"),
            lastName = rs.getString("lastName"),
            city = rs.getString("city"),
            uf = rs.getString("uf"),
            survey = rs.getString("survey"),
            reset = rs.getInt("reset"),
            hash = rs.getString("hash"),
            banTime = rs.getString("banTime"),
            perg = rs.getInt("perg")
        )
}
