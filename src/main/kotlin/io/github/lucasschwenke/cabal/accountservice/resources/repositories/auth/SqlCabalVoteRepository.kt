package io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth

import io.github.lucasschwenke.cabal.accountservice.domain.cabalvote.CabalVote
import io.github.lucasschwenke.cabal.accountservice.domain.repositories.CabalVoteRepository
import io.github.lucasschwenke.cabal.accountservice.resources.repositories.auth.entity.CabalVoteEntity
import org.jdbi.v3.core.Handle
import org.jdbi.v3.core.locator.ClasspathSqlLocator

class SqlCabalVoteRepository : CabalVoteRepository {

    override fun insertCabalVote(cabalVote: CabalVote, handle: Handle): CabalVote {
        val cabalVoteEntity = CabalVoteEntity(
            userNum = cabalVote.userNum,
            id = cabalVote.username,
            xtreme = cabalVote.xtreme,
            topGames = cabalVote.topGames,
            gamesSites = cabalVote.gamesSites,
            topG = cabalVote.topG,
            qtdVote = cabalVote.qtdVote
        )

        val query = ClasspathSqlLocator.create().locate("sql.cabal_vote.insert_cabal_vote")
        handle.createUpdate(query)
            .bind("userNum", cabalVoteEntity.userNum)
            .bind("id", cabalVoteEntity.id)
            .bind("xtreme", cabalVoteEntity.xtreme)
            .bind("topGames", cabalVoteEntity.topGames)
            .bind("gamesSites", cabalVoteEntity.gamesSites)
            .bind("topG", cabalVoteEntity.topG)
            .bind("qtoVote", cabalVoteEntity.qtdVote)
            .execute()

        return cabalVote
    }
}