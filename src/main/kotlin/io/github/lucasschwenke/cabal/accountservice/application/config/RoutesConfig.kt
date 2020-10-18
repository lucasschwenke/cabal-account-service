package io.github.lucasschwenke.cabal.accountservice.application.config

import io.vertx.core.Vertx
import io.vertx.ext.web.Router

fun getRoutes(vertx: Vertx): Router {
    val router = Router.router(vertx)

    router.get("/").handler {
        it.response().end("Hello world!")
    }

    return router
}
