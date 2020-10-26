package io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth

import io.github.lucasschwenke.cabal.accountservice.domain.account.CabalChargeAuth
import io.github.lucasschwenke.cabal.accountservice.domain.repositories.CabalChargeAuthRepository
import io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth.entity.CabalChargeAuthEntity
import org.jdbi.v3.core.Handle
import org.jdbi.v3.core.locator.ClasspathSqlLocator

class SqlCabalChargeAuthRepository : CabalChargeAuthRepository {

    override fun insertCabalChargeAuth(cabalChargeAuth: CabalChargeAuth, handle: Handle): CabalChargeAuth {
        val chargeAuthEntity = CabalChargeAuthEntity(
            userNum = cabalChargeAuth.userNum,
            type = cabalChargeAuth.type,
            expireDate = cabalChargeAuth.expireDate,
            payMinutes = cabalChargeAuth.payMinutes
        )

        val query = ClasspathSqlLocator.create().locate("sql.charge_auth.insert_charge_auth")

        handle.createUpdate(query)
            .bind("userNum", chargeAuthEntity.userNum)
            .bind("type", chargeAuthEntity.type)
            .bind("expireDate", chargeAuthEntity.expireDate)
            .bind("payMinutes", chargeAuthEntity.payMinutes)
            .execute()

        return cabalChargeAuth
    }
}
