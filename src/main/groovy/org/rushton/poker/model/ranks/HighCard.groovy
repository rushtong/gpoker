package org.rushton.poker.model.ranks

import org.rushton.poker.model.Card
import org.rushton.poker.model.Face
import org.rushton.poker.model.Hand
import org.rushton.poker.model.Rank

class HighCard extends Hand<HighCard> {

    final Rank rank = Rank.HighCard

    int compareTo(Hand otherHand) {
        if (this.rank == otherHand.rank) {
            HighCard other = otherHand as HighCard
            utils.compareIntegerLists(
                    getCards()*.face*.ordinal().sort()?.collect { it }?.reverse(),
                    other.getCards()*.face*.ordinal().sort()?.collect { it }?.reverse())
        } else {
            this.rank.ordinal() <=> otherHand.rank.ordinal()
        }
    }

    String toString() {
        List<Face> ranks = getRankMap().keySet()?.toList()?.sort()?.reverse()
        "High Card of ${ranks?.head()?.faceString} with kickers: ${ranks?.tail()*.faceString?.join("; ")}"
    }

    private Map<Face, List<Card>> getRankMap() {
        getCards()?.groupBy { it.face }
    }

}
