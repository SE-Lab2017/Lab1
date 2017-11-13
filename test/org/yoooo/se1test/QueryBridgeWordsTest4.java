package org.yoooo.se1test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.yoooo.se1.QueryBridgeWords;

public class QueryBridgeWordsTest4 {

    private String wordL = "to";
    private String wordR = "to";
    private String expected = "The bridge word from \"to\" to \"to\" is: sichuan.";
    private String expectedRev = "The bridge word from \"to\" to \"to\" is: sichuan.";

    @Test
    public void test() {
        assertEquals(expected, QueryBridgeWords.queryBridgeWords(wordL, wordR));
        assertEquals(expectedRev, QueryBridgeWords.queryBridgeWords(wordR, wordL));
    }

}
