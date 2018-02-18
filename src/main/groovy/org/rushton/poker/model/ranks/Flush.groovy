package org.rushton.poker.model.ranks

import org.rushton.poker.model.Face
import org.rushton.poker.model.Hand
import org.rushton.poker.model.Rank

class Flush extends Hand<Flush> {

    Rank rank = Rank.Flush

    int compareTo(Hand otherHand) {
        if (this.rank == otherHand.rank) {
            Flush other = otherHand as Flush
            this.getHighestFaceCard().ordinal() <=> other.getHighestFaceCard().ordinal()
        } else {
            this.rank.ordinal() <=> otherHand.rank.ordinal()
        }
    }

    String toString() {
        "Flush of ${getCards()*.suit.head().name()}, ${getHighestFaceCard().faceString} high"
    }


    private Face getHighestFaceCard() {
        getCards().sort { it.face.ordinal() }.reverse().head().face
    }

}
