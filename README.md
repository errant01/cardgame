# cardgame
Package to rank hands in a card game

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

