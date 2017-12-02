package com.errant01.cardgame;

import java.util.Comparator;

public class Game {
    private Card[] hand1;
    private Card[] hand2;

    public Game(Card[] hand1, Card[] hand2) {
        this.hand1 = hand1;
        this.hand2 = hand2;
    }

//    public void sortHand(Card[] hand) {
//        Comparator<Card> comparator = Comparator.comparing(card -> card.getIntValue());
//        comparator = comparator.thenComparing(Comparator.comparing(card -> card.getSuit()));
//
//    }

    public String asString() {
        return handAsString(hand1) + System.getProperty("line.separator") + handAsString(hand2);
    }

    private String handAsString(Card[] cards) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Card card: cards) {
            sb.append(card.asString()).append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}
