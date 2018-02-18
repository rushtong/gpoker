package org.rushton.poker.model.ranks

import org.rushton.poker.model.Hand
import org.rushton.poker.model.Rank

class Straight extends Hand<Straight> {

    Rank rank = Rank.Straight

    int compareTo(Hand otherHand) {
        if (this.rank == otherHand.rank) {
            Straight other = otherHand as Straight
            this.getHighestCardValue() <=> other.getHighestCardValue()
        } else {
            this.rank.ordinal() <=> otherHand.rank.ordinal()
        }
    }

    String toString() {
        "Straight of ${getCards().sort { it.face.ordinal() }*.face.join("; ")}"
    }

    private Integer getHighestCardValue() {
        getCards()*.face*.ordinal().sort().reverse().head()
    }


}
