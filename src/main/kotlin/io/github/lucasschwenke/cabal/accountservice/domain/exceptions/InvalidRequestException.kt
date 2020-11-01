package io.github.lucasschwenke.cabal.accountservice.domain.exceptions

class InvalidRequestException(
    message: String,
    private val details: Map<String, List<Any>>
) : ApiException(message = message) {

    override fun httpStatus(): Int = HttpErrorStatus.BAD_REQUEST.statusCode

    override fun apiError(): ApiError = ApiError.INVALID_REQUEST

    override fun userResponseMessage(): String = "$message"

    override fun details() = details
}
