package org.rushton.poker

import spock.lang.Specification

class UtilsTest extends Specification {

    Utils utils = new Utils()

    def "get best hand of ['3H', '7S', '3S', 'QD', 'AH', '3D', '4S']"() {
        given:
        def cardStrings = ['3H', '7S', '3S', 'QD', 'AH', '3D', '4S']
        def cards = cardStrings.collect{utils.parseCardString(it)}
        def hand = utils.getBestHand(cards)

        expect:
        hand.toString().equals("Three 3's with Kickers: A; Q")
    }

    def "compare two lists of integers, l1 < l2"() {
        given:
        def l1 = [4, 3, 2, 1]
        def l2 = [5, 4, 3, 2]
        def comparison = utils.compareIntegerLists(l1, l2)

        expect:
        comparison == -1
    }

    def "compare two lists of integers, l1 < l2, nested condition"() {
        given:
        def l1 = [5, 4, 2, 1]
        def l2 = [5, 4, 3, 2]
        def comparison = utils.compareIntegerLists(l1, l2)

        expect:
        comparison == -1
    }

    def "compare two lists of integers, l1 > l2"() {
        given:
        def l1 = [5, 4, 3, 2]
        def l2 = [4, 3, 2, 1]
        def comparison = utils.compareIntegerLists(l1, l2)

        expect:
        comparison == 1
    }

    def "compare two lists of integers, l1 > l2, nested condition"() {
        given:
        def l1 = [5, 4, 3, 2]
        def l2 = [5, 3, 2, 1]
        def comparison = utils.compareIntegerLists(l1, l2)

        expect:
        comparison == 1
    }

    def "compare two lists of integers, l1 == l2"() {
        given:
        def l1 = [5, 4, 3, 2]
        def l2 = [5, 4, 3, 2]
        def comparison = utils.compareIntegerLists(l1, l2)

        expect:
        comparison == 0
    }

    def "compare two lists of integers, l1 < && shorter l2"() {
        given:
        def l1 = [4, 3]
        def l2 = [5, 4, 3, 2]
        def comparison = utils.compareIntegerLists(l1, l2)

        expect:
        comparison == -1
    }

    def "compare two lists of integers, l1 > && shorter l2"() {
        given:
        def l1 = [6, 1]
        def l2 = [5, 4, 3, 2]
        def comparison = utils.compareIntegerLists(l1, l2)

        expect:
        comparison == 1
    }

    def "compare two lists of integers, l1 == && shorter l2"() {
        given:
        def l1 = [5, 4]
        def l2 = [5, 4, 3, 2]
        def comparison = utils.compareIntegerLists(l1, l2)

        expect:
        comparison == 0
    }

    def "compare two lists of integers, l1 < && longer l2"() {
        given:
        def l1 = [4, 3, 2, 1, 1]
        def l2 = [5, 4, 3, 2]
        def comparison = utils.compareIntegerLists(l1, l2)

        expect:
        comparison == -1
    }

    def "compare two lists of integers, l1 > && longer l2"() {
        given:
        def l1 = [6, 5, 4, 3, 2]
        def l2 = [5, 4, 3, 2]
        def comparison = utils.compareIntegerLists(l1, l2)

        expect:
        comparison == 1
    }

    def "compare two lists of integers, l1 == && longer l2"() {
        given:
        def l1 = [5, 4, 3, 2, 1]
        def l2 = [5, 4, 3, 2]
        def comparison = utils.compareIntegerLists(l1, l2)

        expect:
        comparison == 0
    }

    def "2H 2D 5S 9C KD is a valid pair"() {
        given:
        def cardString = "2H 2D 5S 9C KD"
        def hand = utils.parseHandArray(cardString.split(" ").toList())

        expect:
        utils.isValidPair(hand.getCards())
    }

    def "2H 2D 2S 9C KD is a not valid pair"() {
        given:
        def cardString = "2H 2D 2S 9C KD"
        def cards = utils.parseHandArray(cardString.split(" ").toList()).cards

        expect:
        !utils.isValidPair(cards)
    }

    def "2H 3D 4S 5C KD is a not valid pair"() {
        given:
        def cardString = "2H 3D 4S 5C KD"
        def cards = utils.parseHandArray(cardString.split(" ").toList()).cards

        expect:
        !utils.isValidPair(cards)
    }
}
