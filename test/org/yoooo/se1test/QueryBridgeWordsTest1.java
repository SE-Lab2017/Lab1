package org.yoooo.se1test;

import org.junit.Test;
import org.yoooo.se1.QueryBridgeWords;

import static org.junit.Assert.assertEquals;

public class QueryBridgeWordsTest1 {

    private String wordL = "throw";
    private String wordR = "aaaaa";
    private String expected = "No \"aaaaa\" in the graph!";
    private String expectedRev = "No \"aaaaa\" in the graph!";

    @Test
    public void test() {
        assertEquals(expected, new QueryBridgeWords().queryBridgeWords(wordL, wordR));
        assertEquals(expectedRev, new QueryBridgeWords().queryBridgeWords(wordR, wordL));
    }

}
