package io.github.lucasschwenke.cabal.accountservice.application

import io.github.lucasschwenke.cabal.accountservice.application.config.EnvironmentVariablesConfig
import io.github.lucasschwenke.cabal.accountservice.application.config.getRoutes
import io.github.lucasschwenke.cabal.accountservice.application.modules.applicationModules
import io.vertx.core.Vertx
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

object Application : KoinComponent {

    private val environmentVariablesConfig: EnvironmentVariablesConfig by inject()

    @JvmStatic
    fun start() {
        loadModules()
        loadServer()
    }

    private fun loadModules() {
        startKoin {
            modules(applicationModules)
        }
    }

    private fun loadServer() {
        val vertx = Vertx.vertx()
        val server = vertx.createHttpServer()
        val port = environmentVariablesConfig.serverPort
        val router = getRoutes(vertx)

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
