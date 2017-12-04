package com.errant01.cardgame;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HandTest {

    // 4 of a kind
    private Card[] fourKind = new Card[] {
            new Card("10", "S"),
            new Card("10", "C"),
            new Card("10", "H"),
            new Card("10", "D"),
            new Card("J", "D")
    };

    // full house
    private Card[] boat = new Card[] {
            new Card("10", "S"),
            new Card("J", "S"),
            new Card("10", "H"),
            new Card("10", "D"),
            new Card("J", "D")
    };

    // 3 of a kind
    private Card[] trips = new Card[] {
            new Card("10", "D"),
            new Card("8", "S"),
            new Card("10", "S"),
            new Card("J", "D"),
            new Card("10", "H")
    };

    // 2 pair
    private Card[] twoPair = new Card[] {
            new Card("10", "S"),
            new Card("10", "H"),
            new Card("J", "S"),
            new Card("8", "D"),
            new Card("J", "D")
    };

    // 1 pair
    private Card[] onePair = new Card[] {
            new Card("10", "D"),
            new Card("8", "S"),
            new Card("9", "S"),
            new Card("J", "D"),
            new Card("10", "H")
    };

    // high card
    private Card[] highCard = new Card[] {
            new Card("10", "D"),
            new Card("8", "S"),
            new Card("9", "S"),
            new Card("J", "D"),
            new Card("4", "H")
    };

    // straight
    private Card[] straight = new Card[] {
            new Card("10", "S"),
            new Card("K", "D"),
            new Card("J", "S"),
            new Card("Q", "H"),
            new Card("A", "D")
    };

    // straight flush
    private Card[] straightFlush = new Card[] {
            new Card("10", "S"),
            new Card("K", "s"),
            new Card("J", "S"),
            new Card("Q", "s"),
            new Card("A", "s")
    };

    // flush
    private Card[] flush = new Card[] {
            new Card("10", "D"),
            new Card("8", "d"),
            new Card("9", "d"),
            new Card("J", "D"),
            new Card("3", "d")
    };

    @Test
    public void sort() throws Exception {
        Hand h = new Hand(Arrays.asList(twoPair));
        h.sort();
        assertTrue(h.isSorted());
        // tests value sort and suit sort
        assertEquals(h.getCards().get(0).getValue(), twoPair[4].getValue());
        assertEquals(h.getCards().get(0).getSuit(), twoPair[4].getSuit());
        assertEquals(h.getCards().get(1).getValue(), twoPair[2].getValue());
        assertEquals(h.getCards().get(1).getSuit(), twoPair[2].getSuit());
    }

    @Test
    public void evaluateFlush() throws Exception {
        Hand falseHand = new Hand(Arrays.asList(twoPair));
        falseHand.evaluate();
        assertFalse(falseHand.isFlush());

        Hand trueHand = new Hand(Arrays.asList(flush));
        trueHand.evaluate();
        assertTrue(trueHand.isFlush());
    }

    @Test
    public void evaluateStraight() throws Exception {
        Hand falseHand = new Hand(Arrays.asList(twoPair));
        falseHand.evaluate();
        assertFalse(falseHand.isFlush());

        Hand trueHand = new Hand(Arrays.asList(straight));
        trueHand.evaluate();
        assertTrue(trueHand.isStraight());
    }

    @Test
    public void determineNoGroups() throws Exception {
        Hand noGroup = new Hand(Arrays.asList(highCard));
        noGroup.evaluate();
        assertTrue(noGroup.getBigGroup().isEmpty());
        assertTrue(noGroup.getSmGroup().isEmpty());
    }

    @Test
    public void determineOneGroup() throws Exception {
        Hand noGroup = new Hand(Arrays.asList(onePair));
        noGroup.evaluate();
        assertFalse(noGroup.getBigGroup().isEmpty());
        assertTrue(noGroup.getSmGroup().isEmpty());
    }

    @Test
    public void determineTwoGroup() throws Exception {
        Hand noGroup = new Hand(Arrays.asList(twoPair));
        noGroup.evaluate();
        assertFalse(noGroup.getBigGroup().isEmpty());
        assertFalse(noGroup.getSmGroup().isEmpty());
    }

    @Test
    public void determineBigGroupIsBigger() throws Exception {
        Hand noGroup = new Hand(Arrays.asList(boat));
        noGroup.evaluate();
        assertTrue(noGroup.getBigGroup().size() > noGroup.getSmGroup().size());
    }

    @Test
    public void determineBigGroupIsHigherWhenPairs() throws Exception {
        Hand noGroup = new Hand(Arrays.asList(twoPair));
        noGroup.evaluate();
        assertTrue(noGroup.getBigGroup().get(0).getIntValue() > noGroup.getSmGroup().get(0).getIntValue());
    }
}