package org.rushton.poker.model.ranks

import org.rushton.poker.model.Card
import org.rushton.poker.model.Face
import org.rushton.poker.model.Hand
import org.rushton.poker.model.Rank

class FourOfAKind extends Hand<FourOfAKind> {

    final Rank rank = Rank.FourOfAKind

    int compareTo(Hand otherHand) {
        if (this.rank == otherHand.rank) {
            FourOfAKind other = otherHand as FourOfAKind
            def thisRankList = [this.getQuadRank().ordinal()] + this.getKickerRanks()*.ordinal().collect { it }
            def otherRankList = [other.getQuadRank().ordinal()] + other.getKickerRanks()*.ordinal().collect { it }
            utils.compareIntegerLists(thisRankList, otherRankList)
        } else {
            this.rank.ordinal() <=> otherHand.rank.ordinal()
        }
    }

    String toString() {
        "Four ${getQuadRank().faceString}'s with Kicker: ${getKickerRanks()*.faceString?.join("; ")}"
    }

    Face getQuadRank() {
        getRankMap()?.findAll { it.value.size() == 4 }?.keySet()?.toList()?.first()
    }

    Collection<Face> getKickerRanks() {
        getRankMap()?.findAll { it.value.size() == 1 }?.keySet()?.toList()?.sort { it.ordinal() }?.reverse()
    }

    private Map<Face, List<Card>> getRankMap() {
        getCards()?.groupBy { it.face }
    }


}
