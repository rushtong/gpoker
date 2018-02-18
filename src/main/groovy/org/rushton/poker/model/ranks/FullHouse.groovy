package org.rushton.poker.model.ranks

import org.rushton.poker.model.Card
import org.rushton.poker.model.Face
import org.rushton.poker.model.Hand
import org.rushton.poker.model.Rank

class FullHouse extends Hand<FullHouse> {

    final Rank rank = Rank.FullHouse

    int compareTo(Hand otherHand) {
        if (this.rank == otherHand.rank) {
            FullHouse other = otherHand as FullHouse
            def thisRankList = [this.getTripleRank().ordinal()] + this.getPairRank().ordinal().collect { it }
            def otherRankList = [other.getTripleRank().ordinal()] + other.getPairRank().ordinal().collect { it }
            utils.compareIntegerLists(thisRankList, otherRankList)
        } else {
            this.rank.ordinal() <=> otherHand.rank.ordinal()
        }
    }

    String toString() {
        "Full House, ${getTripleRank().faceString}'s over ${getPairRank().faceString}'s"
    }

    Face getTripleRank() {
        getRankMap()?.findAll { it.value.size() == 3 }?.keySet()?.toList()?.first()
    }

    Face getPairRank() {
        getRankMap()?.findAll { it.value.size() == 2 }?.keySet()?.toList()?.first()
    }

    private Map<Face, List<Card>> getRankMap() {
        getCards()?.groupBy { it.face }
    }


}
