package org.rushton.poker.hands

import org.rushton.poker.Utils
import spock.lang.Specification

class TwoPairTest extends Specification {

    Utils utils = new Utils()

    def "compare two unequal Pairs, hand1 < hand 2"() {
        given:
        def hand1 = utils.parseHandArray("2H 2D 3S 3C KD".split(" ").toList())
        def hand2 = utils.parseHandArray("3H 3S 4C 4D JD".split(" ").toList())

        expect:
        hand1 < hand2
    }

    def "compare two equal Pairs, hand1 > hand 2 based on kicker"() {
        given:
        def hand1 = utils.parseHandArray("3H 3S 4C 4D JD".split(" ").toList())
        def hand2 = utils.parseHandArray("3H 3D 4S 4C 7D".split(" ").toList())

        expect:
        hand1 > hand2
    }

}
