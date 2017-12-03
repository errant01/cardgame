package com.errant01.cardgame;

import java.util.Arrays;

public class CardRank {

    // TODO replace this with Loader from a file defining hands
    // probably belongs associated with Game, like GameLoader. In the case of a general game you would config
    // Deck to use and Game to play, then load assoc data (like initial hands from the deck)
    // At this stage, a Deck would only be used to verify that hands contain legal cards

    private static Card[] hand1 = new Card[] {
            new Card("10", "S"),
            new Card("J", "S"),
            new Card("10", "H"),
            new Card("8", "D"),
            new Card("J", "D"),
    };

    private static Card[] hand2 = new Card[] {
            new Card("10", "D"),
            new Card("8", "S"),
            new Card("9", "S"),
            new Card("J", "D"),
            new Card("10", "H"),
    };

    public static void main(String[] args) {
        Game game = new Game(Arrays.asList(hand1), Arrays.asList(hand2));
        // TODO separate Hand loading step from Game init
        System.out.println("----  Before ----");
        System.out.println(game.asString());
        game.sortHands();
        System.out.println("----  After ----");
        System.out.println(game.asString());
    }

}
