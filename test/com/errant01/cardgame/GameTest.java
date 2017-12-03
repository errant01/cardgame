package com.errant01.cardgame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    private Card[] h1;
    private Card[] h2;

    @Before
    public void setUp() throws Exception {

        h1 = new Card[] {
                new Card("10", "S"),
                new Card("J", "D")
        };

        h2 = new Card[] {
                new Card("8", "S"),
                new Card("10", "H"),
                new Card("9", "S")
        };
    }

    @After
    public void tearDown() throws Exception {
        h1 = null;
        h2 = null;
    }

    @Test
    public void getHand1() throws Exception {
        Game g = new Game(h1, h2);
        assertEquals(g.getHand1().length, h1.length);
        assertEquals(g.getHand1()[0], h1[0]);
        assertEquals(g.getHand1()[1], h1[1]);
    }

    @Test
    public void getHand2() throws Exception {
        Game g = new Game(h1, h2);
        assertEquals(g.getHand2().length, h2.length);
        assertEquals(g.getHand2()[0], h2[0]);
        assertEquals(g.getHand2()[2], h2[2]);
    }
}