package io.github.lucasschwenke.cabal.accountservice.application

import io.github.lucasschwenke.cabal.accountservice.application.configs.EnvironmentVariablesConfig
import io.github.lucasschwenke.cabal.accountservice.application.configs.RoutesConfig
import io.github.lucasschwenke.cabal.accountservice.application.modules.loadModules
import io.github.lucasschwenke.cabal.accountservice.domain.tags.LogTags
import io.github.lucasschwenke.logging.LoggableClass
import io.github.lucasschwenke.logging.LoggerContext
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.concurrent.TimeUnit

object Application : KoinComponent, LoggableClass() {

    private val environmentVariablesConfig: EnvironmentVariablesConfig by inject()
    private val routesConfig: RoutesConfig by inject()

    fun start() {
        LoggerContext.initContext("app-startup")
        loadModules()
        loadServer()
    }

    private fun loadServer() {
        val options = VertxOptions()
        options.apply {
            blockedThreadCheckInterval = 5
            blockedThreadCheckIntervalUnit = TimeUnit.SECONDS

            maxEventLoopExecuteTime = 100
            maxEventLoopExecuteTimeUnit = TimeUnit.MILLISECONDS

            maxWorkerExecuteTime = 10
            maxWorkerExecuteTimeUnit = TimeUnit.SECONDS

            warningExceptionTime = 20
            warningExceptionTimeUnit = TimeUnit.SECONDS
        }

        val vertx = Vertx.vertx(options)

        val server = vertx.createHttpServer()
        val port = environmentVariablesConfig.serverPort
        val router = routesConfig.getRoutes(vertx)

        server.requestHandler(router).listen(port) {
            if (it.succeeded()) {
                logger.debug(LogTags.CONFIGURATION, LogTags.APPLICATION) {
                    "account-service is up on port $port \\o/"
                }
            } else {
                logger.error(it.cause(), LogTags.CONFIGURATION, LogTags.APPLICATION) {
                    "an error occurred when tried to startup the service."
                }
            }
        }
    }
}
