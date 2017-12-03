package com.errant01.cardgame;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

    private List<Card> hand1;
    private List<Card> hand2;

    public Game(List<Card> hand1, List<Card> hand2) {
        this.hand1 = hand1;
        this.hand2 = hand2;
    }

    public List<Card> getHand1() {
        return hand1;
    }

    public List<Card> getHand2() {
        return hand2;
    }

    public void sortHands() {
        hand1 = sortHand(hand1);
        hand2 = sortHand(hand2);
    }

    public String asString() {
        return handAsString(hand1) + System.getProperty("line.separator") + handAsString(hand2);
    }

    private List<Card> sortHand(List<Card> hand) {
        // Use Java Streams with comparator, faster, more compact
        Comparator<Card> comparator = Comparator.comparing(card -> card.getIntValue());
        comparator = comparator.reversed().thenComparing(Comparator.comparing(card -> card.getSuit()));

        Stream<Card> cardStream = hand.stream().sorted(comparator);
        return cardStream.collect(Collectors.toList());
    }

    private String handAsString(List<Card> cards) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        // TODO convert to list iterator
        for (Card card: cards) {
            sb.append(card.asString()).append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}
