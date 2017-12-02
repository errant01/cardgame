package com.errant01.cardgame;

public class CardRank {

    private static Card[] hand1 = new Card[] {
            new Card("10", "S"),
            new Card("J", "S"),
            new Card("10", "H"),
            new Card("10", "D"),
            new Card("J", "D"),
    };

    private static Card[] hand2 = new Card[] {
            new Card("8", "S"),
            new Card("9", "S"),
            new Card("10", "H"),
            new Card("10", "D"),
            new Card("J", "D"),
    };

    public static void main(String[] args) {
        Game game = new Game(hand1, hand2);
        System.out.println(game.asString());
    }

}
