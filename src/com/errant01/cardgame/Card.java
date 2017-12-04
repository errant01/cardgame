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
    public Integer getIntValue() {
        try {
            return new Integer(this.value);
        } catch (NumberFormatException ne) {
            int intVal = 0;
            switch (this.value) {
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
        if (this.getIntValue() == c.getIntValue() ) {
            return 0;
        } else {
            return this.getIntValue() > c.getIntValue() ? 1 : -1;
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
