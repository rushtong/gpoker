package org.rushton.poker.hands

import org.rushton.poker.Utils
import org.rushton.poker.model.Hand
import spock.lang.Specification

class PairTest extends Specification {

    Utils utils = new Utils()

    def "compare two unequal Pairs, hand1 < hand2"() {
        given:
        Hand hand1 = utils.parseHandArray("2H 2D 5S 9C KD".split(" ").toList())
        Hand hand2 = utils.parseHandArray("3H 4S 4C 2D JD".split(" ").toList())

        expect:
        hand1 < hand2
    }

    def "compare two unequal Pairs, hand1 > hand2"() {
        given:
        Hand hand1 = utils.parseHandArray("2H KH 5S 9C KD".split(" ").toList())
        Hand hand2 = utils.parseHandArray("3H 4S 4C 2D JD".split(" ").toList())

        expect:
        hand1 > hand2
    }


    def "compare two equal Pairs, hand1 < hand2 based on kicker"() {
        given:
        Hand hand1 = utils.parseHandArray("4H 4D 5S 9C JD".split(" ").toList())
        Hand hand2 = utils.parseHandArray("3H 4S 4C 2D KD".split(" ").toList())

        expect:
        hand1 < hand2
    }

    def "compare two equal Pairs, hand1 > hand2 based on kicker"() {
        given:
        Hand hand1 = utils.parseHandArray("4H 4D 5S 9C AD".split(" ").toList())
        Hand hand2 = utils.parseHandArray("3H 4S 4C 2D QD".split(" ").toList())

        expect:
        hand1 > hand2
    }


}
