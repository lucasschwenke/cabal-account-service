package io.github.lucasschwenke.cabal.accountservice.application.web.validators

data class Validation<T>(
    val fieldName: String,
    val fieldValue: T,
    val errorMessageList: MutableList<String> = mutableListOf()
)
