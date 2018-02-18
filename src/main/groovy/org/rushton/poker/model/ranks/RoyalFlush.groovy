package org.rushton.poker.model.ranks

import org.rushton.poker.model.Hand
import org.rushton.poker.model.Rank

class RoyalFlush extends Hand<RoyalFlush> {

    Rank rank = Rank.RoyalFlush

    int compareTo(Hand otherHand) {
        if (this.rank == otherHand.rank) {
            0 // all royal flushes are of equal value
        } else {
            this.rank.ordinal() <=> otherHand.rank.ordinal()
        }
    }

    String toString() {
        "Royal Flush of ${getCards()*.suit.head().name()}"
    }

}
