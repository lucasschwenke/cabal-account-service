package io.github.lucasschwenke.cabal.accountservice.domain.cabalvote

data class CabalVote(
    val userNum: Int,
    val username: String,
    val xtreme: Int,
    val topGames: Int,
    val gamesSites: Int,
    val topG: Int,
    val qtdVote: Int
)
