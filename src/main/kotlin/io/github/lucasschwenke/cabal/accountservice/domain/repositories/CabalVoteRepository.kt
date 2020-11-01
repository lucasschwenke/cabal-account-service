package io.github.lucasschwenke.cabal.accountservice.domain.repositories

import io.github.lucasschwenke.cabal.accountservice.domain.account.CabalVote
import org.jdbi.v3.core.Handle

interface CabalVoteRepository {

    fun insertCabalVote(cabalVote: CabalVote, handle: Handle): CabalVote
}
