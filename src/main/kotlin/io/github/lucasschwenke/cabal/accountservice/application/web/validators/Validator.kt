package io.github.lucasschwenke.cabal.accountservice.application.web.validators

interface Validator<T> {

    fun validate(request: T): Map<String, List<String>>
}
