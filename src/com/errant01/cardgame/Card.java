package com.errant01.cardgame;

public class Card implements Comparable<Card> {
    private String value;
    private String suit;

    public Card(String value, String suit) {
        this.value = value.toUpperCase();
        this.suit = suit.toUpperCase();
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    // TODO improve perf - this is an expensive calc to make on sorting Comparator
    // TODO go back to int: used Integer here over int for simplicity of parseInt test and return val. In hindsight, the ripple
    // of Integer and comparison is a larger pain point.
    public Integer getIntegerValue() {
        try {
            return new Integer(value);
        } catch (NumberFormatException ne) {
            int intVal = 0;
            switch (value) {
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

    @Override
    public int compareTo(Card c) {
        // suits don't affect value ranking
        if (getIntegerValue().equals(c.getIntegerValue())) {
            return 0;
        } else {
            return getIntegerValue() > c.getIntegerValue() ? 1 : -1;
        }

    }

    /**
     * For console display
     * @return Card in format "$value $suit"
     */
    public String asString() {
        return value + " " + suit;
    }
}
