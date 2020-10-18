package io.github.lucasschwenke.cabal.accountservice.application

import io.vertx.core.Vertx
import io.vertx.ext.web.Router

object Application {

    @JvmStatic fun start() {
        val vertx = Vertx.vertx()
        val server = vertx.createHttpServer()
        val port = 9000
        val router = Router.router(vertx)

        router.get("/").handler {
            it.response().end("Hello world!")
        }

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
