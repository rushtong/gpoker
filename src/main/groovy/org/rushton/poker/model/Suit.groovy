package org.rushton.poker.model

enum Suit {
    Hearts("H"),
    Diamonds("D"),
    Spades("S"),
    Clubs("C");

    String suit

    Suit(String suit) {
        this.suit = suit
    }

    static Suit getSuit(String suitAbbr) {
        values().find { it.getSuit().equalsIgnoreCase(suitAbbr) }
    }

}