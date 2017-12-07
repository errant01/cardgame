package com.errant01.cardgame;

import java.lang.reflect.Field;
import java.util.Arrays;

public class CardRank {

    // TODO replace this with Loader from a file defining hands, or from string representative input in CLI

    // straight flush
    private static Card[] hand1 = new Card[] {
            new Card("10", "S"),
            new Card("K", "s"),
            new Card("J", "S"),
            new Card("Q", "s"),
            new Card("A", "s")
    };

    // 1 pair
    private static Card[] hand2 = new Card[] {
            new Card("10", "D"),
            new Card("8", "S"),
            new Card("9", "S"),
            new Card("J", "D"),
            new Card("10", "H")
    };

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException{
        // TODO separate Hand loading step from Game init
        Game game;
        if (args.length == 2) {
            System.out.println("Using input: " + args[0] + ", " + args[1]);
            Field f1 = TestHands.class.getDeclaredField(args[0]);
            Field f2 = TestHands.class.getDeclaredField(args[1]);
            game = new Game(Arrays.asList((Card[])f1.get(null)), Arrays.asList((Card[])f2.get(null)));
        } else {
            game = new Game(Arrays.asList(hand1), Arrays.asList(hand2));
        }

        System.out.println("----  Hands being Compared  ----");
        System.out.println(game.asString());
        game.orderHands();
        System.out.println("----  Ranking  ----");
        System.out.println(game.asString());
    }
}
