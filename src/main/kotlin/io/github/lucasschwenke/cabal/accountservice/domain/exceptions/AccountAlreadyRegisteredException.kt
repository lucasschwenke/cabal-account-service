package io.github.lucasschwenke.cabal.accountservice.domain.exceptions

class AccountAlreadyRegisteredException(message: String) : ApiException(message = message) {

    override fun httpStatus(): Int = HttpErrorStatus.BAD_REQUEST.statusCode

    override fun apiError(): ApiError = ApiError.ACCOUNT_ALREADY_REGISTERED

    override fun userResponseMessage(): String = "$message"

    override fun details(): Map<String, List<Any>> = emptyMap()
}
