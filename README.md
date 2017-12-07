# cardgame
Package to rank hands in a card game (5 card Poker, Ace high, no jokers, no wilds)

# Setup
Install Java 8 JDK per OS requirements

run `java -version` and make sure it returns the expected version

clone this repo to a dir on your filesystem

Go to repo root dir

# Compile app
```bash
cd src
javac com/errant01/cardgame/CardRank.java
```

# Run CardRank app
after Compile app step, run the CardRank class

to run the default hands
```bash
java com.errant01.cardgame.CardRank
```
to run pregenerated hands, look in source of `TestHands.java` and add two of the constant names to the command line.

(note: if you use more or less than 2 args, it will go to default. If you type something wrong, it can cause an exception)
```bash
java com.errant01.cardgame.CardRank FLUSH_HI FULL_HOUSE_LO
```
to run custom hands, edit the default hands in `CardRank.java`, recompile and run the default command above.

# Compile and Run Tests
after Compile app step, run these commands to compile
```bash
cd ../test
javac -cp ../src:../ext/lib/junit-4.12.jar com/errant01/cardgame/GameTest.java com/errant01/cardgame/HandTest.java

```
Then run these commands to run the two test files
```bash
java -cp .:../src:../ext/lib/junit-4.12.jar:../ext/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore com.errant01.cardgame.HandTest
java -cp .:../src:../ext/lib/junit-4.12.jar:../ext/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore com.errant01.cardgame.GameTest

```
The tests will run, each one is a . on the console. If there are failures they will be identified on the console.

# PostMortem Notes 

Here is a list of Notes on areas to improve and a discussion on improving extensibility.

## Areas to Improve

* Introducing a Config operation - This should be used to set up the Game, which would include things like, what kind of 
game is being played, and what rules. This example explores only one game type with only one rule variant.
* Formalizing a Loader object - Loading should be separated from Config
* Make hands a List on Game, not 2 discrete fields
* Extraction of critical order comparison functions in Hand class to a Utility class to simplify unit testing. If scoring
is not used, this would allow testing of the comparison logic directly instead of through final results of the ordering process.
* Scoring over compare ordering of hands - If more than one hand need to be ordered to find a winner, or if better performance
is needed to handle more comparisons faster, then using a Scoring approach instead of a Comparison approach on the hand is better.
All of the tie resolution logic complexity would be replaced by the scoring algorithm/logic, and ordering multiple hands in a 
Game just becomes a simple number sort.
* Introduce the Deck object - Using a Deck with a Set of known cards allows the hand loading process to do some compositon
error checking, making sure the hands being created come from the Deck, and if using Removal from the Deck Card Set, then 
it would catch duplicates as well
* Look into better ways to represent Suits and Values - The need for an Integer value for comparison as well as a String value
for visualization / data representation causes some inefficient calculation in sorting the Hand and tie reconciliation. Using
an enum is likely the way to go, but would involve a parsing layer to go from the current data representation to 
the enum in code.
* Exception handling, mostly to inform the user of incorrect cards being used in meaningful language.


## Extensibility

Some of the discussion on extensibility depends on what the requirements actually look like for the future. There is some 
tension between the idea of YAGNI, and smartly choosing areas to make easy to expand if your are "80%" sure it will happen.

In this exercise, I chose more of the YAGNI approach, as this module could be used in different ways. Efficiency is lost
and abstraction complication goes up if a particular piece of software is built to do ALL THE THINGS. For example, if just 
versions of Poker are considered in the future scope, Deck variants are minimized and Rule sets are small. 

But, what if parts of this hand ranking system should be used to feed a computer hand play strategy module for Pinochle, 
which has a significantly different deck? In this case the grouping system needs to be expanded and given more flexibility.
What if its used for other deck based card games, like Uno, or if its used to be a generic final ranking calculator for 
a "house poker night" game in which there are MANY game variants with all kinds of grouping/scoring rules determining winners?
Maybe all of this is just a subsystem and is part of an actual Poker Game application that needs to handle the turn based
process of the game itself.

Given that requirements should drive extensibility, here are some thoughts:
* Use config to load a Game instance using composition. This would contain:
  * Deck - is likely best represented as an abstract Base with concrete classes to define specific composition. 
  Deck contains a Set of Cards composed of Suits and Values (if double "deck" games like Canasta are played, then adjustment
  will be needed to fit in the Set definition).
  * GameType - defined by config and used to identify Hand composition and which CardSorter and Scorer to use in them.
  HandRank will need different concrete impl's per GameType
  * Hands - a specialized container with List characteristics see below
  * Logic for ordering (sorting) the hands in score order
  * Logic for driving the "game sequence" of operations
* Hand should be composed of some different elements:
  * List of Cards
  * CardSorter concrete impl per config / GameType, static utility
  * Scorer concrete impl per config / GameType, static utility