package com.errant01.cardgame;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
        List<Card> cards1 = initHand(TestHands.STRAIGHT_FLUSH_HI);
        List<Card> cards2 = initHand(TestHands.FOUR_KIND_HI);
        Game g = new Game(cards1, cards2);
        g.rankHands();
        assertEquals(g.getHand1().getCards().get(0).getValue(), "A");
        assertEquals(g.getHand2().getCards().get(0).getValue(), "J");
    }

    @Test
    public void orderReversedHands() throws Exception {
        List<Card> cards2 = initHand(TestHands.STRAIGHT_FLUSH_HI);
        List<Card> cards1 = initHand(TestHands.FOUR_KIND_HI);
        Game g = new Game(cards1, cards2);
        g.orderHands();
        assertEquals(g.getHand1().getCards().get(0).getValue(), "A");
        assertEquals(g.getHand2().getCards().get(0).getValue(), "J");
    }

    // Check ties
    @Test
    public void straightFlushTie() throws Exception {
        List<Card> cards2 = initHand(TestHands.STRAIGHT_FLUSH_HI);
        List<Card> cards1 = initHand(TestHands.STRAIGHT_FLUSH_LO);
        Game g = new Game(cards1, cards2);
        g.orderHands();
        assertFalse(g.isTie());
        assertEquals(g.getHand1().getCards().get(0).getValue(), "A");
        assertEquals(g.getHand2().getCards().get(0).getValue(), "J");

        Game g2 = new Game(cards1, cards1);
        g2.orderHands();
        assertTrue(g2.isTie());
    }

    @Test
    public void fourOfaKindTie() throws Exception {
        List<Card> cards1 = initHand(TestHands.FOUR_KIND_HI);
        List<Card> cards2 = initHand(TestHands.FOUR_KIND_LO);
        Game g = new Game(cards1, cards2);
        g.orderHands();
        // check kickers in known position
        assertEquals(g.getHand1().getCards().get(0).getValue(), "J");
        assertEquals(g.getHand2().getCards().get(4).getValue(), "5");

        Game g2 = new Game(cards2, cards1);
        g2.orderHands();
        // check kickers in known position
        assertEquals(g2.getHand1().getCards().get(0).getValue(), "J");
        assertEquals(g2.getHand2().getCards().get(4).getValue(), "5");

        Game g3 = new Game(cards1, cards1);
        g3.orderHands();
        assertTrue(g3.isTie());
    }

    @Test
    public void fullHouseTie() throws Exception {
        List<Card> cards1 = initHand(TestHands.FULL_HOUSE_HI);
        List<Card> cards2 = initHand(TestHands.FULL_HOUSE_MID);
        List<Card> cards3 = initHand(TestHands.FULL_HOUSE_LO);
        Game g = new Game(cards1, cards2);
        g.orderHands();
        // check sm value, big is same
        assertEquals(g.getHand1().getSmGroup().get(0).getValue(), "J");
        assertEquals(g.getHand2().getSmGroup().get(0).getValue(), "5");

        Game g2 = new Game(cards1, cards3);
        g2.orderHands();
        // check big value, big is different
        assertEquals(g2.getHand1().getBigGroup().get(0).getValue(), "10");
        assertEquals(g2.getHand2().getBigGroup().get(0).getValue(), "9");

        Game g3 = new Game(cards3, cards1);
        g3.orderHands();
        assertEquals(g3.getHand1().getBigGroup().get(0).getValue(), "10");
        assertEquals(g3.getHand2().getBigGroup().get(0).getValue(), "9");

        Game g4 = new Game(cards1, cards1);
        g4.orderHands();
        assertTrue(g4.isTie());
    }

    @Test
    public void flushTie() throws Exception {
        List<Card> cards1 = initHand(TestHands.FLUSH_HI);
        List<Card> cards2 = initHand(TestHands.FLUSH_LO);
        Game g = new Game(cards1, cards2);
        g.orderHands();
        // check diff cards at known position
        assertEquals(g.getHand1().getCards().get(1).getValue(), "10");
        assertEquals(g.getHand2().getCards().get(1).getValue(), "9");

        Game g2 = new Game(cards2, cards1);
        g2.orderHands();
        assertEquals(g2.getHand1().getCards().get(1).getValue(), "10");
        assertEquals(g2.getHand2().getCards().get(1).getValue(), "9");

        Game g3 = new Game(cards1, cards1);
        g3.orderHands();
        assertTrue(g3.isTie());
    }

    @Test
    public void straightTie() throws Exception {
        List<Card> cards1 = initHand(TestHands.STRAIGHT_HI);
        List<Card> cards2 = initHand(TestHands.STRAIGHT_LO);
        Game g = new Game(cards1, cards2);
        g.orderHands();
        // check diff cards at known position
        assertEquals(g.getHand1().getCards().get(0).getValue(), "A");
        assertEquals(g.getHand2().getCards().get(0).getValue(), "Q");

        Game g2 = new Game(cards2, cards1);
        g2.orderHands();
        assertEquals(g2.getHand1().getCards().get(0).getValue(), "A");
        assertEquals(g2.getHand2().getCards().get(0).getValue(), "Q");

        Game g3 = new Game(cards1, cards1);
        g3.orderHands();
        assertTrue(g3.isTie());
    }

    @Test
    public void threeOfaKindTie() throws Exception {
        List<Card> cards1 = initHand(TestHands.THREE_KIND_HI);
        List<Card> cards2 = initHand(TestHands.THREE_KIND_LO);
        Game g = new Game(cards1, cards2);
        g.orderHands();
        // check diff cards at known position
        assertEquals(g.getHand1().getCards().get(3).getValue(), "9");
        assertEquals(g.getHand2().getCards().get(3).getValue(), "8");

        Game g2 = new Game(cards2, cards1);
        g2.orderHands();
        assertEquals(g2.getHand1().getCards().get(3).getValue(), "9");
        assertEquals(g2.getHand2().getCards().get(3).getValue(), "8");

        Game g3 = new Game(cards2, cards2);
        g3.orderHands();
        assertTrue(g3.isTie());
    }

    @Test
    public void twoPairTie() throws Exception {
        List<Card> cards1 = initHand(TestHands.TWO_PAIR_HI);
        List<Card> cards2 = initHand(TestHands.TWO_PAIR_MID);
        List<Card> cards3 = initHand(TestHands.TWO_PAIR_LO);
        Game g = new Game(cards1, cards2);
        g.orderHands();
        // check last card, pairs same
        assertEquals(g.getHand1().getCards().get(4).getValue(), "8");
        assertEquals(g.getHand2().getCards().get(4).getValue(), "2");

        Game g2 = new Game(cards1, cards3);
        g2.orderHands();
        // check sm grp, big is same
        assertEquals(g2.getHand1().getSmGroup().get(0).getValue(), "10");
        assertEquals(g2.getHand2().getSmGroup().get(0).getValue(), "8");

        Game g3 = new Game(cards3, cards1);
        g3.orderHands();
        assertEquals(g3.getHand1().getSmGroup().get(0).getValue(), "10");
        assertEquals(g3.getHand2().getSmGroup().get(0).getValue(), "8");

        Game g4 = new Game(cards1, cards1);
        g4.orderHands();
        assertTrue(g4.isTie());
    }

    @Test
    public void onePairTie() throws Exception {
        List<Card> cards1 = initHand(TestHands.ONE_PAIR_HI);
        List<Card> cards2 = initHand(TestHands.ONE_PAIR_LO);
        Game g = new Game(cards1, cards2);
        g.orderHands();
        // check diff cards at known position
        assertEquals(g.getHand1().getCards().get(4).getValue(), "5");
        assertEquals(g.getHand2().getCards().get(4).getValue(), "3");

        Game g2 = new Game(cards2, cards1);
        g2.orderHands();
        assertEquals(g2.getHand1().getCards().get(4).getValue(), "5");
        assertEquals(g2.getHand2().getCards().get(4).getValue(), "3");

        Game g3 = new Game(cards1, cards1);
        g3.orderHands();
        assertTrue(g3.isTie());
    }

    @Test
    public void highCardTie() throws Exception {
        List<Card> cards1 = initHand(TestHands.HIGH_CARD_HI);
        List<Card> cards2 = initHand(TestHands.HIGH_CARD_LO);
        Game g = new Game(cards1, cards2);
        g.orderHands();
        // check diff cards at known position
        assertEquals(g.getHand1().getCards().get(3).getValue(), "8");
        assertEquals(g.getHand2().getCards().get(3).getValue(), "4");

        Game g2 = new Game(cards2, cards1);
        g2.orderHands();
        assertEquals(g2.getHand1().getCards().get(3).getValue(), "8");
        assertEquals(g2.getHand2().getCards().get(3).getValue(), "4");

        Game g3 = new Game(cards1, cards1);
        g3.orderHands();
        assertTrue(g3.isTie());
    }

    @Test
    public void compareAceLowStraight() throws Exception {
        List<Card> cards1 = initHand(TestHands.STRAIGHT_ACE_LO);
        List<Card> cards2 = initHand(TestHands.STRAIGHT_HI);
        Game g = new Game(cards1, cards2);
        g.orderHands();
        // check first cards
        assertEquals(g.getHand1().getCards().get(0).getValue(), "A");
        assertEquals(g.getHand2().getCards().get(0).getValue(), "5");

        Game g2 = new Game(cards2, cards1);
        g2.orderHands();
        assertEquals(g2.getHand1().getCards().get(0).getValue(), "A");
        assertEquals(g2.getHand2().getCards().get(0).getValue(), "5");
    }

}