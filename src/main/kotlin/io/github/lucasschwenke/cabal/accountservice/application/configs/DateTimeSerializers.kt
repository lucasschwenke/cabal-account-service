package io.github.lucasschwenke.cabal.accountservice.application.configs

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import io.github.lucasschwenke.cabal.accountservice.application.extensions.toIsoLocalDate
import io.github.lucasschwenke.cabal.accountservice.application.extensions.toIsoLocalDateTime
import io.github.lucasschwenke.cabal.accountservice.application.extensions.toLocalDate
import io.github.lucasschwenke.cabal.accountservice.application.extensions.toLocalDateTime
import java.time.LocalDate
import java.time.LocalDateTime

class LocalDateTimeSerializer : StdSerializer<LocalDateTime>(LocalDateTime::class.java) {

    override fun serialize(value: LocalDateTime, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeString(value.toIsoLocalDateTime())
    }
}

class LocalDateTimeDeserializer : StdDeserializer<LocalDateTime>(LocalDateTime::class.java) {

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): LocalDateTime =
        p.readValueAs(String::class.java).toLocalDateTime()
}

class LocalDateSerializer : StdSerializer<LocalDate>(LocalDate::class.java) {

    override fun serialize(value: LocalDate, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeString(value.toIsoLocalDate())
    }
}

class LocalDateDeserializer : StdDeserializer<LocalDate>(LocalDate::class.java) {

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): LocalDate =
        p.readValueAs(String::class.java).toLocalDate()
}