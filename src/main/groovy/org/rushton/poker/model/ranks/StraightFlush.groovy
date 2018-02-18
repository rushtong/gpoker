package org.rushton.poker.model.ranks

import org.rushton.poker.model.Face
import org.rushton.poker.model.Hand
import org.rushton.poker.model.Rank

class StraightFlush extends Hand<StraightFlush> {

    Rank rank = Rank.StraightFlush

    int compareTo(Hand otherHand) {
        if (this.rank == otherHand.rank) {
            StraightFlush other = otherHand as StraightFlush
            this.getHighestFaceCard().ordinal() <=> other.getHighestFaceCard().ordinal()
        } else {
            this.rank.ordinal() <=> otherHand.rank.ordinal()
        }
    }

    String toString() {
        "Straight Flush of ${getCards()*.suit.head().name()}, ${getHighestFaceCard().faceString} high"
    }


    private Face getHighestFaceCard() {
        getCards().sort { it.face.ordinal() }.reverse().head().face
    }

}
