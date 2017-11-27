package org.yoooo.se1test;

import org.junit.Test;
import org.yoooo.se1.QueryBridgeWords;

import static org.junit.Assert.assertEquals;

public class QueryBridgeWordsTest2 {

    private String wordL = "uvwxy";
    private String wordR = "qqq";
    private String expected = "No \"uvwxy\" and \"qqq\" in the graph!";
    private String expectedRev = "No \"qqq\" and \"uvwxy\" in the graph!";

    @Test
    public void test() {
        assertEquals(expected, new QueryBridgeWords().queryBridgeWords(wordL, wordR));
        assertEquals(expectedRev, new QueryBridgeWords().queryBridgeWords(wordR, wordL));
    }

}
