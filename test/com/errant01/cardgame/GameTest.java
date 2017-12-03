package com.errant01.cardgame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    Card[] h1a = new Card[] {
            new Card("J", "D"),
            new Card("10", "S")
    };

    Card[] h2a = new Card[] {
            new Card("10", "S"),
            new Card("8", "S"),
            new Card("10", "H")
    };

    private List<Card> h1;
    private List<Card> h2;

    @Before
    public void setUp() throws Exception {
        h1 = Arrays.asList(h1a);
        h2 = Arrays.asList(h2a);
    }

    @After
    public void tearDown() throws Exception {
        h1 = null;
        h2 = null;
    }

    @Test
    public void getHand1() throws Exception {
        Game g = new Game(h1, h2);
        assertEquals(g.getHand1().getCards().size(), h1.size());
        // ok to test by obj id for now, will need to move to comp by value when using loader
        assertEquals(g.getHand1().getCards().get(0), h1.get(0));
        assertEquals(g.getHand1().getCards().get(1), h1.get(1));
    }

    @Test
    public void getHand2() throws Exception {
        Game g = new Game(h1, h2);
        assertEquals(g.getHand2().getCards().size(), h2.size());
        // ok to test by obj id for now, will need to move to comp by value when using loader
        assertEquals(g.getHand2().getCards().get(0), h2.get(0));
        assertEquals(g.getHand2().getCards().get(2), h2.get(2));
    }

    @Test
    public void sortHands() throws Exception {
        Game g = new Game(h1, h2);
        g.sortHands();
        assertTrue(g.getHand1().isSorted());
        assertTrue(g.getHand2().isSorted());
    }
}