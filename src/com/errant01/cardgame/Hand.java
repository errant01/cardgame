package com.errant01.cardgame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hand {
    private List<Card> cards;
    private boolean sorted = false;
    private boolean evaluated = false;
    private boolean flush = false;
    private boolean straight = false;
    private List<Card> bigGroup = new ArrayList<>();
    private List<Card> smGroup = new ArrayList<>();
    private HandRank rank;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isSorted() {
        return sorted;
    }

    public boolean isEvaluated() {
        return evaluated;
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

    public HandRank getRank() {
        return rank;
    }

    public void sort() {
        // Use Java Streams with comparator, faster, more compact than old way
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
        if (!straight && !flush) {
            determineGroups();
        }
        evaluated = true;
    }

    public void determineRank() {
        if (!this.evaluated) {
            evaluate();
        }

        if (isStraight()) {
            if (isFlush()) {
                this.rank = HandRank.STRAIGHT_FLUSH;
                return;
            } else {
                this.rank = HandRank.STRAIGHT;
                return;
            }
        } else {
            if (isFlush()) {
                this.rank = HandRank.FLUSH;
                return;
            }
        }

        if (this.bigGroup.size() == 4) {
            this.rank = HandRank.FOUR_OF_KIND;
            return;
        } else if (this.bigGroup.size() == 3) {
            if (this.smGroup.size() == 2) {
                this.rank = HandRank.FULL_HOUSE;
                return;
            } else {
                this.rank = HandRank.THREE_OF_KIND;
                return;
            }
        } else if (this.bigGroup.size() == 2) {
            if (this.smGroup.size() == 2) {
                this.rank = HandRank.TWO_PAIR;
                return;
            } else {
                this.rank = HandRank.PAIR;
                return;
            }
        }

        this.rank = HandRank.HIGH_CARD;
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
        if (!this.bigGroup.isEmpty()) {
            sb.append(" ").append(this.bigGroup.size()).append(" of ").append(this.bigGroup.get(0).getValue());
        }
        if (!this.smGroup.isEmpty()) {
            sb.append(" ").append(this.smGroup.size()).append(" of ").append(this.smGroup.get(0).getValue());
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

    // groups are only value based
    private void determineGroups() {
        Map<String, List<Card>> mapOfGroups = this.cards.stream()
                .collect(Collectors.groupingBy(Card::getValue));

        // this group split is specific to 5 card Poker
        for (String value : mapOfGroups.keySet()) {
            if (mapOfGroups.get(value).size() > 1) {
                if (this.bigGroup.isEmpty()) {
                    this.bigGroup.addAll(mapOfGroups.get(value));
                } else {
                    // there can be no more than 2 groups of two or more things, so no more groups will match
                    this.smGroup.addAll(mapOfGroups.get(value));
                }
            }
        }

        orderGroups();
    }

    private void orderGroups() {
        if (this.smGroup.size() > this.bigGroup.size()) {
            // swap
            List<Card> tempGroup = this.bigGroup;
            this.bigGroup = this.smGroup;
            this.smGroup = tempGroup;
        }
    }
}
