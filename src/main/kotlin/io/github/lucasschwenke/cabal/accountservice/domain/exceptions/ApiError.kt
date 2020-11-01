package io.github.lucasschwenke.cabal.accountservice.domain.exceptions

enum class ApiError {
    INTERNAL_SERVER_ERROR,
    ACCOUNT_ALREADY_REGISTERED,
    INVALID_REQUEST
}
