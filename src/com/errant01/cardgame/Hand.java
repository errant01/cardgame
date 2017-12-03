package com.errant01.cardgame;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hand {
    private List<Card> cards;
    private boolean sorted = false;
    private boolean flush = false;
    private boolean straight = false;
    private List<Card> bigGroup;
    private List<Card> smGroup;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isSorted() {
        return sorted;
    }

    public boolean isFlush() {
        return flush;
    }

    public boolean isStraight() {
        return straight;
    }

    public List<Card> getBigGroup() {
        return bigGroup;
    }

    public List<Card> getSmGroup() {
        return smGroup;
    }

    public void sort() {
        // Use Java Streams with comparator, faster, more compact
        Comparator<Card> comparator = Comparator.comparing(card -> card.getIntValue());
        comparator = comparator.reversed().thenComparing(Comparator.comparing(card -> card.getSuit()));

        Stream<Card> cardStream = cards.stream().sorted(comparator);
        cards = cardStream.collect(Collectors.toList());
        sorted = true;
    }

    // TODO convert to scoring method here for performance, especially if project will involve more than two hands or type of game
    public void evaluate() {
        determineFlush();
        determineStraight();
    }

    /**
     * For console display
     * @return String in format "[$card, $card, ...]"
     */
    public String asString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Card card: this.cards) {
            sb.append(card.asString()).append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        if (this.straight) {
            sb.append(" ").append("isStraight");
        }
        if (this.flush) {
            sb.append(" ").append("isFlush");
        }
        return sb.toString();
    }

    // All 5 cards must be in a flush
    private void determineFlush() {
        if (!sorted) {
            sort();
        }
        this.flush = true;
        String compareSuit = this.cards.get(0).getSuit();
        // only LinkedList suffers for perf on for with counter loops, so ok to use to skip 0 index
        for (int i = 1; i < this.cards.size(); i++) {
            if (!this.cards.get(i).getSuit().equals(compareSuit)) {
                this.flush = false;
                break;
            }
        }
    }

    // All 5 cards must be in a straight
    private void determineStraight() {
        if (!sorted) {
            sort();
        }
        this.straight = true;
        // check that all cards are a single decrement from previous
        for (int i = 0; i < this.cards.size() - 1; i++) {
            if (!isTwoCardSeq(this.cards.get(i), this.cards.get(i + 1))) {
                this.straight = false;
                break;
            }
        }
    }

    private boolean isTwoCardSeq(Card c1, Card c2) {
        return c1.getIntValue() - c2.getIntValue() == 1;
    }
}
