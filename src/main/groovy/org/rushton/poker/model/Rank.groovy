package org.rushton.poker.model

/**
 * Ranks are ordered such that the ordinal value can be used for comparison,
 * e.g. RoyalFlush.ordinal > StraightFlush.ordinal, etc.
 */
enum Rank {

    HighCard,
    Pair,
    TwoPair,
    ThreeOfAKind,
    Straight,
    Flush,
    FullHouse,
    FourOfAKind,
    StraightFlush,
    RoyalFlush

}