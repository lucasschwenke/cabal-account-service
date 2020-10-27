package io.github.lucasschwenke.cabal.accountservice.application.configs

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.time.LocalDate
import java.time.LocalDateTime

fun configJsonMapper(): ObjectMapper =
    jacksonObjectMapper().apply {
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        this.registerKotlinModule()
        this.registerModule(createDateModule())
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        this.propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
    }

private fun createDateModule(): SimpleModule = with(SimpleModule()) {
    addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer())
    addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer())
    addSerializer(LocalDate::class.java, LocalDateSerializer())
    addDeserializer(LocalDate::class.java, LocalDateDeserializer())
}
