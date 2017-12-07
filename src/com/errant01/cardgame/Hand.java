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
        Comparator<Card> comparator = Comparator.comparing(card -> card.getIntegerValue());
        comparator = comparator.reversed().thenComparing(Comparator.comparing(card -> card.getSuit()));

        Stream<Card> cardStream = cards.stream().sorted(comparator);
        cards = cardStream.collect(Collectors.toList());
        sorted = true;
    }

    // TODO convert to scoring method here for performance, especially if project will involve more than two hands or type of game
    public void evaluate() {
        determineStraightFlush();
        if (!straight && !flush) {
            determineGroups();
        }
        evaluated = true;
    }

    public void determineRank() {
        if (!evaluated) {
            evaluate();
        }

        if (isStraight()) {
            if (isFlush()) {
                rank = HandRank.STRAIGHT_FLUSH;
                return;
            } else {
                rank = HandRank.STRAIGHT;
                return;
            }
        } else {
            if (isFlush()) {
                rank = HandRank.FLUSH;
                return;
            }
        }

        if (bigGroup.size() == 4) {
            rank = HandRank.FOUR_OF_KIND;
            return;
        } else if (bigGroup.size() == 3) {
            if (smGroup.size() == 2) {
                rank = HandRank.FULL_HOUSE;
                return;
            } else {
                rank = HandRank.THREE_OF_KIND;
                return;
            }
        } else if (bigGroup.size() == 2) {
            if (smGroup.size() == 2) {
                rank = HandRank.TWO_PAIR;
                return;
            } else {
                rank = HandRank.PAIR;
                return;
            }
        }

        rank = HandRank.HIGH_CARD;
    }

    /**
     * For console display
     * @return String in format "[$card, $card, ...]"
     */
    public String asString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Card card: cards) {
            sb.append(card.asString()).append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        if (rank != null) {
            sb.append(" ").append(rank.getLabel());
        }

        return sb.toString();
    }

    private void determineAceLowStraight(int aceIndex) {
        // makeAceLow
        cards.get(aceIndex).setValue("AL");

        // check for straight again
        sort();
        straight = true;
        for (int i = 0; i < cards.size() - 1; i++) {
            if (!isTwoCardSeq(cards.get(i), cards.get(i + 1))) {
                straight = false;
                break;
            }
        }

        // if !straight, revert
        if (!straight) {
            cards.get(aceIndex).setValue("A");
            sort();
        }
    }

    private void determineStraightFlush() {
        if (!sorted) {
            sort();
        }
        flush = true;
        String compareSuit = cards.get(0).getSuit();
        straight = true;
        int aceCount = 0;
        int aceIndex = 0;
        // optimizing to traverse only once for both straight and flush checks
        for (int i = 0; i < cards.size(); i++) {
            // fail flush if suit different
            if (flush && !cards.get(i).getSuit().equals(compareSuit)) {
                flush = false;
            }
            // fail straight if next card not in sequence
            if (straight && i < cards.size() - 1 && !isTwoCardSeq(cards.get(i), cards.get(i + 1))) {
                straight = false;
            }
            // count Aces
            if (aceCount < 2 && cards.get(i).getValue().equals("A")) {
                aceIndex = i;
                aceCount++;
            }
        }

        if (!straight && aceCount == 1) {
            determineAceLowStraight(aceIndex);
        }
    }

    private boolean isTwoCardSeq(Card c1, Card c2) {
        return c1.getIntegerValue() - c2.getIntegerValue() == 1;
    }

    // groups are only value based
    private void determineGroups() {
        Map<String, List<Card>> mapOfGroups = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue));

        // this group split is specific to 5 card Poker
        for (String value : mapOfGroups.keySet()) {
            if (mapOfGroups.get(value).size() > 1) {
                if (bigGroup.isEmpty()) {
                    bigGroup.addAll(mapOfGroups.get(value));
                } else {
                    // there can be no more than 2 groups of two or more things, so no more groups will match
                    smGroup.addAll(mapOfGroups.get(value));
                }
            }
        }

        if (hasGroups()) {
            orderGroups();
        }
    }

    private void orderGroups() {
        if ((smGroup.size() > bigGroup.size())
                || (smGroup.size() == bigGroup.size() && (smGroup.get(0).getIntegerValue() > bigGroup.get(0).getIntegerValue()))) {
            // swap
            List<Card> tempGroup = bigGroup;
            bigGroup = smGroup;
            smGroup = tempGroup;
        }
    }

    private boolean hasGroups() {
        return bigGroup.size() > 0;
    }
}
