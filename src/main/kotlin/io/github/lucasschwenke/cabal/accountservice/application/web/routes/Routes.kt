package io.github.lucasschwenke.cabal.accountservice.application.web.routes

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.lucasschwenke.cabal.accountservice.application.web.constants.APPLICATION_JSON_CHARSET_UTF_8
import io.github.lucasschwenke.cabal.accountservice.application.web.constants.CONTENT_TYPE
import io.github.lucasschwenke.cabal.accountservice.application.web.controllers.CreateAccountController
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import org.koin.core.KoinComponent
import org.koin.core.inject

object Routes : KoinComponent {

    private val objectMapper: ObjectMapper by inject()
    private val createAccountController: CreateAccountController by inject()

    fun getRoutes(vertx: Vertx): Router {
        val router = Router.router(vertx)
        router.route().handler(BodyHandler.create())

        router.post("/account").handler {
            val response = createAccountController.createAccount(objectMapper.readValue(it.bodyAsString))
            it.response()
                .setStatusCode(HttpResponseStatus.CREATED.code())
                .putHeader(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8)
                .end(objectMapper.writeValueAsString(response))
        }

        return router
    }
}
