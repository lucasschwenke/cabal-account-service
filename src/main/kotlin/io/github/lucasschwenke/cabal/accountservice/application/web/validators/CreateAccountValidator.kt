package io.github.lucasschwenke.cabal.accountservice.application.web.validators

import io.github.lucasschwenke.cabal.accountservice.application.web.extensions.isInvalidEmail
import io.github.lucasschwenke.cabal.accountservice.application.web.extensions.isInvalidLength
import io.github.lucasschwenke.cabal.accountservice.application.web.extensions.isNullOrBlank
import io.github.lucasschwenke.cabal.accountservice.application.web.requests.CreateAccountRequest
import io.github.lucasschwenke.cabal.accountservice.domain.tags.LogTags
import io.github.lucasschwenke.logging.LoggableClass

class CreateAccountValidator : Validator<CreateAccountRequest> {

    override fun validate(request: CreateAccountRequest): Map<String, List<String>> {
        logger.debug(LogTags.VALIDATION, LogTags.CREATE_ACCOUNT) {
            "Validating create account request"
        }

        val errorList = mutableListOf<Validation<*>>()

        with(request) {
            errorList.add(Validation("username", this.username).isNullOrBlank())
            errorList.add(
                Validation("username", this.username).isInvalidLength(min = MIN_LENGTH, max = MAX_LENGTH)
            )
            errorList.add(Validation("password", this.password).isNullOrBlank())
            errorList.add(
                Validation("password", this.password).isInvalidLength(min = MIN_LENGTH, max = MAX_LENGTH)
            )
            errorList.add(Validation("email", this.email).isNullOrBlank())
            errorList.add(Validation("email", this.email).isInvalidEmail())
            errorList.add(Validation("key", this.key).isNullOrBlank())
            errorList.add(
                Validation("key", this.key).isInvalidLength(min = MIN_LENGTH, max = MAX_LENGTH)
            )
        }

        return errorList.filter { it.errorMessageList.isNotEmpty() }
            .groupBy { it.fieldName }
            .mapValues { it.value.flatMap { valueItem -> valueItem.errorMessageList } }
    }

    companion object : LoggableClass() {
        private const val MIN_LENGTH = 5
        private const val MAX_LENGTH = 15
    }
}
