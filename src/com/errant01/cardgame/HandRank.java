package com.errant01.cardgame;

public enum HandRank {
    STRAIGHT_FLUSH (1, "Straight Flush"),
    FOUR_OF_KIND (2, "Four of a kind"),
    FULL_HOUSE (3, "Full House"),
    FLUSH (4, "Flush"),
    STRAIGHT (5, "Straight"),
    THREE_OF_KIND (6, "Three of a kind"),
    TWO_PAIR (7, "Two Pair"),
    PAIR (8, "One Pair"),
    HIGH_CARD (9, "High Card");

    private final int rank;
    private final String label;

    HandRank(int rank, String label) {
        this.rank = rank;
        this.label = label;
    }

    public int getRank() {
        return rank;
    }

    public String getLabel() {
        return label;
    }
}
