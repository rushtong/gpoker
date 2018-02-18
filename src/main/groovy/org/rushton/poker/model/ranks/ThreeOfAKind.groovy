package org.rushton.poker.model.ranks

import org.rushton.poker.model.Card
import org.rushton.poker.model.Face
import org.rushton.poker.model.Hand
import org.rushton.poker.model.Rank

class ThreeOfAKind extends Hand<ThreeOfAKind> {

    final Rank rank = Rank.ThreeOfAKind

    int compareTo(Hand otherHand) {
        if (this.rank == otherHand.rank) {
            ThreeOfAKind other = otherHand as ThreeOfAKind
            def thisRankList = [this.getTripleRank().ordinal()] + this.getKickerRanks()*.ordinal().collect { it }
            def otherRankList = [other.getTripleRank().ordinal()] + other.getKickerRanks()*.ordinal().collect { it }
            utils.compareIntegerLists(thisRankList, otherRankList)
        } else {
            this.rank.ordinal() <=> otherHand.rank.ordinal()
        }
    }

    String toString() {
        "Three ${getTripleRank().faceString}'s with Kickers: ${getKickerRanks()*.faceString?.join("; ")}"
    }

    Face getTripleRank() {
        getRankMap()?.findAll { it.value.size() == 3 }?.keySet()?.toList()?.first()
    }

    Collection<Face> getKickerRanks() {
        getRankMap()?.findAll { it.value.size() == 1 }?.keySet()?.toList()?.sort { it.ordinal() }?.reverse()
    }

    private Map<Face, List<Card>> getRankMap() {
        getCards()?.groupBy { it.face }
    }


}
