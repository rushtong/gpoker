# Groovy Poker
* Poker Hands: https://www.cardplayer.com/rules-of-poker/hand-rankings 
* Cards: https://en.wikipedia.org/wiki/Standard_52-card_deck

## Assumptions
* A single 52 card deck will be in use
* No wild cards
* Aces are treated as high cards only

# Requirements
Install gradle (i.e. `brew install gradle` or something specific to your platform)

Run wrapper: 
```
gradle wrapper
```

# Running
* Runs with a default set of hands and finds the highest.
* Finds the best of 7 cards from a predefined hand.
* Finds the best of 7 random cards from a shuffled deck.
```
gradle run
```

# Testing
Light on the tests. Beginnings are in place, need to expand. 
```
gradle clean test
```