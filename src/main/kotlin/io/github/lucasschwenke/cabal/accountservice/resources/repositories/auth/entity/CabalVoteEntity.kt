package io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth.entity

data class CabalVoteEntity(
    val userNum: Int,
    val id: String,
    val topGames: Int,
    val topSites: Int,
    val qtdVote: Int
)
