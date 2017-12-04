# cardgame
Package to level hands in a card game

# Setup
Install Java 8 JDK per OS requirements

Go to repo root dir

run java -version and make sure it returns the expected version

# Compile app
cd src

javac com/errant01/cardgame/CardRank.java

# Run CardRank app
after Compile app step

java com.errant01.cardgame.CardRank

# Compile and Run Tests
after Compile app step

cd ../test

javac -cp ../src:../ext/lib/junit-4.12.jar com/errant01/cardgame/CardRankTestSuite.java

java -cp .:../src:../ext/lib/junit-4.12.jar:../ext/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore com.errant01.cardgame.CardRankTestSuite
