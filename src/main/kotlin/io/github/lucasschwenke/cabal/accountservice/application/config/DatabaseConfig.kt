package io.github.lucasschwenke.cabal.accountservice.application.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jdbi.v3.core.Jdbi

object DatabaseConfig {

    fun connect(
        databaseJdbcUrl: String,
        databaseUsername: String,
        databasePassword: String,
        databaseDriverClassName: String,
        databaseConnectionTimeout: Long,
        databaseValidationTimeout: Long,
        databaseMaximumPoolSize: Int
    ): Jdbi {

        val config = HikariConfig().apply {
            this.jdbcUrl = databaseJdbcUrl
            this.username = databaseUsername
            this.password = databasePassword
            this.driverClassName = databaseDriverClassName
            this.connectionTimeout = databaseConnectionTimeout
            this.validationTimeout = databaseValidationTimeout
            this.maximumPoolSize = databaseMaximumPoolSize
        }

        return Jdbi.create(HikariDataSource(config)).also {
            print("connected!")
        }

    }
}
