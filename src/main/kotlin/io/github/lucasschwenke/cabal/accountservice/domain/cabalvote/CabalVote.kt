package io.github.lucasschwenke.cabal.accountservice.domain.cabalvote

data class CabalVote(
    val userNum: Int? = null,
    val id: String,
    val topGames: Int,
    val topSites: Int,
    val qtdVote: Int
)
