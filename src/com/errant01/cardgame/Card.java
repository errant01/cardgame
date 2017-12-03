package com.errant01.cardgame;

import java.util.Comparator;

public class Card {
    private String value;
    private String suit;

    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public Integer getIntValue() {
        try {
            return new Integer(this.value);
        } catch (NumberFormatException ne) {
            int intVal = 0;
            switch (this.value.toUpperCase()) {
                case "J":
                    intVal = 11;
                    break;
                case "Q":
                    intVal = 12;
                    break;
                case "K":
                    intVal = 13;
                    break;
                case "A":
                    intVal = 14;
                    break;
                default:
                    throw new NumberFormatException("Card value not allowed in card: " + asString() + ". Correct Input.");
            }
            return new Integer(intVal);
        }
    }

    public String asString() {
        return this.value + " " + this.suit;
    }

    public static Comparator<Card> CardRankComparator = new Comparator<Card>() {
        @Override
        public int compare(Card c1, Card c2) {
            return c1.getIntValue().compareTo(c2.getIntValue());
        }
    };

    public static Comparator<Card> CardSuitComparator = new Comparator<Card>() {
        @Override
        public int compare(Card c1, Card c2) {
            return c1.getSuit().toUpperCase().compareTo(c2.getSuit().toUpperCase());
        }
    };
}
