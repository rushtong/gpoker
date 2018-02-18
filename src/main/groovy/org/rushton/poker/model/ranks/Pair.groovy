package org.rushton.poker.model.ranks

import org.rushton.poker.model.Card
import org.rushton.poker.model.Face
import org.rushton.poker.model.Hand
import org.rushton.poker.model.Rank

class Pair extends Hand<Pair> {

    final Rank rank = Rank.Pair

    // Constructs a list of face values in the order that they need to be compared.
    // First item is the pair, so that gets checked first
    // Subsequent items in the list are the kickers, all sorted appropriately
    int compareTo(Hand otherHand) {
        if (this.rank == otherHand.rank) {
            Pair other = otherHand as Pair
            def thisRankList = [this.getPairRank().ordinal()] + this.getKickerRanks()*.ordinal().collect { it }
            def otherRankList = [other.getPairRank().ordinal()] + other.getKickerRanks()*.ordinal().collect { it }
            utils.compareIntegerLists(thisRankList, otherRankList)
        } else {
            this.rank.ordinal() <=> otherHand.rank.ordinal()
        }
    }

    String toString() {
        "One Pair of ${getPairRank().faceString}'s with Kickers: ${getKickerRanks()*.faceString?.join("; ")}"
    }

    Face getPairRank() {
        getRankMap()?.findAll { it.value.size() == 2 }?.keySet()?.toList()?.first()
    }

    Collection<Face> getKickerRanks() {
        getRankMap()?.findAll { it.value.size() == 1 }?.keySet()?.toList()?.sort { it.ordinal() }?.reverse()
    }

    private Map<Face, List<Card>> getRankMap() {
        getCards()?.groupBy { it.face }
    }

}
