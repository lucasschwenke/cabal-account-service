package io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth.entity

data class CabalVoteEntity(
    val userNum: Int,
    val id: String,
    val xtreme: Int,
    val topGames: Int,
    val gamesSites: Int,
    val topG: Int,
    val qtdVote: Int
)
