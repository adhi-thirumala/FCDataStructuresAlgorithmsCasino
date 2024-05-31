# Java Jackpot Fox Den Casino
## Description of the Program
### Features (Lower number means high priority)
* Recreated Random package that calls [Random.org](https://www.random.org) API
* Good GUI and interface between the games 
* Has shared money over the games
* Has
  * Blackjack (2)
  * Roulette (3)
  * Craps (4)
  * Baccarat (5)
* Gives players up to two loans if they go below zero dollars 
* Allows the player to cash out whenever they want
* Has persistent scoreboard with top scores
### Inputs
* Clicks for the games
* Name for the scoreboard
### Outputs
* Graphical interface with scores and the games
* Scoreboard stored in text file as to be persistent across launches
### Data Structures
* Stack for the deck of cards in Blackjack and Baccarat

## Sources
### Game Rules
https://www.cse.psu.edu/~gik2/teach/craps.pdf - Craps
https://en.wikipedia.org/wiki/Roulette#Rules_of_play_against_a_casino - roulette
https://en.wikipedia.org/wiki/Blackjack#Player_decisions - blackjack rules

## Rules of Craps
### First roll a pair of (assumed fair) six-sided dice.
* If the outcome (dice sum) of the first roll X is 7 or 11 then the
player wins,
* else if the X ∈ {2, 3, 12} then the player loses (“craps out”),
* else define the “point” X ∈ {4, 5, 6, 8, 9, 10} and the player
continues to roll the dice.
### For each subsequent (independent) roll of the dice:
* If the player rolls the point X then they win,
* else if the player rolls 7 then they lose,
* else they roll again.


## GPT Generated rules of Baccarat
### Objective
The objective of Baccarat is to bet on which of two hands, the Player or the Banker, will have a total closest to 9, or if the round will end in a tie.

### Card Values
- **Aces** are worth 1 point.
- **2 to 9** are worth their face value.
- **10s, Jacks, Queens, and Kings** are worth 0 points.

### Game Setup
1. **Betting:** Players place their bets on one of three options:
  - Player hand wins
  - Banker hand wins
  - Tie (both hands have the same total)

### Dealing the Cards
1. **Initial Deal:** The dealer deals two cards each to the Player and the Banker, usually face-up.
2. **Natural Win:** If either the Player or the Banker has a total of 8 or 9 with the initial deal (called a "natural"), no more cards are drawn, and the winner is determined. If both have naturals with the same total, it's a tie.

### Drawing Additional Cards
1. **Player's Rule:**
  - If the Player's total is 0-5, the Player draws a third card.
  - If the Player's total is 6 or 7, the Player stands.

1. **Banker's Rule:** The Banker's actions depend on the Player's third card (if any) and the Banker's own total:
  - If the Banker's total is 0-2, the Banker always draws a third card.
  - If the Banker's total is 3, the Banker draws a third card unless the Player's third card was an 8.
  - If the Banker's total is 4, the Banker draws a third card if the Player's third card was 2, 3, 4, 5, 6, or 7.
  - If the Banker's total is 5, the Banker draws a third card if the Player's third card was 4, 5, 6, or 7.
  - If the Banker's total is 6, the Banker draws a third card if the Player's third card was a 6 or 7.
  - If the Banker's total is 7, the Banker stands.

### Calculating the Total
- The totals are calculated by adding the values of the cards. If the total is more than 10, only the rightmost digit of the total is considered. For example, a hand with a 7 and an 8 (totaling 15) counts as 5.

### Winning
- The hand with a total closest to 9 wins.
- If you bet on the Player's hand and it wins, you get paid 1:1.
- If you bet on the Banker's hand and it wins, you get paid 1:1 minus a 5% commission (due to the slight statistical advantage).
- If you bet on a tie and it wins, you get paid 8:1 or 9:1, depending on the casino.
