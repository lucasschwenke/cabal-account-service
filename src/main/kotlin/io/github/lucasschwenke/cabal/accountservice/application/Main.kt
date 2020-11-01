package io.github.lucasschwenke.cabal.accountservice.application

import io.github.lucasschwenke.logging.LoggableClass

object Main : LoggableClass() {

    @JvmStatic
    fun main(args: Array<String>) {
        Application.start()
    }
}
