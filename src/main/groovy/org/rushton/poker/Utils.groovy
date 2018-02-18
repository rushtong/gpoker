package org.rushton.poker

import groovy.json.JsonOutput
import groovy.transform.TailRecursive
import org.rushton.poker.model.Card
import org.rushton.poker.model.Face
import org.rushton.poker.model.Hand
import org.rushton.poker.model.Suit
import org.rushton.poker.model.ranks.*


class Utils {

    static Collection<Card> shuffleDeck() {
        Collection<Card> deck = GroovyCollections.combinations(Suit.values(), Face.values()).collect { s, f ->
            new Card(suit: s, face: f)
        }
        Collections.shuffle(deck)
        deck
    }

    static Hand getBestHand(Collection<Card> cards) {
        def permutations = []
        def cardStrings = cards.collect {it.face.faceString + it.suit.suit}
        cardStrings.eachPermutation { permutations << it }
        Collection<Hand> hands = permutations.collect { it.take(5).sort() }.unique().collect {
            parseHandArray(it as List<String>)
        }
        hands.sort().last()
    }

    /**
     * Process cards and generate a hand, trying the highest rank first, and so on.
     * The assumption here is that we are only dealing with valid 5-card hands. All
     * of the underlying `isValid...` methods implicitly expect that condition.
     *
     * @param cardStrings List of card strings
     * @return parsed Hand
     */
    static Hand parseHandArray(List<String> cardStrings) {
        Collection<Card> cards = cardStrings.collect { parseCardString(it.trim()) }
        Hand hand
        switch (cards) {
            case { isValidRoyalFlush(cards) }:
                hand = new RoyalFlush(cards: cards)
                break
            case { isValidStraightFlush(cards) }:
                hand = new StraightFlush(cards: cards)
                break
            case { isValidFourOfAKind(cards) }:
                hand = new FourOfAKind(cards: cards)
                break
            case { isValidFullHouse(cards) }:
                hand = new FullHouse(cards: cards)
                break
            case { isValidFlush(cards) }:
                hand = new Flush(cards: cards)
                break
            case { isValidStraight(cards) }:
                hand = new Straight(cards: cards)
                break
            case { isValidThreeOfAKind(cards) }:
                hand = new ThreeOfAKind(cards: cards)
                break
            case { isValidTwoPair(cards) }:
                hand = new TwoPair(cards: cards)
                break
            case { isValidPair(cards) }:
                hand = new Pair(cards: cards)
                break
            case { isValidHighCard(cards) }:
                hand = new HighCard(cards: cards)
                break
            default:
                hand = new HighCard(cards: cards)
                break
        }
        hand
    }

    static Boolean isValidRoyalFlush(Collection<Card> cards) {
        isValidStraightFlush(cards) && cards*.face.contains(Face.Ace)
    }

    static Boolean isValidStraightFlush(Collection<Card> cards) {
        isValidStraight(cards) && isValidFlush(cards)
    }

    static Boolean isValidFourOfAKind(Collection<Card> cards) {
        getFaceGroups(cards)?.findAll { it.value.size() == 4 }?.size() == 1
    }

    static Boolean isValidFullHouse(Collection<Card> cards) {
        isValidThreeOfAKind(cards) && isValidPair(cards)
    }

    static Boolean isValidFlush(Collection<Card> cards) {
        getSuitGroups(cards).keySet().size() == 1
    }

    static Boolean isValidStraight(Collection<Card> cards) {
        def faceValues = cards*.face*.ordinal().sort().collect { it }
        isListInOrderedSequence(faceValues)
    }

    static Boolean isValidThreeOfAKind(Collection<Card> cards) {
        getFaceGroups(cards)?.findAll { it.value.size() == 3 }?.size() == 1
    }

    static Boolean isValidTwoPair(Collection<Card> cards) {
        getFaceGroups(cards)?.findAll { it.value.size() == 2 }?.size() == 2
    }

    static Boolean isValidPair(Collection<Card> cards) {
        getFaceGroups(cards)?.findAll { it.value.size() == 2 }?.size() == 1
    }

    static Boolean isValidHighCard(Collection<Card> cards) {
        !getFaceGroups(cards)?.any { it.value.size() != 1 }
    }


    @TailRecursive
    static Boolean isListInOrderedSequence(List<Integer> intList) {
        // Since we are always comparing the head to the head of the tail,
        // If at the end our comparison, assume true
        if (intList.isEmpty() || intList.tail().isEmpty()) return true
        def x = intList.head()
        def y = intList.tail().head()
        if (y == null || x == null || y - x != 1)
            return false
        isListInOrderedSequence(intList.tail())
    }

    @TailRecursive
    int compareIntegerLists(List<Integer> one, List<Integer> two) {
        // If any element is null, we've exhausted all prior comparisons,
        // consider the two lists as equivalent
        if (one.isEmpty() || two.isEmpty()) return 0
        def head1 = one.head()
        def head2 = two.head()
        if (head1 != head2)
            return head1 <=> head2
        compareIntegerLists(one.tail(), two.tail())
    }

    static Card parseCardString(String str) {
        Suit suit = Suit.getSuit(str.reverse().take(1).toString())
        Face face = Face.getFace(str.reverse().drop(1).reverse().toString())
        new Card(face: face, suit: suit)
    }

    /**
     * Utility method to collect groups of cards with the same face value
     * Face is the key and the card list value will all have the same face.
     * Good for collecting pairs, three of a kind, four of a kind, etc.
     *
     * @param hand
     * @param size
     * @return Map of Face to cards of the same face
     */
    static Map<Face, List<Card>> getFaceGroups(Collection<Card> cards) { cards?.groupBy { it.face } }

    /**
     * Utility method to collect groups of cards with the same suit.
     * Suit is the key and the card list value will all have the same suit.
     * Good for collecting flushes, straight flushes, etc.
     *
     * @param hand
     * @return Map of Suit to cards of the same suit
     */
    private static Map<Suit, List<Card>> getSuitGroups(Collection<Card> cards) {
        cards?.groupBy { it.suit }
    }

}