package com.errant01.cardgame;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    private List<Card> h1 = Arrays.asList(h1a);
    private List<Card> h2 = Arrays.asList(h2a);

    private List<Card> initHand(Card[] cards) {
        return Arrays.asList(cards);
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

    // functional tests - testing all the guts.
    @Test
    public void orderHands() throws Exception {
        List<Card> cards1 = initHand(TestHands.STRAIGHT_FLUSH);
        List<Card> cards2 = initHand(TestHands.FOUR_KIND);
        Game g = new Game(cards1, cards2);
        g.rankHands();
        assertEquals(g.getHand1().getCards().get(0).getValue(), "A");
        assertEquals(g.getHand2().getCards().get(0).getValue(), "J");
    }

    @Test
    public void orderReversedHands() throws Exception {
        List<Card> cards2 = initHand(TestHands.STRAIGHT_FLUSH);
        List<Card> cards1 = initHand(TestHands.FOUR_KIND);
        Game g = new Game(cards1, cards2);
        g.orderHands();
        assertEquals(g.getHand1().getCards().get(0).getValue(), "A");
        assertEquals(g.getHand2().getCards().get(0).getValue(), "J");
    }
}