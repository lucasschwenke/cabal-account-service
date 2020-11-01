package io.github.lucasschwenke.cabal.accountservice.application.web.extensions

import io.github.lucasschwenke.cabal.accountservice.application.web.validators.Validation
import java.util.regex.Pattern.compile

fun Validation<String>.isNullOrBlank(): Validation<String> {
    this.fieldValue.takeIf { it.isBlank() }?.run {
        errorMessageList.add("must not be empty or null.")
    }

    return this
}

fun Validation<String>.isInvalidEmail(): Validation<String> {
    this.fieldValue.takeIf { !emailRegex.matcher(it).matches() }?.run {
        errorMessageList.add("must be a valid email.")
    }

    return this
}

fun Validation<String>.isInvalidLength(min: Int, max: Int): Validation<String> {
    this.fieldValue.takeIf { it.length < min || it.length > max }?.run {
        errorMessageList.add("must be a min length $min and max length $max")
    }

    return this
}

private val emailRegex = compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
)
