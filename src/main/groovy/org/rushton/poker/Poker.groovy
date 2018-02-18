package org.rushton.poker

import groovy.util.logging.Slf4j
import org.rushton.poker.model.Card
import org.rushton.poker.model.Hand

@Slf4j
class Poker {

    private static List<Hand> makeSomeHands() {
        Utils utils = new Utils()
        [
                utils.parseHandArray(["2H", "3H", "4H", "5H", "6H"]),
                utils.parseHandArray(["2H", "4H", "6H", "8H", "10H"]),
                utils.parseHandArray(["JH", "10C", "8S", "QD", "9H"]),
                utils.parseHandArray(["JH", "10C", "8S", "7D", "9H"]),
                utils.parseHandArray(["JH", "4C", "4S", "4H", "9H"]),
                utils.parseHandArray(["JH", "JC", "4S", "4H", "JD"]),
                utils.parseHandArray(["QH", "4C", "3S", "JC", "9H"]),
                utils.parseHandArray(["KH", "4C", "4S", "JC", "9H"]),
                utils.parseHandArray(["JH", "4C", "4S", "4H", "JD"]),
                utils.parseHandArray(["3S", "4S", "5S", "6S", "7S"]),
                utils.parseHandArray(["JH", "JS", "JD", "JC", "5H"]),
                utils.parseHandArray(["10C", "KC", "JC", "QC", "AC"]),
                utils.parseHandArray(["QH", "4C", "4S", "JC", "9H"]),
                utils.parseHandArray(["QH", "4C", "4S", "QC", "9H"]),
                utils.parseHandArray(["JH", "4C", "4S", "JC", "9H"]),
        ]
    }

    static void main(String[] args) {

        Utils utils = new Utils()

        def hands = makeSomeHands().sort()
        log.info " Default Hands:"
        hands.each {
            log.info "\t${it}"
        }

        log.info "Highest of the Default Hands:"
        log.info "\t${hands.last()}"

        Collection<String> cardStrings = ["3H", "7S", "3S", "QD", "AH", "3D", "4S"]
        Collection<Card> cards = cardStrings.collect{utils.parseCardString(it)}
        log.info("Best hand of ['3H', '7S', '3S', 'QD', 'AH', '3D', '4S']:")
        log.info("\t ${utils.getBestHand(cards)}")


        Collection<Card> deck = utils.shuffleDeck()
        Collection<Card> randomCards = deck.take(7)
        log.info("Best hand of Random Cards: ['${randomCards.collect{it.face.faceString + it.suit.suit}.join("', '")}']:")
        log.info("\t ${utils.getBestHand(randomCards)}")

    }

}
