package io.github.lucasschwenke.cabal.accountservice.application

import io.github.lucasschwenke.cabal.accountservice.application.configs.EnvironmentVariablesConfig
import io.github.lucasschwenke.cabal.accountservice.application.configs.RoutesConfig
import io.github.lucasschwenke.cabal.accountservice.application.modules.loadModules
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.concurrent.TimeUnit

object Application : KoinComponent {

    private val environmentVariablesConfig: EnvironmentVariablesConfig by inject()
    private val routesConfig: RoutesConfig by inject()

    @JvmStatic
    fun start() {
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
                println("account-service is up on port $port \\o/")
            } else {
                println(it.cause())
            }
        }
    }
}

fun main() {
    Application.start()
}
