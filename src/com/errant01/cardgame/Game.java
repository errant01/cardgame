package com.errant01.cardgame;

import java.util.List;

public class Game {

    private Hand hand1;
    private Hand hand2;

    public Game(List<Card> cards1, List<Card> cards2) {

        this.hand1 = new Hand(cards1);
        this.hand2 = new Hand(cards2);;
    }

    public Hand getHand1() {
        return hand1;
    }

    public Hand getHand2() {
        return hand2;
    }

    public void sortHands() {
        hand1.sort();
        hand2.sort();
    }

    public void evaluateHands() {
        hand1.evaluate();
        hand2.evaluate();
    }

    public String asString() {
        return hand1.asString() + System.getProperty("line.separator") + hand2.asString();
    }
}
