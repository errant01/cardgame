package com.errant01.cardgame;

public final class TestHands {
    // STRAIGHT FLUSH
    public static final Card[] STRAIGHT_FLUSH = new Card[] {
            new Card("10", "S"),
            new Card("K", "s"),
            new Card("J", "S"),
            new Card("Q", "s"),
            new Card("A", "s")
    };

    // 4 of a kind
    public static final Card[] FOUR_KIND = new Card[] {
            new Card("10", "S"),
            new Card("10", "C"),
            new Card("10", "H"),
            new Card("10", "D"),
            new Card("J", "D")
    };

    // full house
    public static final Card[] FULL_HOUSE = new Card[] {
            new Card("10", "S"),
            new Card("J", "S"),
            new Card("10", "H"),
            new Card("10", "D"),
            new Card("J", "D")
    };

    // FLUSH
    public static final Card[] FLUSH = new Card[] {
            new Card("10", "D"),
            new Card("8", "d"),
            new Card("9", "d"),
            new Card("J", "D"),
            new Card("3", "d")
    };

    // STRAIGHT
    public static final Card[] STRAIGHT = new Card[] {
            new Card("10", "S"),
            new Card("K", "D"),
            new Card("J", "S"),
            new Card("Q", "H"),
            new Card("A", "D")
    };

    // 3 of a kind
    public static final Card[] THREE_KIND = new Card[] {
            new Card("10", "D"),
            new Card("8", "S"),
            new Card("10", "S"),
            new Card("J", "D"),
            new Card("10", "H")
    };

    // 2 pair
    public static final Card[] TWO_PAIR = new Card[] {
            new Card("10", "S"),
            new Card("10", "H"),
            new Card("J", "S"),
            new Card("8", "D"),
            new Card("J", "D")
    };

    // 1 pair
    public static final Card[] ONE_PAIR = new Card[] {
            new Card("10", "D"),
            new Card("8", "S"),
            new Card("9", "S"),
            new Card("J", "D"),
            new Card("10", "H")
    };

    // high card
    public static final Card[] HIGH_CARD = new Card[] {
            new Card("10", "D"),
            new Card("8", "S"),
            new Card("9", "S"),
            new Card("J", "D"),
            new Card("4", "H")
    };
}
