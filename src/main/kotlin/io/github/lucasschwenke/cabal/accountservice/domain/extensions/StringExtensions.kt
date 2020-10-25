package io.github.lucasschwenke.cabal.accountservice.domain.extensions

import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter

fun String.toSHA1() = hashString("SHA-1", this)
fun String.toMD5() = hashString("MD5", this)

private fun hashString(type: String, input: String): String {
    val bytes = MessageDigest
        .getInstance(type)
        .digest(input.toByteArray())

    return DatatypeConverter.printHexBinary(bytes).toUpperCase()
}
