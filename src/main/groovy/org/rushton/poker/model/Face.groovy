package org.rushton.poker.model

enum Face {
    Two("2"),
    Three("3"),
    Four("4"),
    Five("5"),
    Six("6"),
    Seven("7"),
    Eight("8"),
    Nine("9"),
    Ten("10"),
    Jack("J"),
    Queen("Q"),
    King("K"),
    Ace("A")

    String faceString

    Face(String faceString) {
        this.faceString = faceString
    }

    static Face getFace(String faceAbbr) {
        values().find { it.faceString.equalsIgnoreCase(faceAbbr) }
    }

}