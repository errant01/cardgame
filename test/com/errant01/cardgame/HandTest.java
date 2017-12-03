package com.errant01.cardgame;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HandTest {

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
}