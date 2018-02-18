package org.rushton.poker.model

import groovy.json.JsonOutput
import org.rushton.poker.Utils
import sun.reflect.generics.reflectiveObjects.NotImplementedException

class Hand<T> implements Comparable<Hand> {

    Utils utils = new Utils()
    Collection<Card> cards
    Rank rank = null

    // Subclasses must implement
    int compareTo(Hand otherHand) { throw new NotImplementedException() }

    // Subclasses can implement
    String toString() { JsonOutput.toJson(getCards()).toString() }

}
