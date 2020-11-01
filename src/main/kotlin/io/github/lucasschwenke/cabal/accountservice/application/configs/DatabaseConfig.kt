package io.github.lucasschwenke.cabal.accountservice.application.configs

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.lucasschwenke.cabal.accountservice.domain.tags.LogTags
import io.github.lucasschwenke.logging.LoggableClass
import org.jdbi.v3.core.Jdbi

object DatabaseConfig : LoggableClass() {

    fun connect(
        databaseJdbcUrl: String,
        databaseUsername: String,
        databasePassword: String,
        databaseDriverClassName: String,
        databaseConnectionTimeout: Long,
        databaseValidationTimeout: Long,
        databaseMaximumPoolSize: Int
    ): Jdbi {
        logger.debug(LogTags.CONFIGURATION, LogTags.DATABASE) {
            "connecting in account database with the follow jdbcUrl: $databaseJdbcUrl"
        }

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
            logger.info(LogTags.CONFIGURATION, LogTags.DATABASE) {
                "database connected and configured!!!"
            }
        }
    }
}
