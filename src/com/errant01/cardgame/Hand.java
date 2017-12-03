package com.errant01.cardgame;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hand {
    private List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void sort() {
        // Use Java Streams with comparator, faster, more compact
        Comparator<Card> comparator = Comparator.comparing(card -> card.getIntValue());
        comparator = comparator.reversed().thenComparing(Comparator.comparing(card -> card.getSuit()));

        Stream<Card> cardStream = cards.stream().sorted(comparator);
        cards = cardStream.collect(Collectors.toList());
    }

    /**
     * For console display
     * @return String in format "[$card, $card, ...]"
     */
    public String asString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        // TODO convert to list iterator
        for (Card card: this.cards) {
            sb.append(card.asString()).append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}
