package org.rushton.poker.model.ranks

import org.rushton.poker.model.Card
import org.rushton.poker.model.Face
import org.rushton.poker.model.Hand
import org.rushton.poker.model.Rank

class TwoPair extends Hand<TwoPair> {

    final Rank rank = Rank.TwoPair

    int compareTo(Hand otherHand) {
        if (this.rank == otherHand.rank) {
            TwoPair other = otherHand as TwoPair
            def thisRankList = this.getPairRanks()*.ordinal() + this.getKickerRanks()*.ordinal()
            def otherRankList = other.getPairRanks()*.ordinal() + other.getKickerRanks()*.ordinal()
            utils.compareIntegerLists(thisRankList.collect { it }, otherRankList.collect { it })
        } else {
            this.rank.ordinal() <=> otherHand.rank.ordinal()
        }
    }

    String toString() {
        "Pair of ${getPairRanks()*.faceString?.join("'s and ")}'s with Kickers: ${getKickerRanks()*.faceString?.join("; ")}"
    }

    private Collection<Face> getPairRanks() {
        getRankMap()?.findAll { it.value.size() == 2 }?.keySet()?.toList()?.take(2)?.sort { it.ordinal() }?.reverse()
    }

    private Collection<Face> getKickerRanks() {
        getRankMap()?.findAll { it.value.size() == 1 }?.keySet()?.toList()?.sort { it.ordinal() }?.reverse()
    }

    private Map<Face, List<Card>> getRankMap() {
        getCards()?.groupBy { it.face }
    }

}
