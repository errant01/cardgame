package com.errant01.cardgame;

import java.util.Comparator;

public class Card {
    private String value;
    private String suit;

    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    // TODO improve perf - this is an expensive calc to make on sorting Comparator
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

    /**
     * For console display
     * @return Card in format "$value $suit"
     */
    public String asString() {
        return this.value + " " + this.suit;
    }
}
