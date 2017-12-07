package com.errant01.cardgame;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Hand hand1;
    private Hand hand2;
    private boolean tie = false;

    public Game(List<Card> cards1, List<Card> cards2) {

        hand1 = new Hand(cards1);
        hand2 = new Hand(cards2);;
    }

    public Hand getHand1() {
        return hand1;
    }

    public Hand getHand2() {
        return hand2;
    }

    public boolean isTie() {
        return tie;
    }

    public void sortHands() {
        hand1.sort();
        hand2.sort();
    }

    public void rankHands() {
        hand1.determineRank();
        hand2.determineRank();
    }

    public void orderHands() {
        if (hand1.getRank() == null) {
            rankHands();
        }

        // order by hand rank level
        if (hand1.getRank().getLevel() > hand2.getRank().getLevel()) {
            swapHands();
            return;
        } else if (hand1.getRank().getLevel() < hand2.getRank().getLevel()) {
            return;
        }

        // determine tie case
        switch (hand1.getRank()) {
            case STRAIGHT_FLUSH:
                // check high card only
                orderHandsByCompareAtCardIndex(0, hand1.getCards(), hand2.getCards());
                break;
            case FOUR_OF_KIND:
                // check big group card, if tie, remove group and check remaining card
                orderHandsByGroups();

                if (tie) {
                    orderByHandsMinusGroups();
                }
                break;
            case FULL_HOUSE:
                // check big group, if tie, check small group
                orderHandsByGroups();
                break;
            case FLUSH:
                // check next highest card
                tie = true;
                orderHandsByNextHighestCard(0, hand1.getCards(), hand2.getCards());
                break;
            case STRAIGHT:
                // check high card only
                orderHandsByCompareAtCardIndex(0, hand1.getCards(), hand2.getCards());
                break;
            case THREE_OF_KIND:
                // check high group, if tie, check for next highest card after removing group
                orderHandsByGroups();
                if (tie) {
                    orderByHandsMinusGroups();
                }
                break;
            case TWO_PAIR:
                // check high group, if tie, check lo group, if tie, check high last card
                orderHandsByGroups();

                if (tie) {
                    orderByHandsMinusGroups();
                }
                break;
            case PAIR:
                // check high group, if tie then remove group, check for high card
                orderHandsByGroups();

                if (tie) {
                    orderByHandsMinusGroups();
                }

                break;
            case HIGH_CARD:
                // check next highest card
                tie = true;
                orderHandsByNextHighestCard(0, hand1.getCards(), hand2.getCards());
                break;
        }
    }

    // TODO extract these ordering to util class with public methods for isolated testing
    // Util class returns boolean for swap or not, swap is handled in this class
    // must pass card Lists in hand1, hand2 order

    private void orderByHandsMinusGroups() {
        List<Card> compHand1 = cardsMinusGroups(hand1);
        List<Card> compHand2 = cardsMinusGroups(hand2);
        orderHandsByNextHighestCard(0, compHand1, compHand2);
    }
    private void orderHandsByGroups() {
        Integer intValGroup1 = hand1.getBigGroup().get(0).getIntegerValue();
        Integer intValGroup2 = hand2.getBigGroup().get(0).getIntegerValue();
        if (intValGroup1.equals(intValGroup2)) {
            tie = true;
        } else if (intValGroup2 > intValGroup1) {
            swapHands();
        }

        if (tie && !hand1.getSmGroup().isEmpty()) {
            tie = false;
            intValGroup1 = hand1.getSmGroup().get(0).getIntegerValue();
            intValGroup2 = hand2.getSmGroup().get(0).getIntegerValue();
            if (intValGroup1.equals(intValGroup2)) {
                tie = true;
            } else if (intValGroup2 > intValGroup1) {
                swapHands();
            }
        }
    }

    private void orderHandsByNextHighestCard(int index, List<Card> h1, List<Card> h2) {
        while (index < h1.size() && tie) {
            orderHandsByCompareAtCardIndex(index, h1, h2);
            index++;
        }
    }

    // must pass card Lists in hand1, hand2 order
    private void orderHandsByCompareAtCardIndex(int index, List<Card> h1, List<Card> h2) {
        int compareValue = h1.get(index).compareTo(h2.get(index));
        if (compareValue == 0) {
            tie = true;
        } else if (compareValue == -1) { // swap hand1 with hand2
            swapHands();
            tie = false;
        } else {
            tie = false;
        }
    }

    private List<Card> cardsMinusGroups(Hand h) {
        List<Card> leftovers = new ArrayList<>(h.getCards());
        leftovers.removeAll(h.getBigGroup());
        leftovers.removeAll(h.getSmGroup());
        return leftovers;
    }

    private void swapHands() {
        Hand temp = hand1;
        hand1 = hand2;
        hand2 = temp;
    }

    public String asString() {
        return hand1.asString() + System.getProperty("line.separator") + hand2.asString();
    }
}
