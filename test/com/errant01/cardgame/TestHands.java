package com.errant01.cardgame;

public final class TestHands {
    // STRAIGHT_HI FLUSH_HI
    public static final Card[] STRAIGHT_FLUSH_HI = new Card[] {
            new Card("10", "S"),
            new Card("K", "s"),
            new Card("J", "S"),
            new Card("Q", "s"),
            new Card("A", "s")
    };

    public static final Card[] STRAIGHT_FLUSH_LO = new Card[] {
            new Card("10", "H"),
            new Card("8", "H"),
            new Card("J", "H"),
            new Card("9", "H"),
            new Card("7", "H")
    };

    // 4 of a kind
    public static final Card[] FOUR_KIND_HI = new Card[] {
            new Card("10", "S"),
            new Card("10", "C"),
            new Card("10", "H"),
            new Card("10", "D"),
            new Card("J", "D")
    };

    public static final Card[] FOUR_KIND_LO = new Card[] {
            new Card("10", "S"),
            new Card("10", "C"),
            new Card("10", "H"),
            new Card("10", "D"),
            new Card("5", "D")
    };

    // full house
    public static final Card[] FULL_HOUSE_HI = new Card[] {
            new Card("10", "S"),
            new Card("J", "S"),
            new Card("10", "H"),
            new Card("10", "D"),
            new Card("J", "D")
    };

    public static final Card[] FULL_HOUSE_MID = new Card[] {
            new Card("10", "S"),
            new Card("5", "S"),
            new Card("10", "H"),
            new Card("10", "D"),
            new Card("5", "D")
    };

    public static final Card[] FULL_HOUSE_LO = new Card[] {
            new Card("9", "S"),
            new Card("J", "S"),
            new Card("9", "H"),
            new Card("9", "D"),
            new Card("J", "D")
    };

    // flush
    public static final Card[] FLUSH_HI = new Card[] {
            new Card("10", "D"),
            new Card("8", "d"),
            new Card("9", "d"),
            new Card("J", "D"),
            new Card("3", "d")
    };

    public static final Card[] FLUSH_LO = new Card[] {
            new Card("2", "D"),
            new Card("8", "d"),
            new Card("9", "d"),
            new Card("J", "D"),
            new Card("3", "d")
    };

    // straight
    public static final Card[] STRAIGHT_HI = new Card[] {
            new Card("10", "S"),
            new Card("K", "D"),
            new Card("J", "S"),
            new Card("Q", "H"),
            new Card("A", "D")
    };

    public static final Card[] STRAIGHT_LO = new Card[] {
            new Card("10", "S"),
            new Card("8", "D"),
            new Card("J", "S"),
            new Card("Q", "H"),
            new Card("9", "D")
    };

    public static final Card[] STRAIGHT_ACE_LO = new Card[] {
            new Card("5", "S"),
            new Card("3", "D"),
            new Card("A", "S"),
            new Card("2", "H"),
            new Card("4", "D")
    };


    // 3 of a kind
    public static final Card[] THREE_KIND_HI = new Card[] {
            new Card("10", "D"),
            new Card("8", "S"),
            new Card("10", "S"),
            new Card("9", "D"),
            new Card("10", "H")
    };

    public static final Card[] THREE_KIND_LO = new Card[] {
            new Card("10", "D"),
            new Card("8", "S"),
            new Card("10", "S"),
            new Card("4", "D"),
            new Card("10", "H")
    };

    // 2 pair
    public static final Card[] TWO_PAIR_HI = new Card[] {
            new Card("10", "S"),
            new Card("10", "H"),
            new Card("J", "S"),
            new Card("8", "D"),
            new Card("J", "D")
    };

    public static final Card[] TWO_PAIR_MID = new Card[] {
            new Card("10", "S"),
            new Card("10", "H"),
            new Card("J", "S"),
            new Card("2", "D"),
            new Card("J", "D")
    };

    public static final Card[] TWO_PAIR_LO = new Card[] {
            new Card("7", "S"),
            new Card("8", "H"),
            new Card("J", "S"),
            new Card("8", "D"),
            new Card("J", "D")
    };

    // 1 pair
    public static final Card[] ONE_PAIR_HI = new Card[] {
            new Card("A", "D"),
            new Card("8", "S"),
            new Card("9", "S"),
            new Card("5", "D"),
            new Card("A", "H")
    };

    public static final Card[] ONE_PAIR_LO = new Card[] {
            new Card("A", "D"),
            new Card("8", "S"),
            new Card("9", "S"),
            new Card("3", "D"),
            new Card("A", "H")
    };

    // high card
    public static final Card[] HIGH_CARD_HI = new Card[] {
            new Card("10", "D"),
            new Card("8", "S"),
            new Card("9", "S"),
            new Card("J", "D"),
            new Card("4", "H")
    };

    public static final Card[] HIGH_CARD_LO = new Card[] {
            new Card("10", "D"),
            new Card("3", "S"),
            new Card("9", "S"),
            new Card("J", "D"),
            new Card("4", "H")
    };
}
