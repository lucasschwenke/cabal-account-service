package io.github.lucasschwenke.cabal.accountservice.application.configs

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.lucasschwenke.cabal.accountservice.application.web.controllers.CreateAccountController
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import org.koin.core.KoinComponent
import org.koin.core.inject

object RoutesConfig : KoinComponent {

    private val objectMapper: ObjectMapper by inject()
    private val createAccountController: CreateAccountController by inject()

    fun getRoutes(vertx: Vertx): Router {
        val router = Router.router(vertx)
        router.route().handler(BodyHandler.create())

        router.post("/account").handler {
            val response = createAccountController.createAccount(objectMapper.readValue(it.bodyAsString))
            it.response()
                .setStatusCode(201)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(objectMapper.writeValueAsString(response))
        }



        return router
    }
}
