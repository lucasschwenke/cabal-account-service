package io.github.lucasschwenke.cabal.accountservice.domain.services

import io.github.lucasschwenke.cabal.accountservice.domain.account.CabalVote
import io.github.lucasschwenke.cabal.accountservice.domain.repositories.CabalVoteRepository
import io.github.lucasschwenke.cabal.accountservice.domain.tags.LogTags
import io.github.lucasschwenke.logging.LoggableClass
import org.jdbi.v3.core.Handle

class CabalVoteService(
    private val cabalVoteRepository: CabalVoteRepository
) {

    fun createCabalVote(
        userNum: Int,
        username: String,
        handle: Handle
    ): CabalVote {
        logger.debug(LogTags.SERVICE, LogTags.CREATE_ACCOUNT) {
            "Configuring new cabal vote for username $username"
        }

        val cabalVote = CabalVote(
            userNum = userNum,
            username = username,
            xtreme = XTREME_INITIAL_VALUE,
            topGames = TOP_GAMES_INITIAL_VALUE,
            gamesSites = GAMES_SITES_INITIAL_VALUE,
            topG = TOP_G_INITIAL_VALUE,
            qtdVote = QTD_VOTE_INITIAL_VALUE
        )

        return cabalVoteRepository.insertCabalVote(cabalVote, handle)
    }

    companion object : LoggableClass() {
        private const val TOP_GAMES_INITIAL_VALUE = 0
        private const val XTREME_INITIAL_VALUE = 0
        private const val GAMES_SITES_INITIAL_VALUE = 0
        private const val TOP_G_INITIAL_VALUE = 0
        private const val QTD_VOTE_INITIAL_VALUE = 0
    }
}
