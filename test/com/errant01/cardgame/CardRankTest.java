package com.errant01.cardgame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import com.errant01.cardgame.CardRank;

import static org.junit.Assert.*;

public class CardRankTest {

    private String output;
    @Before
    public void setUp() throws Exception {
        output = "This is a card game hand ranking app.";
    }

    @After
    public void tearDown() throws Exception {
        output = null;
    }

    @Test
    public void generateOutput() throws Exception {
        assertEquals(output, CardRank.generateOutput());
    }

}