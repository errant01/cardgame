package com.errant01.cardgame;

import java.util.List;

public class Game {

    private Hand hand1;
    private Hand hand2;
    private boolean tie = false;

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

    public void rankHands() {
        hand1.determineRank();
        hand2.determineRank();
    }

    public void orderHands() {
        if (hand1.getRank() == null) {
            rankHands();
        }

        if (hand1.getRank().getLevel() > hand2.getRank().getLevel()) {
            swapHands();
            return;
        } else if (hand1.getRank().getLevel() < hand2.getRank().getLevel()) {
            return;
        } else {
            tie = true;
        }

        // determine tie case
        switch (hand1.getRank()) {
            case STRAIGHT_FLUSH:
                // check high card only
                orderHandsByCompareAtCardIndex(0);
                break;
            case FOUR_OF_KIND:
                // check high card, if tie, check last card
                orderHandsByCompareAtCardIndex(0);
                if (this.tie) {
                    tie = false;
                    orderHandsByCompareAtCardIndex(4);
                }
                break;
            case FULL_HOUSE:
                // check big big group, if tie, check small group
                break;
            case FLUSH:
                // check next highest card
                break;
            case STRAIGHT:
                // check high card only
                orderHandsByCompareAtCardIndex(0);
                break;
            case THREE_OF_KIND:
                // check high group, if tie, check for next highest card
                break;
            case TWO_PAIR:
                // check high group, if tie, check lo group, if tie, check high last card
                break;
            case PAIR:
                // check high group, if tie then high card
                break;
            case HIGH_CARD:
                // check next highest card
                orderHandsByNextHighestCard(0);
                break;
        }
    }

    private void orderHandsByNextHighestCard(int startIndex) {
        while (startIndex < hand1.getCards().size() && this.tie) {
            orderHandsByCompareAtCardIndex(startIndex);
            startIndex++;
        }
    }

    private void orderHandsByCompareAtCardIndex(int index) {
        int compareValue = hand1.getCards().get(index).compareTo(hand2.getCards().get(index));
        if (compareValue == 0) {
            tie = true;
        } else if (compareValue == -1) { // swap hand1 with hand2
            swapHands();
            tie = false;
        } else {
            tie = false;
        }
    }

    private void swapHands() {
        System.out.println("Swapping hands");
        Hand temp = hand1;
        hand1 = hand2;
        hand2 = temp;
    }

    public String asString() {
        return hand1.asString() + System.getProperty("line.separator") + hand2.asString();
    }
}
