package io.github.lucasschwenke.cabal.accountservice.application.config

import com.natpryce.konfig.Configuration
import com.natpryce.konfig.EnvironmentVariables
import com.natpryce.konfig.getValue
import com.natpryce.konfig.intType
import com.natpryce.konfig.longType
import com.natpryce.konfig.stringType

class EnvironmentVariablesConfig(
    configuration: Configuration = EnvironmentVariables()
) {

    val serverPort = configuration[SERVER_PORT]

    val databaseJdbcUrl = configuration[DATABASE_JDBC_URL]
    val databaseUsername = configuration[DATABASE_USERNAME]
    val databasePassword = configuration[DATABASE_PASSWORD]
    val databaseDriver = configuration[DATABASE_DRIVER]
    val databaseTimeout = configuration[DATABASE_TIMEOUT]
    val databaseValidationTimeout = configuration[DATABASE_VALIDATION_TIMEOUT]
    val databaseMaximumPoolSize = configuration[DATABASE_MAXIMUM_POOL_SIZE]

    val expireAuthDays = configuration[EXPIRE_AUTH_DAYS]

    companion object {
        private val SERVER_PORT by intType

        private val DATABASE_JDBC_URL by stringType
        private val DATABASE_USERNAME by stringType
        private val DATABASE_PASSWORD by stringType
        private val DATABASE_DRIVER by stringType
        private val DATABASE_TIMEOUT by longType
        private val DATABASE_VALIDATION_TIMEOUT by longType
        private val DATABASE_MAXIMUM_POOL_SIZE by intType
        private val EXPIRE_AUTH_DAYS by longType
    }
}